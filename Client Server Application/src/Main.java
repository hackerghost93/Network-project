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
		// add name to GUI
		super("Client-Server Application");
		JButton tcpBtn = new JButton("TCP");
		tcpBtn.addActionListener(new ActionListener() {
			
			// create new action listener to the button of TCP 
			// which make an instance of TCPWindow
			@Override
			public void actionPerformed(ActionEvent e) {
				new TCPWindow().run();
			}
			
		});
		setResizable(false);
		JButton udpBtn = new JButton("UDP");
		udpBtn.addActionListener(new ActionListener(){
			// for UDPWindow instance 
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
		/// new instance from main
		(new Main()).run();
	}
	
	/// show GUI
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		show();	
	}

}
