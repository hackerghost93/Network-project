/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JTextArea;

/**
 *
 * @author HackerGhost
 */
public class UDPServerCore implements Runnable {
    static DatagramSocket socket ;
    DatagramPacket packet = null ;
    byte[] BUFFER = new byte[256];
    public static JTextArea writeServer ;

    public UDPServerCore(DatagramPacket packet , 
            byte[] BUFFER ) {
        this.packet = packet ;
        this.BUFFER = BUFFER ;
    }
    
    
    
    @Override
    public void run(){
    	/// Get data from packet
        String x = new String(packet.getData(),0,packet.getLength());
        /// Call the function to solve the string and return the answer
        String y = parseString(x);
        /// the answer i will put it in buffer
        BUFFER = y.getBytes();
        /// Make data for sending the packet
        DatagramPacket res = new DatagramPacket(BUFFER , BUFFER.length 
        ,packet.getAddress(),packet.getPort());
        try{
        	/// Send the packet
        sendPacket(res);
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
    
    synchronized private static void sendPacket(DatagramPacket packet) throws IOException
    {
    	/// Just as i solved error 
    	/// making a pool for threads you should implement it with your hand not using ExecutorService
    	socket.send(packet);
    }
    
    synchronized private static String parseString(String x )
    {
    	/// Parse and Solve
        String arr[] = x.split(" ");
        int z = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[2]);
        y = y + z;
        writeServer.append(x+ "=" + y +"\n");
        return String.valueOf(y);
    }
    
}
