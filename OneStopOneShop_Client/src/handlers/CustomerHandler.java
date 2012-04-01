/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package handlers;

import communicator.*;
import entities.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import onestoponeshop_client.gui.EditCustomerPage;
import onestoponeshop_client.gui.NewCustomerPage;
import onestoponeshop_client.gui.controller.UIHandler;

/**
 *
 * @author 090007J
 */
public class CustomerHandler { 
    private static CustomerHandler instance = null;
    //private ClientGateway clientgateway;
    //private Customers customer;
    private UIHandler uihandler;
    
    protected CustomerHandler() {
        //clientgateway = ClientGateway.getInstance();
        //customer = new Customers();
        uihandler = UIHandler.getInstance();
    }

    public static CustomerHandler getCustomerhandler() {
        if (instance == null) {
            instance = new CustomerHandler();
        }
        return instance;
    }
    
    public Customers searchCustomer(String text) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public Customers searchCustomer(int ID){
        Customers customer = new Customers();
        try{
            customer = ClientGateway.getInstance().viewCustomer(ID);
        }catch(IOException ex){}
//        customer.setId(123);
//        customer.setName("Gaiz");
//        customer.setAddressLine1("line1");
//        customer.setAddressLine2("line2");
//        customer.setPostalCode("1234");
//        customer.setCity("city");
//        customer.setCountry("SL");
        System.out.println(ID);
        //issue search query to client gateway
        
        return customer;
    }
//        public ArrayList<Customers> getListCustomersePage(){
//        ArrayList<Customers> customers = new ArrayList<Customers>();
//        System.out.println("list customers");
//        //get the list of customers from the client gateway
//        return customers;
//    }
    
    public ArrayList<Customers> getCustomerList(){
        ArrayList<Customers> customers = new ArrayList<Customers>();
        try {
            customers = ClientGateway.getInstance().getCustomerList();
        } catch (IOException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    public void getNewCustomerDetails(){
        
        Customers cus=new Customers();
        NewCustomerPage ncp = UIHandler.getInstance().getNcp();
        cus.setId(ncp.getCustID());
        cus.setName(ncp.getCustName());
        cus.setAddressLine1(ncp.getAddressLine1());
        cus.setAddressLine2(ncp.getAddressLine2());
        cus.setCity(ncp.getCity());
        cus.setPostalCode(ncp.getPostalCode());
        cus.setCountry(ncp.getCountry());
        try {
            ClientGateway.getInstance().createCustomer(cus);
        } catch (IOException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void getEditCustomerPage(){
        Customers customer = new Customers();
        EditCustomerPage ecp = uihandler.getEdc();
        customer.setName(ecp.getCustName());
        customer.setId(ecp.getCustID());
        customer.setAddressLine1(ecp.getAddressLine1());
        customer.setAddressLine2(ecp.getAddressLine2());
        customer.setCity(ecp.getCity());
        customer.setCountry(ecp.getCountry());
        System.out.println(ecp.getAddressLine2());
        //customer.setPostalCode(ecp.getPostalCode());
        //customer.setTypeId(ecp.getCustomerType());
        //customer.setGender(ecp.getGender());
        try{
            ClientGateway.getInstance().updateCustomer(customer);
        }catch(IOException ex){}
        
    }
    public void removeCustomer(int ID){
        try {
            ClientGateway.getInstance().deleteCustomer(ID);
            //remove customer method - client gateway
        } catch (IOException ex) {
            Logger.getLogger(CustomerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public Orders searchOrder(int ID) throws IOException, ClassNotFoundException{
         return ClientGateway.getInstance().viewOrder(ID);
    }
}
