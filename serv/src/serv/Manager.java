package serv;

import communicator.Packet;
import communicator.Packet.FlagType;
import communicator.PacketTypes;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import tableaccess.*;
import loginhandle.LoginRequest;
//import serv.Packet.FlagType;

import entities.Accounts;
import entities.CustomerTelephones;
import entities.Customers;
import entities.ItemStocks;
import entities.LargeorderClients;
import entities.MailorderClients;
import entities.OrderItems;
import entities.Orders;
import entities.PersonalSalesRepresentatives;
import entities.Users;
import entities.WalkinClients;
import loginhandle.LoginHandler;
import loginhandle.MD5FailedException;

public class Manager implements Runnable {
	
	Socket socket;
	Packet receivedPacket;

	public Manager(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	
        public void run1(){
        ObjectInputStream in = null;
        try {
            System.out.println("waiting..");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("between");
            receivedPacket = (Packet) in.readObject();
            System.out.println("object read.");
            System.out.println(receivedPacket.getUserID());
            System.out.println(receivedPacket.getFlag());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
                }
	public void run() {
		// TODO Auto-generated method stub
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			receivedPacket = (Packet) in.readObject();
            this.handleReceivedPacket(receivedPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void handleReceivedPacket(Packet receivedPacket) {
		PacketTypes request = receivedPacket.getPacketType();
		
		if (request == PacketTypes.LOGIN &&
				!LoginHandler.getInstance().isLoggedIn(receivedPacket.getUserID())) {
			login();
		}
		/*
		if (!LoginHandler.getInstance().isLoggedIn(receivedPacket.getUserID())) {
			sendError(FlagType.NOT_LOGGED_IN);
			return;
		}
		*/
		switch(request) {
			
		case LOGOUT:
			 logout();
			break;
			
		case ADD_USER:
			createUser();
			break;
			
		case CREATE_CUSTOMER:
			createCustomer();
			break;
			
		case DELETE_USER:
			deleteUser();
			break;
			
		case UPDATE_PASSWORD:
			updatePassword();
			break;
			
		case DELETE_CUSTOMER:
			deleteCustomer();
			break;
			
		case UPDATE_CUSTOMER:
			updateCustomer();
			break;
			
		case VIEW_CUSTOMER:
			viewCustomer();
			break;
			
		case CREATE_ACCOUNT:
			createAccount();
			break;

		case VIEW_ACCOUNT:
			viewAccount();
			break;
			
		case UPDATE_ACCOUNT:
			updateAccount();
			break;
			
		case CREATE_LO_CUSTOMER:
			createLOCustomer();
			break;
			
		case CREATE_MO_CUSTOMER:
			createMOCustomer();
			break;
			
		case CREATE_WALKIN_CUSTOMER:
			createWalkinCustomer();
			break;
			
		case VIEW_LO_CUSTOMER:
			viewLOCustomer();
			break;
			
		case VIEW_MO_CUSTOMER:
			viewMOCustomer();
			break;
			
		case VIEW_WALKIN_CUSTOMER:
			viewWalkinCustomer();
			break;
			
		case UPDATE_LO_CUSTOMER:
			updateLOCustomer();
			break;
			
		case UPDATE_MO_CUSTOMER:
			updateMOCustomer();
			break;
			
		case UPDATE_WALKIN_CUSTOMER:
			updateWalkinCustomer();
			break;
			
		case DELETE_LO_CUSTOMER:
			deleteLOCustomer();
			break;
			
		case DELETE_MO_CUSTOMER:
			deleteMOCustomer();
			break;
			
		case DELETE_WALKIN_CUSTOMER:
			deleteWalkinCustomer();
			break;
			
		case CREATE_SALES_REP:
			createSalesRep();
			break;
			
		case VIEW_SALES_REP:
			viewSalesRep();
			break;
			
		case UPDATE_SALES_REP:
			updateSalesRep();
			break;
			
		case DELETE_SALES_REP:
			deleteSalesRep();
			break;
			
		case CREATE_TELELPHONE:
			createTelephone();
			break;
			
		case VIEW_TELELPHONE:
			viewTelephone();
			break;
			
		case UPDATE_TELELPHONE:
			updateTelephone();
			break;
			
		case DELETE_TELELPHONE:
			deleteTelephone();
			break;
		
		case CREATE_STOCK_ITEM:
			createStockItem();
			break;
			
		case VIEW_STOCK_ITEM:
			viewStockItem();
			break;
			
		case UPDATE_STOCK_ITEM:
			updateStockItem();
			break;
			
		case DELETE_STOCK_ITEM:
			deleteStockItem();
			break;
			
		case CREATE_ORDER:
			createOrder();
			break;
			
		case VIEW_ORDER:
			viewOrder();
			break;
			
		case UPDATE_ORDER:
			updateOrder();
			break;
			
		case DELETE_ORDER:
			deleteOrder();
			break;
			
		case CREATE_ORDER_ITEM:
			createOrderItem();
			break;
			
		case VIEW_ORDER_ITEM:
			viewOrderItem();
			break;
			
		case UPDATE_ORDER_ITEM:
			updateOrderItem();
			break;
			
		case DELETE_ORDER_ITEM:
			deleteOrderItem();
			break;
                    
                case GET_ITEM_LIST:
                        getItemList();
                        break;
                    
                case GET_MAIL_ORDER_LIST:
                        getMailOrderList();
                        break;
                case GET_LARGE_ORDER_LIST:
                        getLargeOrderList();
                        break;
                    
                case GET_WALIKIN_LIST:
                        getWalkinList();
                        break;
                 case GET_STOCK_LIST:
                        getItemList();
                        break;            
                    
		}
		
	}
        
        private void getItemList(){
            
                //OrderItems temp  = (OrderItems) receivedPacket.getMessage();
		//Order_itemsTableAccess.getConnection().remove(temp.getOrderId(),temp.getItemStockId() );
                sendPacket(Item_stocksTableAccess.getConnection().getItemList());
            
        }
        
        private void getMailOrderList(){
            
                //OrderItems temp  = (OrderItems) receivedPacket.getMessage();
		//Order_itemsTableAccess.getConnection().remove(temp.getOrderId(),temp.getItemStockId() );
                sendPacket(CustomerTableAccess.getConnection().getMailOrderList());
            
        }
        
        private void getLargeOrderList(){
            
                //OrderItems temp  = (OrderItems) receivedPacket.getMessage();
		//Order_itemsTableAccess.getConnection().remove(temp.getOrderId(),temp.getItemStockId() );
                sendPacket(CustomerTableAccess.getConnection().getLargeOrderList());
            
        }
        
        
        private void getWalkinList(){
            
                //OrderItems temp  = (OrderItems) receivedPacket.getMessage();
		//Order_itemsTableAccess.getConnection().remove(temp.getOrderId(),temp.getItemStockId() );
                sendPacket(CustomerTableAccess.getConnection().getWalkinList());
            
        }
	
	
	
	private void deleteOrderItem() {
		OrderItems temp  = (OrderItems) receivedPacket.getMessage();
		Order_itemsTableAccess.getConnection().remove(temp.getOrderId(),temp.getItemStockId() );		
	}

	private void updateOrderItem() {
		OrderItems temp = (OrderItems) receivedPacket.getMessage();
		Order_itemsTableAccess.getConnection().update(temp);				
	}

	private void viewOrderItem() {
		int id  = (Integer) receivedPacket.getMessage();
		sendPacket(Order_itemsTableAccess.getConnection().get(id));					
	}

	private void createOrderItem() {
		OrderItems temp = (OrderItems) receivedPacket.getMessage();
		Order_itemsTableAccess.getConnection().addOrderItem(temp);			
	}

	private void deleteOrder() {
		int id  = (Integer) receivedPacket.getMessage();
		OrdersTableAccess.getConnection().remove(id);			
	}

	private void updateOrder() {
		Orders temp = (Orders) receivedPacket.getMessage();
		OrdersTableAccess.getConnection().update(temp);		
	}

	private void viewOrder() {
		int id  = (Integer) receivedPacket.getMessage();
		sendPacket(OrdersTableAccess.getConnection().get(id));			
	}

	private void createOrder() {
		Orders temp = (Orders) receivedPacket.getMessage();
		int cusid=OrdersTableAccess.getConnection().addOrder(temp);
                temp.setId(cusid);
                Order_itemsTableAccess.getConnection().addOrderItemList(temp.getOrderList());
	}

	private void deleteStockItem() {
		int id  = (Integer) receivedPacket.getMessage();
		Item_stocksTableAccess.getConnection().remove(id);		
	}

	private void updateStockItem() {
		ItemStocks temp = (ItemStocks) receivedPacket.getMessage();
		Item_stocksTableAccess.getConnection().update(temp);			
	}

	private void viewStockItem() {
		int id  = (Integer) receivedPacket.getMessage();
		sendPacket(Item_stocksTableAccess.getConnection().get(id));			
	}

	private void createStockItem() {
		ItemStocks temp = (ItemStocks) receivedPacket.getMessage();
		Item_stocksTableAccess.getConnection().addAccount(temp);					
	}

	private void deleteTelephone() {
		CustomerTelephones temp  = (CustomerTelephones) receivedPacket.getMessage();
		Customer_telephoneTableAccess.getConnection().remove(temp.getCustomerid(), temp.getNumber());		
	}

	private void updateTelephone() {
		CustomerTelephones temp = (CustomerTelephones) receivedPacket.getMessage();
		Customer_telephoneTableAccess.getConnection().update(temp);				
	}

	private void viewTelephone() {
		int id  = (Integer) receivedPacket.getMessage();
		sendPacket(Customer_telephoneTableAccess.getConnection().get(id));	
	}

	private void createTelephone() {
		CustomerTelephones temp = (CustomerTelephones) receivedPacket.getMessage();
		Customer_telephoneTableAccess.getConnection().add(temp);			
	}

	private void deleteSalesRep() {
		int id  = (Integer) receivedPacket.getMessage();
		Personal_sales_representativesTableAccess.getConnection().remove(id);		
	}

	private void updateSalesRep() {
		PersonalSalesRepresentatives temp = (PersonalSalesRepresentatives) receivedPacket.getMessage();
		Personal_sales_representativesTableAccess.getConnection().update(temp);				
	}

	private void viewSalesRep() {
		int id  = (Integer) receivedPacket.getMessage();
		sendPacket(Personal_sales_representativesTableAccess.getConnection().get(id));		
	}

	private void createSalesRep() {
		PersonalSalesRepresentatives temp = (PersonalSalesRepresentatives) receivedPacket.getMessage();
		Personal_sales_representativesTableAccess.getConnection().addAccount(temp);		
	}

	private void deleteWalkinCustomer() {
		int id  = (Integer) receivedPacket.getMessage();
		Walkin_clientsTableAccess.getConnection().remove(id);		
	}

	private void deleteMOCustomer() {
		int id  = (Integer) receivedPacket.getMessage();
		Mailorder_clientsTableAccess.getConnection().remove(id);		
	}

	private void deleteLOCustomer() {
		int id  = (Integer) receivedPacket.getMessage();
		Largeorder_clientsTableAccess.getConnection().remove(id);
	}

	private void updateWalkinCustomer() {
		WalkinClients temp = (WalkinClients) receivedPacket.getMessage();
		Walkin_clientsTableAccess.getConnection().update(temp);
	}

	private void updateMOCustomer() {
		MailorderClients temp = (MailorderClients) receivedPacket.getMessage();
		Mailorder_clientsTableAccess.getConnection().update(temp);		
	}

	private void updateLOCustomer() {
		LargeorderClients temp = (LargeorderClients) receivedPacket.getMessage();
		Largeorder_clientsTableAccess.getConnection().update(temp);		
	}

	private void viewWalkinCustomer() {
		int id  = (Integer) receivedPacket.getMessage();
		sendPacket(Walkin_clientsTableAccess.getConnection().get(id));		
	}

	private void viewMOCustomer() {
		int id  = (Integer) receivedPacket.getMessage();
		sendPacket(Mailorder_clientsTableAccess.getConnection().get(id));
	}

	private void viewLOCustomer() {
		int id  = (Integer) receivedPacket.getMessage();
		sendPacket(Largeorder_clientsTableAccess.getConnection().get(id));
	}

	private void createWalkinCustomer() {
		WalkinClients temp = (WalkinClients) receivedPacket.getMessage();
		Walkin_clientsTableAccess.getConnection().addAccount(temp);
	}

	private void createMOCustomer() {
		MailorderClients temp = (MailorderClients) receivedPacket.getMessage();
		Mailorder_clientsTableAccess.getConnection().addAccount(temp);
	}

	private void createLOCustomer() {
		LargeorderClients temp = (LargeorderClients) receivedPacket.getMessage();
		Largeorder_clientsTableAccess.getConnection().addAccount(temp);
	}

	private void updateAccount() {
		Accounts temp = (Accounts) receivedPacket.getMessage();
		AccountsTableAccess.getConnection().update(temp);
	}

	private void viewAccount() {
		
		int id = ((Integer) receivedPacket.getMessage());
		int accountID = CustomerTableAccess.getConnection().get(id).getId();
		Accounts temp = AccountsTableAccess.getConnection().get(accountID);
		sendPacket(temp);
	}

	private void createAccount() {
		
		Accounts temp = (Accounts) receivedPacket.getMessage();
		AccountsTableAccess.getConnection().addAccount(temp);
	}
	private void viewCustomer() {
		
		int id = ((Customers) receivedPacket.getMessage()).getId();
		Customers temp = CustomerTableAccess.getConnection().get(id);
		sendPacket(temp);
	}

	private void updateCustomer() {
		
		Customers temp = (Customers) receivedPacket.getMessage();
		CustomerTableAccess.getConnection().update(temp);
	}

	private void deleteCustomer() {
		
		int id = (Integer) receivedPacket.getMessage();
		CustomerTableAccess.getConnection().remove(id);
		AccountsTableAccess.getConnection().remove(id);
	}

	private boolean isAdminCall() {
		String requesterID = receivedPacket.getUserID();
		Users requester = UsersTableAccess.getConnection().get(requesterID);
		
		return requester.getAccessLevel() == Users.ADMIN;
	}
	
	private void updatePassword() {
		
		if (isAdminCall()) {
			String userID = ((Users) receivedPacket.getMessage()).getUsername();
			String password = ((Users) receivedPacket.getMessage()).getPassword();
			
			Users temp = UsersTableAccess.getConnection().get(userID);
			try {
				temp.setPassword(LoginHandler.getInstance().getMD5(password));
				UsersTableAccess.getConnection().update(temp);
				
			} catch (MD5FailedException e) {
				sendError(FlagType.NO_SUCH_USER);
			}
		} else {
			sendError(FlagType.NO_PERMISSION);
		}
		
	}

	private void deleteUser() {
		
		if (isAdminCall()) {
			String userID = (String) receivedPacket.getMessage();
			UsersTableAccess.getConnection().remove(userID);
			LoginHandler.getInstance().logout(userID);
		} else {
			sendError(FlagType.NO_PERMISSION);
		}
		
	}

	private void createCustomer() {
		
		String requesterID = receivedPacket.getUserID();
		int cusId;
                //if (LoginHandler.getInstance().isLoggedIn(requesterID)) {
			CustomerTableAccess.getConnection().addCustomer((Customers) receivedPacket.getMessage());
                        Customers cus=(Customers)receivedPacket.getMessage();
                        /*
                        switch(cus.getTypeID()){   WRITE THIS
                        
                            case 1: MailorderClients mo=new MailorderClients();
                                    mo.setCustomerId(cus.get);
                        
                        }*/
		//} else {
			//sendError(FlagType.NOT_LOGGED_IN);
		//}
	}

	private void createUser() {
		
		if (isAdminCall()) {
			Users request = (Users) receivedPacket.getMessage();
			registerUser(request.getUsername(), request.getPassword(), request.getAccessLevel());
		} else {
			sendError(FlagType.NO_PERMISSION);
		}		
	}

	private boolean registerUser(String userId, String password, int access) {
		try {
			String encodedPassword = LoginHandler.getInstance().getMD5(password);

			UsersTableAccess.getConnection().addAccount(new Users(userId,encodedPassword,access));

			return true;
			 
		} catch (MD5FailedException e) {
			return false;
		}
	}
	
	private void login() {
		String username = ((LoginRequest) receivedPacket.getMessage()).getUsername();
		String password = ((LoginRequest) receivedPacket.getMessage()).getPassword();
		boolean success = LoginHandler.getInstance().login(username, password);
		
		if (success) {
			Users temp = UsersTableAccess.getConnection().get(username);
			sendPacket(temp);
		} else {
			sendError(Packet.FlagType.NO_SUCH_USER);
		}
	}
	
	private void logout() {
		String username = (String) receivedPacket.getMessage();
		LoginHandler.getInstance().logout(username);
		sendPacket("Logged from the system");
	}

	private void sendError(FlagType error) {
		Packet temp = new Packet(error);
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
		    out.writeObject(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void sendPacket(Object message) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
