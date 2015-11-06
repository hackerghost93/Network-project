import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class UDPWindow extends JFrame implements Runnable{
	/**
	 * UDPWindow 
	 * constructor null 
	 */
	private static final long serialVersionUID = 5214317696524690836L;
	public JTextArea UDPserver = new JTextArea("Server");
	private JTextArea UDPclient = new JTextArea("Client\n\n");
	private JTextArea number = new JTextArea("");
	private JLabel label = new JLabel("Number of Clients");
	
	private static final int FONTSIZE = 16; 
	
	private JButton btn = new JButton("Connect");
	
	UDPWindow()
	{
		super("UCP");
		setSize(600,600);
		//setResizable(false);
		setLayout(new GridLayout(5,2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		UDPserver.setEnabled(false);
		UDPclient.setEnabled(false);
		UDPserver.setFont(new Font("utf-8", Font.ITALIC, FONTSIZE));
		UDPclient.setFont(new Font("utf-8", Font.ITALIC, FONTSIZE));
//		UDPserver.setBounds(0, 0, 300, 500);	
//		UDPclient.setBounds(0, 0, 300, 500);
		JScrollPane clientScroll = new JScrollPane (UDPclient);
	    clientScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    clientScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    JScrollPane serverScroll = new JScrollPane (UDPserver);
	    serverScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    serverScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    serverScroll.setSize(new Dimension(300,500));
	    clientScroll.setSize(new Dimension(300,500));

		label.setBounds(0, 525, label.getText().length()*10, 25);
		number.setBounds(label.getText().length()*8, 525, 
				300 - (label.getText().length()*10 + 50), 25);
		btn.setBounds(label.getText().length()*8 + 300 
				- (label.getText().length()*10 + 50)+ 10, 525, 150 , 25);
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
		number.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		add(clientScroll);
		add(serverScroll);
		add(label);
		add(number);
		add(btn);
	}
	
	public void run(){
		show();
		try {
			new UDPServer(7999,this.UDPserver).start();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			//new UDPServer(7891).start();
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}
		
	}
}
