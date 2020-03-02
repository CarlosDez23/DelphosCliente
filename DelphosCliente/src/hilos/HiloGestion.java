/*
 * Hilo para gestionar todas las inserciones, ya sean de cursos, usuarios, notas
 */
package hilos;

import comunicacion.ComunicacionEstatica;
import constantes.CodigoOrden;
import controlador.ControladorInterfaz;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import modelo.Alumno;
import modelo.Curso;
import modelo.Usuario;
import util.Utiles;
import vistas.Administracion;
import vistas.VentanaProfesor;

/**
 *
 * @author Carlos González
 */
public class HiloGestion implements Runnable {

	private Thread hilo;
	private Object objetoEnviar;
	//Se corresponde con la isntrucción que va a realizar el hilo del lado del servidor
	private short accion;
	//Para la gestión de algunas ids
	private Object id;

	/**
	 * Distintos elementos de la interfaz que le vamos a pasar al hilo en diferentes constructores en función de donde sea llamado y el elemento de la interfaz que deba tocar
	 */
	//Le pasamos la ventana por si tenemos que desactivarla desde el hilo 
	private JFrame ventanaActiva;

	//Tablas de la interfaz
	private JTable tabla;

	//Lista de la interfaz
	private JList<String> lista;

	//Combobox de la interfaz
	private JComboBox<String> combo;

	/**
	 * Tendremos distintos constructores en función de las distintas acciones que va a realizar el hilo
	 *
	 * @param objetoEnviar
	 * @param accion
	 */
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

	public HiloGestion(short accion, JList<String> lista, JComboBox<String> combo) {
		this.accion = accion;
		this.lista = lista;
		this.combo = combo;
		this.hilo = new Thread(this);
	}

	public HiloGestion(Object objetoEnviar, short accion, Object id) {
		this.objetoEnviar = objetoEnviar;
		this.accion = accion;
		this.id = id;
		this.hilo = new Thread(this);
	}

	public HiloGestion(short accion, Object id, JList<String> lista) {
		this.accion = accion;
		this.id = id;
		this.lista = lista;
		this.hilo = new Thread(this);
	}

	public void start() {
		this.hilo.start();
		this.hilo = new Thread(this);
	}

	@Override
	public void run() {
		System.out.println(accion);
		gestionAccion();
	}

	private void gestionAccion() {
		try {
			ComunicacionEstatica.enviarObjeto(accion);
			System.out.println(accion);
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
			case CodigoOrden.LISTAR_CURSOS:
				listarCursos();
				break;

			case CodigoOrden.ADD_CURSO:
				insertarCurso();
				break;

			case CodigoOrden.EDITAR_CURSO:
				editarCurso();
				break;

			case CodigoOrden.ELEGIR_CURSO:
				asignarCurso();
				break;

			case CodigoOrden.ASIGNAR_PROFESOR:
				asignarCursoProfesor();
				break;

			case CodigoOrden.LISTAR_CURSOS_PROFESOR:
				listarCursosProfesor();
				break;

			case CodigoOrden.LISTAR_ALUMNOS_CURSO:
				listarAlumnosCurso();
				break;

			case CodigoOrden.PONER_NOTA:
				ponerNota();
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
		boolean ok = (boolean) ComunicacionEstatica.recibirObjeto();
		if (ok) {
			Utiles.lanzarMensaje("Te has registrado correctamente");
		} else {
			Utiles.lanzarMensaje("Ha habido un problema con el registro");
		}
	}

	private void gestionLogin() throws IOException, ClassNotFoundException {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		Usuario aux = (Usuario) ComunicacionEstatica.recibirObjeto();
		System.out.println(aux);
		if (aux == null) {
			Utiles.lanzarMensaje("El usuario no está registrado");
		} else {
			ControladorInterfaz.tipoLogin(aux, this.ventanaActiva);
		}
	}

	private void listarUsuarios() throws IOException, ClassNotFoundException {
		System.out.println(accion);
		ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) ComunicacionEstatica.recibirObjeto();
		Utiles.construirTabla(listaUsuarios, tabla);
		Administracion.setListaUsuarios(listaUsuarios);
	}

