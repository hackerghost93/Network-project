import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TCPWindow extends JFrame implements Runnable {
	
	/**
	 * constructor null
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea server = new JTextArea("Server");
	private JTextArea client = new JTextArea("Client\n\n");
	private JTextArea number = new JTextArea("Number");
	private JLabel label = new JLabel("Number of Clients");
	
	private static final int FONTSIZE = 16; 
	
	private JButton btn = new JButton("Connect");
	
	
	public TCPWindow() {
		super("TCP");
		setSize(600, 600);
		setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel textPanel = new JPanel();
		JPanel bottomPanel= new JPanel();
		textPanel.setLayout(new GridLayout(1,2));
		bottomPanel.setLayout(new FlowLayout());
		panel.add(textPanel,BorderLayout.CENTER);
		panel.add(bottomPanel,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		server.setEnabled(false);
		client.setEnabled(false);
		server.setFont(new Font("utf-8", Font.ITALIC, FONTSIZE));
		client.setFont(new Font("utf-8", Font.ITALIC, FONTSIZE));
		JScrollPane clientScroll = new JScrollPane(client);
	    	clientScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    	clientScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	   	JScrollPane serverScroll = new JScrollPane(server);
	   	serverScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    	serverScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    	serverScroll.setSize(new Dimension(300,500));
	    	clientScroll.setSize(new Dimension(300,500));
		server.setBounds(0, 0, 300, 500);
		client.setBounds(300, 0, 300, 500);
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
					for(int i = 1 ; i <= n ; ++i)
						new TCPClient("Client " + i, client).start();
				} catch(Exception ex) { ex.printStackTrace(); }
			}
			
		});
	    number.setMinimumSize(new Dimension(270,280));
		number.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		textPanel.add(clientScroll);
		textPanel.add(serverScroll);
		bottomPanel.add(label);
		bottomPanel.add(number);
		bottomPanel.add(btn);
		add(panel);
	}

	@SuppressWarnings("deprecation")
	public void run()  {
		show();
		new TCPServer(server).start();
	}
	
}

