package remote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private ObjectInputStream serverDataStream = null;
	private ObjectOutputStream clientDataStream = null;
	private State currentState;
	public void run(int port, String IPAddress) {
		Socket s = null;
		try {
			s = new Socket(IPAddress, port);
			currentState = State.CONNECTED;
			serverDataStream = new ObjectInputStream(s.getInputStream());
			clientDataStream = new ObjectOutputStream(s.getOutputStream());
			AutoHeader ah = new AutoHeader().setState(this.currentState).setBodyType(String.class);
			AutoRequest ar = new AutoRequest(ah, "getmenu");
			clientDataStream.writeObject(ar);
			Object response1 = serverDataStream.readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.printf("IOException caught when attempting to connect to %s:%d. This is an unrecoverable error, Exiting...", IPAddress, port);
			System.exit(2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ClassNotFoundException caught while attempting to read from the server. This is an unrecoverable error, Exiting...");
			System.exit(2);
		} finally {
			try {
				s.close();
				serverDataStream.close();
				clientDataStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(2);
			}
		}
	}
}
