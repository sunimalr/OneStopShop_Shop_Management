/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Server implements Runnable{

    private static final int PORT = 5000; // Change port
    ObjectInputStream in = null;
    ServerSocket serverSocket = null;

    Socket socket=null;


    public Server() throws IOException{

    }
    @Override
        public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
          //  try {
                try {
                    
                    socket = serverSocket.accept();
                } catch (IOException ex) {
                    System.out.println("Error occured while accepting the socket");
                    return;
                }

                System.out.println("Connection created, client IP: " + socket.getInetAddress());

                Manager manager = new Manager(socket);
                
//                ObjectInputStream i= new ObjectInputStream(socket.getInputStream());
//                    System.out.println((String)i.readObject());
                
                Thread t=new Thread(manager);
                t.start();
                System.out.println("Manager thread started and completed");
            }
//            //throw new UnsupportedOperationException("Not supported yet.");
//            catch (IOException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            }
       
        }
    


    


        //throw new UnsupportedOperationException("Not supported yet.");
    //}

}
