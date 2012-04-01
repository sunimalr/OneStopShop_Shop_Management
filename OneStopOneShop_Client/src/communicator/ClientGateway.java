/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communicator;

import handlers.StockHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//import requestProtocol.LoginRequest;
//import serv.PacketTypes;
import entities.*;

/**
 *
 * @author User
 */
public class ClientGateway {
    
    //private Client myClient;
    private String currentUser="not_logged";
    private Socket socket;
    private static final String ip = "127.0.0.1";
    private static final int port = 5000;    
    private static ClientGateway clientGateway=null;

    private ClientGateway() throws IOException {
    	//socket = new Socket(ip, port);
    }
    
    public static synchronized ClientGateway getInstance() throws IOException{
        if(clientGateway == null){
            clientGateway = new ClientGateway();
        }
        return clientGateway;
    }
    
    public void test(){
        try {
            //socket=new Socket(ip, port);
            String str=new String("yut");
            Packet pk = new Packet(Packet.FlagType.NO_SUCH_USER);
            ObjectOutputStream oo=new ObjectOutputStream(socket.getOutputStream()) ;
            oo.writeObject(pk);
            oo.flush();;
            //Packet send = new Packet(PacketTypes.LOGIN,"testuser", "111");
            System.out.println("packet sent");
            socket.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientGateway.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public LoginRequest login(LoginRequest loginRequest) throws IOException{ // returns user type
        socket = new Socket(ip, port);
        LoginRequest loginReq=null;
        Packet send = new Packet(PacketTypes.LOGIN, this.currentUser, loginRequest);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            loginReq = (LoginRequest) in.readObject();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
        socket.close();
        return loginReq;
        
    }
    
    public void logout() throws IOException{ // returns user type
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.LOGOUT, this.currentUser, this.currentUser);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
        
        
    }
    
    public void addUser(Users user) throws IOException{ // returns user type
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.ADD_USER, this.currentUser, user);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
        
        
    }
    
