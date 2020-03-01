/*
 * Hilo para gestionar todas las inserciones, ya sean de cursos, usuarios, notas
 */
package hilos;

import comunicacion.ComunicacionEstatica;
import constantes.CodigoOrden;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
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
	
	private void gestionLogin() throws IOException, ClassNotFoundException{
		ComunicacionEstatica.enviarObjeto(objetoEnviar);
		Usuario aux = (Usuario) ComunicacionEstatica.getInput().readObject();
		System.out.println(aux);
		if (aux == null) {
			Utiles.lanzarMensaje("El usuario no está registrado");
		}else{
			byte rol = aux.getRol();
		
			
			//Utiles.lanzarMensaje("Bienvenido "+aux.getNombreUsuario()+" tu rol es "+rolUsuario);
			new Administracion().setVisible(true);
			this.ventanaActiva.dispose();
		}		
	}
	
	private String gestionRol(byte idRol){
		String rolUsuario = "";
		switch(idRol){
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
					break;
				
				case 4:
					rolUsuario = "Ambos"; 
				default:
					break;
			}
		return rolUsuario;
	}
	
	private synchronized void listarUsuarios() throws IOException, ClassNotFoundException{
		System.out.println(accion);
		ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>)ComunicacionEstatica.getInput().readObject();
		if (!listaUsuarios.isEmpty()) {
			DefaultTableModel modelo = new DefaultTableModel();
			modelo.addColumn("Nombre");
			modelo.addColumn("Telefono");
			modelo.addColumn("Direccion");
			modelo.addColumn("Edad");
			modelo.addColumn("Rol");
			for (int i = 0; i < listaUsuarios.size(); i++) {
				Usuario aux = (Usuario)listaUsuarios.get(i);
				modelo.addRow(new Object[]{
					aux.getNombreUsuario(),
					aux.getTelefono(),
					aux.getDireccion(),
					String.valueOf(aux.getEdad()),
					gestionRol(aux.getRol()),	
				});
			}
			
			tabla.setModel(modelo);
				
		}
		
		//Pillamos los datos del servicio
		
//		ArrayList<Usuario> listUsuarios = ControladorUsuarios.listarUsuarios();
//		if (listUsuarios != null) {
//			DefaultTableModel modelo = new DefaultTableModel();
//			tabla = new JTable(modelo);
//			modelo.addColumn("DNI");
//			modelo.addColumn("USUARIO");
//			modelo.addColumn("CORREO");
//			modelo.addColumn("ROL");
//			for (int i = 0; i < listUsuarios.size(); i++) {
//				Usuario u = (Usuario) listUsuarios.get(i);
//				modelo.addRow(new Object[]{u.getDni(), u.getUsuario(), u.getCorreo(), String.valueOf(u.getRol())});
//			}
//			this.getContentPane().setLayout(null);
//			tabla.setModel(modelo);
//			tabla.setBounds(10,10,50,50);
//			JScrollPane scrTabla = new JScrollPane(tabla);
//			scrTabla.setBounds(20, 50, 800, 200);
//			this.add(scrTabla);
//			
//			ListSelectionModel cell = tabla.getSelectionModel();
//			cell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			
//			cell.addListSelectionListener(new ListSelectionListener() {
//				@Override
//				public void valueChanged(ListSelectionEvent e) {		
//					int fila = tabla.getSelectedRow();
//					valor = (String) tabla.getValueAt(fila, 0);
//					System.out.println(valor);
//					
//				}
//			});
//		}
		
	}
}
