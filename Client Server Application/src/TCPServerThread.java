import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;


public class TCPServerThread extends Thread {
	
	private Socket socket;
	private String messageFromClient;
	
	private JTextArea serverWriter;
	
	public TCPServerThread(Socket socket, JTextArea server) {
		this.socket = socket;
		this.serverWriter = server;
	}
	
	public void run() {
		try {
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			while((messageFromClient = inFromClient.readLine()) != null) {
				String[] arr = messageFromClient.trim().split(" ");
				int a = Integer.parseInt(arr[arr.length-1]);
				int b = Integer.parseInt(arr[arr.length-2]);
				String message = "\n" + arr[0] + " " + arr[1].split(":")[0] + 
						"'s response: " + Integer.toString(a+b) + "\n";
				Helper.write(serverWriter, message);
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