	private void activarUsuario() throws IOException, ClassNotFoundException {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		boolean ok = (boolean) ComunicacionEstatica.getInput().readObject();
		if (ok) {
			Utiles.lanzarMensaje("Usuario activado correctamente");
		} else {
			Utiles.lanzarMensaje("Ha habido un problema al activar al usuario");
		}
	}

	private void listarCursos() {
		System.out.println("Entrando a listar cursos");
		ArrayList<Curso> listaCurso = (ArrayList<Curso>) ComunicacionEstatica.recibirObjeto();
		System.out.println("Lista de cursos recibida " + listaCurso.size());
		if (!listaCurso.isEmpty()) {
			Administracion.setListaCursos(listaCurso);
			DefaultListModel demoList = new DefaultListModel();
			for (int i = 0; i < listaCurso.size(); i++) {
				Curso aux = (Curso) listaCurso.get(i);
				demoList.addElement(aux.getCodigoCurso() + "    " + aux.getNombre());
				this.combo.addItem(aux.getCodigoCurso());
			}
			this.lista.setModel(demoList);

		} else {
			Utiles.lanzarMensaje("Aún no se han registrado cursos");
		}
	}

	private void insertarCurso() {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		boolean ok = (boolean) ComunicacionEstatica.recibirObjeto();
		if (ok) {
			Utiles.lanzarMensaje("Curso añadido correctamente");
		} else {
			Utiles.lanzarMensaje("Ha habido un problema al añadir el curso");
		}
	}

	private void editarCurso() {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		boolean ok = (boolean) ComunicacionEstatica.recibirObjeto();
		if (ok) {
			Utiles.lanzarMensaje("Curso modificado correctamente");
		} else {
			Utiles.lanzarMensaje("Ha habido un problema al actualizar el curso");
		}
	}

	private void asignarCurso() {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		boolean ok = (boolean) ComunicacionEstatica.recibirObjeto();
		if (ok) {
			Utiles.lanzarMensaje("Curso asignado");
		} else {
			Utiles.lanzarMensaje("Ha habido un problema al asignar el curso");
		}
	}

	private void asignarCursoProfesor() {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		ComunicacionEstatica.enviarObjeto(id);
		gestionConfirmacion("Profesor asignado a curso correctamente", "Ha habido un problema al asignar al profesor");
	}

	private void listarCursosProfesor() {
		ComunicacionEstatica.enviarObjeto(id);
		ArrayList<Curso> listaCurso = (ArrayList<Curso>) ComunicacionEstatica.recibirObjeto();
		if (!listaCurso.isEmpty()) {
			System.out.println(listaCurso.size());
			VentanaProfesor.setListCur(listaCurso);
			DefaultListModel demoList = new DefaultListModel();
			for (int i = 0; i < listaCurso.size(); i++) {
				Curso aux = (Curso) listaCurso.get(i);
				demoList.addElement(aux.getCodigoCurso() + "    " + aux.getNombre());
			}
			this.lista.setModel(demoList);

		} else {
			Utiles.lanzarMensaje("Aún no se han registrado cursos");
		}
	}

	private void listarAlumnosCurso() {
		ComunicacionEstatica.enviarObjeto(id);
		ArrayList<Alumno> listaAlumnos = (ArrayList<Alumno>) ComunicacionEstatica.recibirObjeto();
		if (!listaAlumnos.isEmpty()) {
			VentanaProfesor.setListAlu(listaAlumnos);
			DefaultListModel demoList = new DefaultListModel();
			for (int i = 0; i < listaAlumnos.size(); i++) {
				Alumno aux = (Alumno) listaAlumnos.get(i);
				demoList.addElement(aux.getNombreUsuario());
			}
			this.lista.setModel(demoList);
		} else {
			Utiles.lanzarMensaje("No hay alumnos en este curso");
		}
	}

	private void ponerNota() {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		gestionConfirmacion("Nota asignada correctamente", "Ha habido un problema al poner la nota");

	}

	private void gestionConfirmacion(String correcto, String incorrecto) {
		boolean ok = (boolean) ComunicacionEstatica.recibirObjeto();
		if (ok) {
			Utiles.lanzarMensaje(correcto);
		} else {
			Utiles.lanzarMensaje(incorrecto);
		}
	}
}
