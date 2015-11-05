import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;


public class TCPServer extends Thread {
	
	public static final int PORT = 4444;
	
	private static final String welcome =
			"Server is ready for connections ...";
	
	private JTextArea serverWriter;
	
	public TCPServer(JTextArea serverWriter) {
		this.serverWriter = serverWriter;
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			Helper.write(serverWriter, "\n" + welcome + "\n");
			while(true) {
				Socket socket = serverSocket.accept();
				new TCPServerThread(socket, serverWriter).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
