/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


import javax.swing.JTextArea;

/**
 *
 * @author HackerGhost
 */
public class UDPServer extends Thread {
       
    DatagramSocket socket = null ;
    DatagramPacket packet = null ;
    byte[] BUFFER = new byte[256];
 
    // Add Server Socket number and Reference where the Server will write
    public UDPServer(int port,JTextArea writeServer	) throws SocketException{
        this.socket = new DatagramSocket(port);
        UDPServerCore.socket = socket ;
        UDPServerCore.writeServer = writeServer ;
        writeServer.append("Establishing Connection \n" );
    }
    
    @Override
    /// Start Server Thread
    public void run()
    {
        while(true)
        {
        	/// When I receive I make a core Thread and handle it
            try{
                packet = new DatagramPacket(BUFFER,BUFFER.length);
                socket.receive(packet);
                new UDPServerCore(packet,BUFFER).run();
            }
            catch(IOException e)
            {
                System.out.println("UDP receiving packet problem "
                + e.getMessage());
            }
        }
    }
    
}
