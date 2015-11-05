/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JTextArea;

/**
 *
 * @author HackerGhost
 */
public class UDPClient extends Thread {
    
    DatagramSocket socket = null ;
    DatagramPacket packet = null ;
    byte[] BUFFER = new byte[256];
    int arg1 , arg2 ;
    final int port ;
    int clientNum ;
    public static JTextArea writeServer  ;
    
    public UDPClient(int port) throws SocketException
    {
        this.socket = new DatagramSocket();
        this.arg1 = (int) (Math.random() * 1000) ;
        this.arg2 = (int) (Math.random() * 1000) ;
        this.port = port ;
    }
    
    @Override
    public void run()
    {
        try{
            String x = arg1 + " + " + arg2;
            BUFFER = x.getBytes();
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            packet = new DatagramPacket(BUFFER , BUFFER.length,ip,port);
            printOutput(new String("Client send" + x));
            socket.send(packet);
            socket.receive(packet);
            String output = new String(packet.getData(),0,packet.getLength());
            printOutput(new String("receive " + x + "=" + output));
        }
        catch(IOException e)
        {
            System.out.println("UDP sending problem " + e.getMessage());
        }
    }
    
    synchronized private void printOutput(String output)
    {
    	writeServer.append(output + "\n");
    }
}
