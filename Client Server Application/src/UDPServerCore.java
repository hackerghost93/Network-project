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
    DatagramSocket socket = null ;
    DatagramPacket packet = null ;
    byte[] BUFFER = new byte[256];
    public static JTextArea writeServer ;

    public UDPServerCore(DatagramSocket socket , DatagramPacket packet , 
            byte[] BUFFER ) {
        this.socket = socket ;
        this.packet = packet ;
        this.BUFFER = BUFFER ;
    }
    
    
    
    @Override
    public void run(){
        String x = new String(packet.getData(),0,packet.getLength());
        String y = parseString(x);
        BUFFER = y.getBytes();
        //packet.setData(y.getBytes());
        DatagramPacket res = new DatagramPacket(BUFFER , BUFFER.length 
        ,packet.getAddress(),packet.getPort());
        try{
        socket.send(res);
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
    
    synchronized private static String parseString(String x )
    {
        String arr[] = x.split(" ");
        int z = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[2]);
        y = y + z;
        writeServer.append(x+ "=" + y +"\n");
        return String.valueOf(y);
    }
    
}
