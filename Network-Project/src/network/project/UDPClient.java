/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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
    
    public UDPClient(int port,int clientNum) throws SocketException
    {
        this.socket = new DatagramSocket();
        this.arg1 = (int) (Math.random() * 1000) ;
        this.arg2 = (int) (Math.random() * 1000) ;
        this.port = port ;
        this.clientNum = clientNum ;
    }
    
    @Override
    public void run()
    {
        try{
            String x = arg1 + " + " + arg2;
            BUFFER = x.getBytes();
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            packet = new DatagramPacket(BUFFER , BUFFER.length,ip,port);
            socket.send(packet);
            socket.receive(packet);
            String output = new String(packet.getData(),0,packet.getLength());
            System.out.println("the answer of " + x + " is " +  output);
        }
        catch(IOException e)
        {
            System.out.println("UDP sending problem " + e.getMessage());
        }
    }
}
