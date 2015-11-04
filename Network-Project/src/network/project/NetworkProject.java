/*
 * 
 * 
 * 
 */
package network.project;

import java.net.SocketException;

/**
 *
 * @author hackerghost
 */
public class NetworkProject {

    /**
     * @param args the command line arguments
     * @throws java.net.SocketException
     */
    public static void main(String[] args) throws SocketException {
        UDPServer server = new UDPServer(5732);
        server.start();
        for(int i = 0 ; i < 20 ; i++)
        {
            UDPClient client = new UDPClient(5732);
            client.start();
        }
    }
    
}
