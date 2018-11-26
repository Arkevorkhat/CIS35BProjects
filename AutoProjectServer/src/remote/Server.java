package remote;

import java.io.IOException;
import java.net.ServerSocket;

import adapter.InitAuto;

public class Server {
	public static ServerSocket coreSocket = null;
	static {
		InitAuto.init();
	}
	public static void Start(int port) {
		try {
			coreSocket = new ServerSocket(port);
			do {
				try {
					new Thread(new ServerThread(coreSocket.accept())).start();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					break;
				}
			} while (true);
		} catch (IOException e) {
		} finally {

		}
	}
}
