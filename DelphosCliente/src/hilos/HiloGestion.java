/*
 * Hilo para gestionar todas las inserciones, ya sean de cursos, usuarios, notas
 */
package hilos;

import comunicacion.ComunicacionEstatica;
import constantes.CodigoOrden;
import controlador.ControladorInterfaz;
import java.security.PublicKey;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import modelo.Alumno;
import modelo.Curso;
import modelo.Nota;
import modelo.Usuario;
import seguridad.Seguridad;
import util.Utiles;
import vistas.Administracion;
import vistas.VentanaAlumno;
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

	//TextField de la interfaz
	private JTextField txtField;
	
	//Checkbox de la interfaz
	private JCheckBox checkBox;

	
	//Vamos a tener distintos constructores en función de lo que queramos que haga el hilo 
	
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

	public HiloGestion(short accion, Object objetoEnviar, JTextField txtField, JCheckBox checkBox) {
		this.accion = accion;
		this.objetoEnviar = objetoEnviar;
		this.txtField = txtField;
		this.checkBox = checkBox;
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

		case CodigoOrden.LISTAR_PROFESORES_ALUMNO:
			listarProfesoresAlumno();
			break;

		case CodigoOrden.VER_NOTA:
			verNota();
			break;
		default:
			break;
		}
	}

	private void gestionRegistro() {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		boolean ok = (boolean) ComunicacionEstatica.recibirObjeto();
		if (ok) {
			ControladorInterfaz.lanzarMensaje("Te has registrado correctamente");
		} else {
			ControladorInterfaz.lanzarMensaje("Ha habido un problema con el registro");
		}
	}

	private void gestionLogin() {
		ComunicacionEstatica.enviarObjeto(objetoEnviar);

		Usuario aux = (Usuario) ComunicacionEstatica.recibirObjeto();
		PublicKey publicaServidor = (PublicKey) ComunicacionEstatica.recibirObjeto();
		Seguridad.claveCifrado = aux.getClaveKey();
		Seguridad.publicaServidor = publicaServidor;
		System.out.println("Recibida publica del servidor "+Seguridad.publicaServidor);
		if (aux == null) {
			ControladorInterfaz.lanzarMensaje("El usuario no está registrado");
		} else {
			ControladorInterfaz.tipoLogin(aux, this.ventanaActiva);
		}
	}

	private void listarUsuarios() {
		ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) Seguridad.descifrar(Seguridad.claveCifrado, ComunicacionEstatica.recibirObjeto());
		Utiles.construirTabla(listaUsuarios, tabla);
		Administracion.setListaUsuarios(listaUsuarios);
	}

	private void activarUsuario() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(objetoEnviar, Seguridad.claveCifrado));
		gestionConfirmacion("Usuario activado correctamente", "Ha habido un problema al activar al usuario");
	}

	private void listarCursos() {
		System.out.println("Entrando a listar cursos");
		ArrayList<Curso> listaCurso = (ArrayList<Curso>) Seguridad.descifrar(Seguridad.claveCifrado, ComunicacionEstatica.recibirObjeto());
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
			ControladorInterfaz.lanzarMensaje("Aún no se han registrado cursos");
		}
	}

	private void insertarCurso() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(objetoEnviar, Seguridad.claveCifrado));
		gestionConfirmacion("Curso añadido", "Ha habido un problema al añadir el curso");
	}

	private void editarCurso() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(objetoEnviar, Seguridad.claveCifrado));
		gestionConfirmacion("Curso modificado correctamente", "Ha habido un problema al actualizar el curso");
	}

	private void asignarCurso() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(objetoEnviar, Seguridad.claveCifrado));
		gestionConfirmacion("Curso asignado", "Ha habido un problema al asignar el curso");
	}

	private void asignarCursoProfesor() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(objetoEnviar, Seguridad.claveCifrado));
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(id, Seguridad.claveCifrado));
		gestionConfirmacion("Profesor asignado a curso correctamente", "Ha habido un problema al asignar al profesor");
	}

	private void listarCursosProfesor() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(id, Seguridad.claveCifrado));
		ArrayList<Curso> listaCurso = (ArrayList<Curso>) Seguridad.descifrar(Seguridad.claveCifrado, ComunicacionEstatica.recibirObjeto());
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
			ControladorInterfaz.lanzarMensaje("Aún no se han registrado cursos");
		}
	}

	private void listarAlumnosCurso() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(id, Seguridad.claveCifrado));
		ArrayList<Alumno> listaAlumnos = (ArrayList<Alumno>) Seguridad.descifrar(Seguridad.claveCifrado, ComunicacionEstatica.recibirObjeto());
		if (!listaAlumnos.isEmpty()) {
			VentanaProfesor.setListAlu(listaAlumnos);
			DefaultListModel demoList = new DefaultListModel();
			for (int i = 0; i < listaAlumnos.size(); i++) {
				Alumno aux = (Alumno) listaAlumnos.get(i);
				demoList.addElement(aux.getNombreUsuario());
			}
			this.lista.setModel(demoList);
		} else {
			ControladorInterfaz.lanzarMensaje("No hay alumnos en este curso");
		}
	}

	private void ponerNota() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(objetoEnviar, Seguridad.claveCifrado));
		gestionConfirmacion("Nota asignada correctamente", "Ha habido un problema al poner la nota");

	}

	private void listarProfesoresAlumno() {
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(id, Seguridad.claveCifrado));
		ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) Seguridad.descifrar(Seguridad.claveCifrado, ComunicacionEstatica.recibirObjeto());
		VentanaAlumno.setListProfesores(listaUsuarios);
		if (!listaUsuarios.isEmpty()) {
			System.out.println("Entrando a mostrar usuarios");
			VentanaAlumno.setListProfesores(listaUsuarios);
			DefaultListModel demoList = new DefaultListModel();
			for (int i = 0; i < listaUsuarios.size(); i++) {
				Usuario aux = (Usuario) listaUsuarios.get(i);
				demoList.addElement(aux.getNombreUsuario());
			}
			this.lista.setModel(demoList);
		} else {
			ControladorInterfaz.lanzarMensaje("Parece que aún no tienes profesores");
		}
	}

	private void verNota() {
		System.out.println("Entrando notas");
		System.out.println(objetoEnviar);
		ComunicacionEstatica.enviarObjeto(Seguridad.cifrarConClaveSimetrica(objetoEnviar, Seguridad.claveCifrado));
		Nota nota = (Nota) Seguridad.descifrar(Seguridad.claveCifrado, ComunicacionEstatica.recibirObjeto());
		System.out.println(nota);
		if (nota == null) {
			ControladorInterfaz.lanzarMensaje("Todavía no tienes una nota asignada");
		} else {
			this.txtField.setText(nota.getNota());
			if (Seguridad.verificarMensaje(nota)) {
				this.checkBox.setSelected(true);
			}else{
				ControladorInterfaz.lanzarMensaje("La firma no es válida");
			}
		}
	}

	private synchronized void gestionConfirmacion(String correcto, String incorrecto) {
		boolean ok = (boolean) Seguridad.descifrar(Seguridad.claveCifrado, ComunicacionEstatica.recibirObjeto());
		if (ok) {
			ControladorInterfaz.lanzarMensaje(correcto);
		} else {
			ControladorInterfaz.lanzarMensaje(incorrecto);
		}
	}
}
