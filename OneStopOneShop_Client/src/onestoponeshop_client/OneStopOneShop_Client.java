/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onestoponeshop_client;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import communicator.ClientGateway;
import entities.Customers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import onestoponeshop_client.gui.controller.UIHandler;

/**
 *
 * @author Gaiz
 */
public class OneStopOneShop_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            try{
                UIManager.setLookAndFeel(new NimbusLookAndFeel());
            }catch(Exception e){
                System.out.println("error");
            }
           // try {
                //HomePage hp = new HomePage();
                /*
                LoginPage lp = new LoginPage();
                CustomerPage cp = new CustomerPage();
                FrontDeskAdmin fap = new FrontDeskAdmin();
                FrontDeskSalesperson sp = new FrontDeskSalesperson();
                UserManagementPanel up = new UserManagementPanel();
                NewCustomerPage np = new NewCustomerPage();
                SearchCustomersPage scp = new SearchCustomersPage();
                OrderPage op = new OrderPage();
                StockPage stp = new StockPage();
                ListCustomersPage lcp = new ListCustomersPage();
                EditCustomerPage ecp = new EditCustomerPage();
                NewOrderPage nop = new NewOrderPage();
                
                Home hm = new Home();
                hm.addJpanel(lp);
                //hp.add(nop);
                //hp.pack();
                //hp.setVisible(true);
                 * 
                 */
                UIHandler uih = UIHandler.getInstance();
                uih.getAdminFD();
              //uih.getSalesFD();
                uih.getCustomerPage();
                //LoginPage lp = new LoginPage();
                //Home hm = new Home();
                //hm.addJpanel(lp);
            
//            Customers cus=new Customers();
//            cus.setName("TESTNAME");
//            cus.setAddressLine1("ddf");
//            cus.setAddressLine2("hjjhds");
//            cus.setCity("yuu");
//            cus.setCountry("Suiur");
//            cus.setPostalCode("11234");
//            
//                ClientGateway.getInstance().createCustomer(cus);
            
        //    } catch (IOException ex) {
          //      Logger.getLogger(OneStopOneShop_Client.class.getName()).log(Level.SEVERE, null, ex);
           // }
//        } catch (IOException ex) {
//            Logger.getLogger(OneStopOneShop_Client.class.getName()).log(Level.SEVERE, null, ex);
    }
}
