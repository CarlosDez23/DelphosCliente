/*
 * Hilo para gestionar todas las inserciones, ya sean de cursos, usuarios, notas
 */
package hilos;

import comunicacion.ComunicacionEstatica;
import constantes.CodigoOrden;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import modelo.Usuario;
import util.Utiles;

/**
 *
 * @author Carlos González
 */
public class HiloGestion implements Runnable {

	private Thread hilo;
	private Object objetoEnviar;
	//Se corresponde con la isntrucción que va a realizar el hilo del lado del servidor
	private short accion;

	public HiloGestion(Object objetoEnviar, short accion) {
		this.hilo = new Thread(this);
		this.objetoEnviar = objetoEnviar;
		this.accion = accion;
	}

	public void start() {
		this.hilo.start();
	}

	@Override
	public void run() {
		gestionAccion();
	}

	private void gestionAccion() {
		try {
			switch (this.accion) {
			case CodigoOrden.REGISTRAR:
				gestionRegistro();
				break;

			case CodigoOrden.LOGIN:
				gestionLogin();
				break;
			default:
				break;
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void gestionRegistro() throws IOException, ClassNotFoundException {
		ComunicacionEstatica.getOutput().writeShort(accion);
		ComunicacionEstatica.getOutput().writeObject(objetoEnviar);
		boolean ok = (boolean) ComunicacionEstatica.getInput().readObject();
		if (ok) {
			Utiles.lanzarMensaje("Te has registrado correctamente");
		} else {
			Utiles.lanzarMensaje("Ha habido un problema con el registro");
		}
	}
	
	private void gestionLogin() throws IOException, ClassNotFoundException{
		ComunicacionEstatica.getOutput().writeShort(accion);
		ComunicacionEstatica.getOutput().writeObject(objetoEnviar);
		Usuario aux = (Usuario) ComunicacionEstatica.getInput().readObject();
		System.out.println(aux);
		if (aux == null) {
			Utiles.lanzarMensaje("El usuario no está registrado");
		}else{
			byte rol = aux.getRol();
			String rolUsuario = "";
			switch(rol){
				case 0:
					rolUsuario = "Sin asignar";
					break;
				case 1:
					rolUsuario ="Alumno";
					break;
				case 2:
					rolUsuario ="Profesor";
					break;
				case 3:
					rolUsuario = "Administrador";
				default:
					break;
			}
			Utiles.lanzarMensaje("Bienvenido "+aux.getNombreUsuario()+" tu rol es "+rolUsuario);
		}		
	}
}
