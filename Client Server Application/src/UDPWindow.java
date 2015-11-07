import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UDPWindow extends JFrame implements Runnable{
	/**
	 * UDPWindow 
	 * constructor null 
	 */
	private static final long serialVersionUID = 5214317696524690836L;
	public JTextArea UDPserver = new JTextArea("Server");
	private JTextArea UDPclient = new JTextArea("Client\n\n");
	private JTextField number = new JTextField("Number");
	private JLabel label = new JLabel("Number of Clients");
	
	private static final int FONTSIZE = 16; 
	
	private JButton btn = new JButton("Connect");
	
	UDPWindow()
	{
		// Simple GUI Constructor
		super("UCP");
		setSize(800,700);
		//To show it at the center of the screen
		setLocationRelativeTo(null);
		//Panels for GUI layouts
		JPanel panel = new JPanel();
		JPanel textPanel = new JPanel();
		JPanel bottomPanel= new JPanel();
		panel.setLayout(new BorderLayout());
		textPanel.setLayout(new GridLayout(1,2));
		bottomPanel.setLayout(new FlowLayout());
		panel.add(textPanel,BorderLayout.CENTER);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		UDPserver.setEnabled(false);
		UDPclient.setEnabled(false);
		UDPserver.setFont(new Font("utf-8", Font.ITALIC, FONTSIZE));
		UDPclient.setFont(new Font("utf-8", Font.ITALIC, FONTSIZE));
		JScrollPane clientScroll = new JScrollPane (UDPclient);
	    clientScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    clientScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    JScrollPane serverScroll = new JScrollPane (UDPserver);
	    serverScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    serverScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    number.setMinimumSize(new Dimension(270,280));
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = number.getText();
				StringBuilder t = new StringBuilder("");
				for(int i = 0 ; i < s.length() ; ++i)
					if(Character.isDigit(s.charAt(i)))
						t.append(s.charAt(i));
				if(t.length() == 0 || t.length() > 4) {
					JOptionPane.showMessageDialog(null, "Number REJECTED !!", 
								"Error Message", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int n = Integer.parseInt(t.toString());
				try {
					///Create Clients 
					UDPClient.writeServer = UDPclient ;
					UDPClient[] clients  = new UDPClient[50] ;
					for(int i = 0 ; i < n ; i++)
					{
						clients[i]= new UDPClient(7999,i);
					}
					for(int i = 0 ; i < n ; i++)
						clients[i].start();
				} catch(Exception ex) { ex.printStackTrace(); }
			}
			
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		number.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		textPanel.add(clientScroll);
		textPanel.add(serverScroll);
		bottomPanel.add(label);
		bottomPanel.add(number);
		bottomPanel.add(btn);
		add(panel);
	}
	
	@SuppressWarnings("deprecation")
	public void run(){
		show();
		try {
			new UDPServer(7999,this.UDPserver).start();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
