/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JFrame;
import modelo.Alumno;
import modelo.Usuario;
import vistas.Administracion;
import vistas.VentanaAlumno;
import vistas.VentanaProfesor;

/**
 *
 * @author Carlos González
 */
public class ControladorInterfaz {
	
	public static void tipoLogin(Usuario u, JFrame ventanaActiva) {
		switch (u.getRol()) {
		case 0:
			new Administracion().setVisible(true);
			ventanaActiva.dispose();
			break;
		case 1:
			new VentanaAlumno(u.getIdUsuario()).setVisible(true);
			ventanaActiva.dispose();
			break;
		case 2:
			new VentanaProfesor(u.getIdUsuario()).setVisible(true);
			ventanaActiva.dispose();
			break;
		case 3:
			new Administracion().setVisible(true);
			ventanaActiva.dispose();
		case 4:
			break;
		default:
			new Administracion().setVisible(true);
			ventanaActiva.dispose();
			break;
		}
	}
	
}
