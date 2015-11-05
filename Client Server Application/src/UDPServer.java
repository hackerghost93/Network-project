/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextArea;

/**
 *
 * @author HackerGhost
 */
public class UDPServer extends Thread {
       
    DatagramSocket socket = null ;
    DatagramPacket packet = null ;
    byte[] BUFFER = new byte[256];
    ExecutorService executor = Executors.newFixedThreadPool(10);
 
    public UDPServer(int port,JTextArea writeServer	) throws SocketException{
        this.socket = new DatagramSocket(port);
        UDPServerCore.writeServer = writeServer ;
        writeServer.append("Establishing Connection \n" );
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try{
                packet = new DatagramPacket(BUFFER,BUFFER.length);
                socket.receive(packet);
                executor.execute(new UDPServerCore(socket,packet,BUFFER));
            }
            catch(IOException e)
            {
                System.out.println("UDP receiving packet problem "
                + e.getMessage());
            }
        }
    }
    
    /**
     * return void
     * terminate running threads
     */
    public void terminate()
    {
        executor.shutdown();
    }
    
}
