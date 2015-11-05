import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;


public class TCPClient extends Thread {

	private Socket socket;
	private String username;
	private PrintWriter toServer;
	private JTextArea clientWriter;
	
	public TCPClient(String username, JTextArea clientWriter) {
		this.clientWriter = clientWriter;
		this.username = username;
	}
	
	@Override
	public void run() {
		try {
			socket = new Socket("localhost", TCPServer.PORT);
			toServer = new PrintWriter(socket.getOutputStream(), true);
			while(true) {
				int a = (int)(Math.random()*(1e9))%100 + 1;
				int b = (int)(Math.random()*(1e9))%100 + 1;
				String message = username + ": " + a + " " + b;
				toServer.println(message);
				Helper.write(clientWriter, "\n" + message + "\n");
				Helper.randomWait();
			}
		} catch(Exception e) {}
	}

}
