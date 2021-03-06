import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;


public class TCPClient extends Thread {

	private Socket socket;
	private String username;
	private PrintWriter toServer;
	private JTextArea clientWriter;
	private BufferedReader fromServer;
	
	public TCPClient(String username, JTextArea clientWriter) {
		this.clientWriter = clientWriter;
		this.username = username;
	}
	
	@Override
	public void run() {
		try {
			socket = new Socket("localhost", TCPServer.PORT);
			toServer = new PrintWriter(socket.getOutputStream(), true);
			fromServer = new BufferedReader(new 
					InputStreamReader(socket.getInputStream()));
			while(true) {
				int a = (int)(Math.random()*(1e9))%100 + 1;
				int b = (int)(Math.random()*(1e9))%100 + 1;
				String message = username + ": " + a + " " + b;
				toServer.println(message);
				Helper.write(clientWriter, "\n" + message 
						+ " |Server ==>" + fromServer.readLine());
				Helper.randomWait();
			}
		} catch(Exception e) {}
	}

}

