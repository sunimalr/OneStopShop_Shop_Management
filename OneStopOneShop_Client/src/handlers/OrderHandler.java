/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import entities.Orders;
import communicator.ClientGateway;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import onestoponeshop_client.gui.controller.UIHandler;
import onestoponeshop_client.gui.NewOrderPage;

/**
 *
 * @author Gaiz
 */
public class OrderHandler {
    
    
    private static OrderHandler instance = null;
    //private ClientGateway clientgateway;
    //private Customers customer;
    private UIHandler uihandler;
    
    protected OrderHandler() {
        //clientgateway = ClientGateway.getInstance();
        //customer = new Customers();
        uihandler = UIHandler.getInstance();
    }

    public static OrderHandler getOrderHandler() {
        if (instance == null) {
            instance = new OrderHandler();
        }
        return instance;
    }
    
    
    public ArrayList<Orders> getOrderList(){
        ArrayList<Orders> orderList = new ArrayList<Orders>();
        try {
            orderList = ClientGateway.getInstance().getOrderList();
        } catch (IOException ex) {
            Logger.getLogger(OrderHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
        
        
    }
    
    public void getNewOrderDetails(){
        
        Orders order=new Orders();
        NewOrderPage nop= UIHandler.getInstance().getNop();
        order.setOrderDate(nop.getDate());
        order.setId(nop.getID());
        order.setCustomerId(nop.getCustomerID());
        order.setTotalAmt(nop.getTotalAmt());
        try {
            ClientGateway.getInstance().createOrder(order);
        } catch (IOException ex) {
            Logger.getLogger(OrderHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Orders searchOrder(int ID){
        Orders order = new Orders();
        try {
            order = ClientGateway.getInstance().viewOrder(ID);
        } catch (IOException ex) {
            Logger.getLogger(OrderHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
}
