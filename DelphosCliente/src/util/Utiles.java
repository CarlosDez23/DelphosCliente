/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

/**
 *
 * @author cgonz
 */
public class Utiles {
	
	public static void lanzarMensaje(String mensaje){
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
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
}
