/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import serv.Server;
import tableaccess.*;

/**
 *
 * @author 090007j
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
           Server server=new Server();
           server.run();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
