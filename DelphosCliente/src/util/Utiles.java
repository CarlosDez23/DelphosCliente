/*
 * Algunas utilidades para nuestra aplicación
 */
package util;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;

/**
 *
 * @author cgonz
 */
public class Utiles {

	
	public static String gestionRol(byte idRol) {
		String rolUsuario = "";
		switch (idRol) {
		case 0:
			rolUsuario = "Sin asignar";
			break;
		case 1:
			rolUsuario = "Alumno";
			break;
		case 2:
			rolUsuario = "Profesor";
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

	public static int gestionRolInversa(String rolSeleccionado) {
		int rol;
		switch (rolSeleccionado) {
		case "Alumno":
			rol = 1;
			break;
		case "Profesor":
			rol = 2;
			break;
		case "Administrador":
			rol = 3;
			break;
		default:
			rol = 4;
			break;
		}
		return rol;
	}

	public static void construirTabla(ArrayList<Usuario> listaUsuarios, JTable tabla) {
		if (!listaUsuarios.isEmpty()) {
			DefaultTableModel modelo = new DefaultTableModel();
			modelo.addColumn("Nombre");
			modelo.addColumn("Telefono");
			modelo.addColumn("Direccion");
			modelo.addColumn("Edad");
			modelo.addColumn("Rol");
			for (int i = 0; i < listaUsuarios.size(); i++) {
				Usuario aux = (Usuario) listaUsuarios.get(i);
				modelo.addRow(new Object[]{
					aux.getNombreUsuario(),
					aux.getTelefono(),
					aux.getDireccion(),
					String.valueOf(aux.getEdad()),
					Utiles.gestionRol(aux.getRol()),});
			}
			tabla.setModel(modelo);
		}
	}
}
