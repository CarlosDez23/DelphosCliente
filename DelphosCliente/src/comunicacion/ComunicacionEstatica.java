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

	static {
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

	public synchronized static void enviarObjeto(Object object) {
		try {
			output.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static Object recibirObjeto(){
		Object objetoRecibido = null;
		try {
			objetoRecibido = input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objetoRecibido;
	}
}
