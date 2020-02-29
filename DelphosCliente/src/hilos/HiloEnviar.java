/*
 * Hilo para gestionar todas las inserciones, ya sean de cursos, usuarios, notas
 */
package hilos;

import comunicacion.ComunicacionEstatica;
import constantes.CodigoOrden;
import java.io.IOException;
import javax.swing.JFrame;
import util.Utiles;

/**
 *
 * @author Carlos González
 */
public class HiloEnviar implements Runnable {
	
	private Thread hilo;
	private Object objetoEnviar;
	//Se corresponde con la isntrucción que va a realizar el hilo del lado del servidor
	private short accion;
	//Le pasamos la ventana desde la que se instancia el hilo, para poder pintar mensajes si lo necesitamos
	private JFrame ventana;

	public HiloEnviar(Object objetoEnviar, short accion) {
		this.hilo = new Thread(this);
		this.objetoEnviar = objetoEnviar;
		this.accion = accion;
	}
	
	public void start(){
		this.hilo.start();
	}
	
	@Override
	public void run() {
		try {
			ComunicacionEstatica.getOutput().writeShort(accion);
			ComunicacionEstatica.getOutput().writeObject(objetoEnviar);
			gestionConfirmacion(ComunicacionEstatica.getInput().readBoolean());
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void gestionConfirmacion(boolean ok){
		switch(this.accion){
			case CodigoOrden.REGISTRAR:
				if (ok) {
					Utiles.lanzarMensaje("Te has registrado correctamente");
				}else{
					Utiles.lanzarMensaje("Ha habido un problema con el registro");
				}
				break;
			default:
				break;
			
		}
	}
}
