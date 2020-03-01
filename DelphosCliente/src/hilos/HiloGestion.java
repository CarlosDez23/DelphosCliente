/*
 * Hilo para gestionar todas las inserciones, ya sean de cursos, usuarios, notas
 */
package hilos;

import comunicacion.ComunicacionEstatica;
import constantes.CodigoOrden;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import util.Utiles;
import vistas.Administracion;

/**
 *
 * @author Carlos González
 */
public class HiloGestion implements Runnable {

	private Thread hilo;
	private Object objetoEnviar;
	//Se corresponde con la isntrucción que va a realizar el hilo del lado del servidor
	private short accion;
	//Le pasamos la ventana por si tenemos que desactivarla desde el hilo 
	private JFrame ventanaActiva;

	//Tablas de la interfaz
	private JTable tabla;

	public HiloGestion(Object objetoEnviar, short accion) {
		this.hilo = new Thread(this);
		this.objetoEnviar = objetoEnviar;
		this.accion = accion;
	}

	public HiloGestion(Object objetoEnviar, short accion, JFrame ventanaActiva) {
		this.objetoEnviar = objetoEnviar;
		this.accion = accion;
		this.ventanaActiva = ventanaActiva;
		this.hilo = new Thread(this);
	}

	public HiloGestion(short accion, JTable tabla, JFrame ventanaActiva) {
		this.accion = accion;
		this.tabla = tabla;
		this.ventanaActiva = ventanaActiva;
		this.hilo = new Thread(this);
	}

	public void start() {
		this.hilo.start();
	}
	
	public void join(){
		try {
			this.hilo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println(accion);
		gestionAccion();
	}

	private void gestionAccion() {
		try {
			ComunicacionEstatica.enviarObjeto(accion);
			switch (this.accion) {
			case CodigoOrden.REGISTRAR:
				gestionRegistro();
				break;

			case CodigoOrden.LOGIN:
				gestionLogin();
				break;

			case CodigoOrden.LISTAR_USUARIOS:
				listarUsuarios();
				break;
			
			case CodigoOrden.ACTIVAR_USUARIO:
				activarUsuario();
				break;
			default:
				break;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void gestionRegistro() throws IOException, ClassNotFoundException {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		boolean ok = (boolean) ComunicacionEstatica.getInput().readObject();
		if (ok) {
			Utiles.lanzarMensaje("Te has registrado correctamente");
		} else {
			Utiles.lanzarMensaje("Ha habido un problema con el registro");
		}
	}

	private void gestionLogin() throws IOException, ClassNotFoundException {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		Usuario aux = (Usuario) ComunicacionEstatica.getInput().readObject();
		System.out.println(aux);
		if (aux == null) {
			Utiles.lanzarMensaje("El usuario no está registrado");
		} else {
			new Administracion().setVisible(true);
			this.ventanaActiva.dispose();
		}
	}

	private void listarUsuarios() throws IOException, ClassNotFoundException {
		System.out.println(accion);
		ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) ComunicacionEstatica.getInput().readObject();
		Utiles.construirTabla(listaUsuarios, tabla);
		Administracion.setListaUsuarios(listaUsuarios);
	}
	
	private void activarUsuario() throws IOException, ClassNotFoundException{
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		boolean ok = (boolean) ComunicacionEstatica.getInput().readObject();
		if (ok) {
			Utiles.lanzarMensaje("Usuario activado correctamente");
		} else {
			Utiles.lanzarMensaje("Ha habido un problema al activar al usuario");
		}
	}
}