    public void deleteUser(String user) throws IOException{ // returns user type
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.DELETE_USER, this.currentUser, user);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
        
        
    }
    
    public void updatePassword(Users user) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.UPDATE_PASSWORD, this.currentUser, user);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
        
        
    }
    
    
    public void createCustomer(Customers customer) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.CREATE_CUSTOMER, this.currentUser, customer);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public void deleteCustomer(int customer_id) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.DELETE_CUSTOMER, this.currentUser, customer_id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
     public void updateCustomer(Customers cus) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.UPDATE_CUSTOMER, this.currentUser, cus);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
     
    public Customers viewCustomer(int id) throws IOException{ 
        socket = new Socket(ip, port);
        Customers cus=null;
        Packet send = new Packet(PacketTypes.VIEW_CUSTOMER, this.currentUser, id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            cus = (Customers) in.readObject();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
        socket.close();
        return cus;
        
    }
     
    public void createAccount(Accounts account) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.CREATE_ACCOUNT, this.currentUser, account);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public Accounts viewAccount(int id) throws IOException{ //Revise
        socket = new Socket(ip, port);
        Accounts ac=null;
        Packet send = new Packet(PacketTypes.VIEW_ACCOUNT, this.currentUser, id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            ac = (Accounts) in.readObject();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
        socket.close();
        return ac;
        
    }
    
    public void updateAccount(Accounts account) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.UPDATE_ACCOUNT, this.currentUser, account);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    
    public void createLargeOrderCustomer(LargeorderClients customer) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.CREATE_LO_CUSTOMER, this.currentUser, customer);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public LargeorderClients viewLargeOrderCustomer(int customer_id) throws IOException{ 
        socket = new Socket(ip, port);
        LargeorderClients cus=null;
        Packet send = new Packet(PacketTypes.VIEW_LO_CUSTOMER, this.currentUser, customer_id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            cus = (LargeorderClients) in.readObject();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
        socket.close();
        return cus;
        
    }
    
    public void updateLargeOrderCustomer(LargeorderClients client) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.UPDATE_LO_CUSTOMER, this.currentUser, client);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public void deleteLargeOrderCustomer(int customer_id) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.DELETE_LO_CUSTOMER, this.currentUser, customer_id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public void createMailOrderCustomer(MailorderClients client) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.CREATE_MO_CUSTOMER, this.currentUser, client);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    
     public MailorderClients viewMailOrderCustomer(int customer_id) throws IOException{ 
        socket = new Socket(ip, port);
        MailorderClients cus=null;
        Packet send = new Packet(PacketTypes.VIEW_MO_CUSTOMER, this.currentUser, customer_id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            cus = (MailorderClients) in.readObject();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
        socket.close();
        return cus;
        
    }
     
     
     public void updateMailOrderCustomer(MailorderClients client) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.UPDATE_MO_CUSTOMER, this.currentUser, client);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public void deleteMailOrderCustomer(int customer_id) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.DELETE_MO_CUSTOMER, this.currentUser, customer_id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public void createWalkingCustomer(WalkinClients client) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.CREATE_WALKIN_CUSTOMER, this.currentUser, client);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public WalkinClients viewWalkingCustomer(int customer_id) throws IOException{ 
        socket = new Socket(ip, port);
        WalkinClients cus=null;
        Packet send = new Packet(PacketTypes.VIEW_WALKIN_CUSTOMER, this.currentUser, customer_id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            cus = (WalkinClients) in.readObject();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
        socket.close();
        return cus;
        
    }
    
    public void updateWalkingCustomer(WalkinClients client) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.UPDATE_WALKIN_CUSTOMER, this.currentUser, client);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public void deleteWalkingCustomer(int customer_id) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.DELETE_WALKIN_CUSTOMER, this.currentUser, customer_id);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    public void createSalesRep(PersonalSalesRepresentatives rep) throws IOException{ 
        socket = new Socket(ip, port);
        
        Packet send = new Packet(PacketTypes.CREATE_SALES_REP, this.currentUser, rep);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(send);
        socket.close();
     
    }
    
    
    
    private void sendPacket(Object data) throws IOException {
         //socket = new Socket(ip, port);
    	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(data);
        out.close();
        //socket.close();
    }
    
    private Object readPacket() throws IOException, ClassNotFoundException {
    	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

    	Object temp = in.readObject();
        in.close();
    	//socket.close();
    	return temp;
    }
    
    public PersonalSalesRepresentatives viewSaleRep(int salesRepID) throws IOException, ClassNotFoundException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.VIEW_SALES_REP, this.currentUser, salesRepID);
        sendPacket(send);
        PersonalSalesRepresentatives temp = (PersonalSalesRepresentatives) readPacket();
        socket.close();
        return temp; 
        
    }
    
    public void updateSalesRep(PersonalSalesRepresentatives salesRep) throws IOException { 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.UPDATE_SALES_REP, this.currentUser, salesRep);
        sendPacket(send);
         socket.close();
    }
    
    public void deleteSalesRep(int salesRepID) throws IOException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.DELETE_SALES_REP, this.currentUser, salesRepID);
        sendPacket(send);
         socket.close();
    }
    
    public void createTelephone(CustomerTelephones telephone) throws IOException{
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.CREATE_TELELPHONE, this.currentUser, telephone);
        sendPacket(send);
         socket.close();
     
    }
    
    public CustomerTelephones viewTelephone(int customerID) throws IOException, ClassNotFoundException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.VIEW_TELELPHONE, this.currentUser, customerID);
        sendPacket(send);
        CustomerTelephones temp = (CustomerTelephones) readPacket();
         socket.close();
        return temp; 
    }
    
    public void updateTelephone(CustomerTelephones salesRep) throws IOException { 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.UPDATE_TELELPHONE, this.currentUser, salesRep);
        sendPacket(send);
         socket.close();
    }
    
    public void deleteTelephone(int customerID) throws IOException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.DELETE_TELELPHONE, this.currentUser, customerID);
        sendPacket(send);
         socket.close();
    }
    
    public void createStockItem(ItemStocks item) throws IOException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.CREATE_STOCK_ITEM, this.currentUser, item);
        sendPacket(send);
         socket.close();
     
    }
    
    public ItemStocks viewStockItem(int itemID) throws IOException, ClassNotFoundException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.VIEW_STOCK_ITEM, this.currentUser, itemID);
        sendPacket(send);
        ItemStocks temp = (ItemStocks) readPacket();
        socket.close();
        return temp; 
    }
    
    public void updateStockItem(ItemStocks item) throws IOException { 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.UPDATE_STOCK_ITEM, this.currentUser, item);
        sendPacket(send);
         socket.close();
    }
    
    public void deleteStockItem(int itemID) throws IOException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.DELETE_STOCK_ITEM, this.currentUser, itemID);
        sendPacket(send);
        socket.close();
    }
    
    public void createOrder(Orders item) throws IOException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.CREATE_ORDER, this.currentUser, item);
        sendPacket(send);
        socket.close();
     
    }
    
    public Orders viewOrder(int orderID) throws IOException, ClassNotFoundException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.VIEW_ORDER, this.currentUser, orderID);
        sendPacket(send);
        Orders temp = (Orders) readPacket();
        socket.close();
        return temp; 
    }
    
    public void updateOrder(Orders order) throws IOException { 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.UPDATE_ORDER, this.currentUser, order);
        sendPacket(send);
        socket.close();
    }
    
    public void deleteOrder(int orderID) throws IOException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.DELETE_ORDER, this.currentUser, orderID);
        sendPacket(send);
        socket.close();
    }
    
    public void createOrderItem(OrderItems item) throws IOException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.CREATE_ORDER_ITEM, this.currentUser, item);
        sendPacket(send);
        socket.close();
    }
    
    public OrderItems viewOrderItem(int itemID) throws IOException, ClassNotFoundException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.VIEW_ORDER_ITEM, this.currentUser, itemID);
        sendPacket(send);
        socket.close();
        return (OrderItems) readPacket(); 
    }
    
    public void updateOrderItem(OrderItems item) throws IOException { 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.UPDATE_ORDER_ITEM, this.currentUser, item);
        sendPacket(send);
        socket.close();
    }
    
    public void deleteOrderItem(int itemID) throws IOException{ 
         socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.DELETE_ORDER_ITEM, this.currentUser, itemID);
        sendPacket(send);
        socket.close();
    }
    
    public ArrayList<Customers> getCustomerList() throws IOException, ClassNotFoundException {
    	 socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.GET_CUSTOMER_LIST, this.currentUser, null);
        sendPacket(send);
        ArrayList<Customers> temp = (ArrayList<Customers>) readPacket();
        socket.close();
        return temp;
    }
    
    public ArrayList<Orders> getOrderList() throws IOException, ClassNotFoundException {
    	 socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.GET_ORDER_LIST, this.currentUser, null);
        sendPacket(send);
        ArrayList<Orders> temp = (ArrayList<Orders>) readPacket();
        socket.close();
        return temp;
    }
    
    public ArrayList<ItemStocks> getStockList() throws IOException, ClassNotFoundException {
    	 socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.GET_STOCK_LIST, this.currentUser, null);
        sendPacket(send);
        ArrayList<ItemStocks> temp = (ArrayList<ItemStocks>) readPacket();
        socket.close();
        return temp;
    }
    
    public ArrayList<PersonalSalesRepresentatives> getSalesRepList() throws IOException, ClassNotFoundException {
    	 socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.GET_SALES_REP_LIST, this.currentUser, null);
        sendPacket(send);
        ArrayList<PersonalSalesRepresentatives> temp =(ArrayList<PersonalSalesRepresentatives>) readPacket();
         socket.close();
        return temp;
    }
    
    public ArrayList<Customers> getMailOrderCusList() throws IOException, ClassNotFoundException {
    	 socket = new Socket(ip, port);
        Packet send = new Packet(PacketTypes.GET_MAIL_ORDER_LIST, this.currentUser, null);
        sendPacket(send);
        ArrayList<Customers> temp = (ArrayList<Customers>) readPacket();
        socket.close();
        return temp;
    }
    
     public ArrayList<Customers> getLargeOrderCusList() throws IOException, ClassNotFoundException {
    	 socket = new Socket(ip, port);
         Packet send = new Packet(PacketTypes.GET_LARGE_ORDER_LIST, this.currentUser, null);
        sendPacket(send);
        ArrayList<Customers> temp = (ArrayList<Customers>) readPacket();
        socket.close();
        return temp;
    }
     
      public ArrayList<Customers> getWaliknCusList() throws IOException, ClassNotFoundException {
    	 socket = new Socket(ip, port);
          Packet send = new Packet(PacketTypes.GET_WALIKIN_LIST, this.currentUser, null);
        sendPacket(send);
        ArrayList<Customers> temp = (ArrayList<Customers>) readPacket();
        socket.close();
        return temp;
    }
}
