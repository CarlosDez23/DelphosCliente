/*
 * Clase que va a gestionar la comunicación con el servidor
 */
package comunicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Carlos González
 */
public class ComunicacionEstatica {
	
	private static final String DIRECCION = "localhost";
	private static final int PUERTO = 49153;
	private static Socket servidor;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	
	static{
		try {
			servidor = new Socket(DIRECCION, PUERTO);
			output = new ObjectOutputStream(servidor.getOutputStream());
			input = new ObjectInputStream(servidor.getInputStream()); 
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//Con este getter vamos a poder recoger el Socket donde lo necesitemos
	public static Socket getServidor() {
		return servidor;
	}
	
	//Igualmente, le hacemos getters a los streams que vayamos a necesitar
	public static ObjectOutputStream getOutput() {
		return output;
	}

	public static ObjectInputStream getInput() {
		return input;
	}
}
