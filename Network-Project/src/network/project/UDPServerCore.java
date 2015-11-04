/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author HackerGhost
 */
public class UDPServerCore implements Runnable {
    DatagramSocket socket = null ;
    DatagramPacket packet = null ;
    byte[] BUFFER = new byte[256];

    public UDPServerCore(DatagramSocket socket , DatagramPacket packet , 
            byte[] BUFFER) {
        this.socket = socket ;
        this.packet = packet ;
        this.BUFFER = BUFFER ;
    }
    
    
    
    public void run(){
        String x = new String(packet.getData(),0,packet.getLength());
        System.out.println("received thread = " + Thread.currentThread().getId()
        + " and data " + x );
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
    
    private static String parseString(String x )
    {
        String arr[] = x.split(" ");
        int z = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[2]);
        System.out.println("Parsing " + arr[0] + " " + z + arr[2] + " " + y
        + " id" + Thread.currentThread().getId());
        y = y + z;
        System.out.println(Thread.currentThread().getId() + " " + y);
        return String.valueOf(y);
    }
    
}
