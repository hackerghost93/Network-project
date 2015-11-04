import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Main extends JFrame implements Runnable {
	
	public Main() {
		super("Client-Server Application");
		JButton tcpBtn = new JButton("TCP");
		JButton udpBtn = new JButton("UDP");
		setSize(300, 100);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(tcpBtn);
		add(udpBtn);
	}
	
	public static void main(String[] args) {
		(new Main()).run();
	}

	@Override
	public void run() {
		show();
	}

}
