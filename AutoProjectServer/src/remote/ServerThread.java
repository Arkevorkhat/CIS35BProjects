package remote;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread implements Runnable {
	public ObjectInputStream clientDataStream = null;
	public ObjectOutputStream serverDataStream = null;
	public Socket client = null;
	public MenuState currentMenu;

	public ServerThread(Socket client) {
		this.client = client;
	}
	@Override
	public void run() {
		try {
			if (client.isConnected()) {
				serverDataStream = new ObjectOutputStream(client.getOutputStream());
				clientDataStream = new ObjectInputStream(client.getInputStream());
				do {
					AutoRequest ar = (AutoRequest) clientDataStream.readObject();
					switch(ar.head.requestedOperation) {
					case GET_AUTO:
						break;
					case GET_LIST:
						break;
					case GET_MENU:			
						break;
					case UPLOAD_PROPERTIES:
						break;
					case RESET:
						break;
					default:
						break;
					}
				} while (client.isConnected());
			}
		} catch (IOException e) {
			System.err.printf("Failed to create %s while connecting to %s, exiting...",
					e.getStackTrace()[0].getClassName(), client.getInetAddress().getHostName());
		} catch (ClassNotFoundException e) {
			System.err.printf("Unexpected input obtained when recieving data from %s",
					client.getInetAddress().getHostAddress());
		} finally {
			try {
				client.close();
				clientDataStream.close();
				serverDataStream.close();
			} catch (IOException e) {
				System.err.println("Could not close streams, this is an unrecoverable error, exiting...");
				System.exit(2);
			}
		}
	}
}