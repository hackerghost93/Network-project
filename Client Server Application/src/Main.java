import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Main extends JFrame implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6181612111777646203L;

	public Main() {
		super("Client-Server Application");
		JButton tcpBtn = new JButton("TCP");
		tcpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TCPWindow().run();
			}
			
		});
		setResizable(false);
		JButton udpBtn = new JButton("UDP");
		udpBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				new UDPWindow().run();
			}
		});
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
