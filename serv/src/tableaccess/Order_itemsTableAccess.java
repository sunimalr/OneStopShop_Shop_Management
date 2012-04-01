/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tableaccess;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.OrderItems;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class Order_itemsTableAccess {
    
    
   
    private static Order_itemsTableAccess orderItemTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public Order_itemsTableAccess() {
        con = null;
        pStatement = null;
        statement = null;
        this.url = DEFAULT_URL;
       
    }

    private void makeConnection() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            System.out.println("Connection: " + con);
            statement = con.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS group7_db");
            statement.executeUpdate("use group7_db");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS order_items ( order_id INT NOT NULL, item_stock_id INT NOT NULL, qty_amt int(7) NOT NULL, price decimal(7,2) NOT NULL)");
            System.out.println("Database and order_items created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addOrderItem(OrderItems orderItem){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO order_items(order_id,item_stock_id,qty_amt,price) "+"values(?,?,?,?)");
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getItemStockId());
             pstmt.setInt(3, orderItem.getQtyAmt());
            pstmt.setBigDecimal(4, orderItem.getPrice());
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to order_items table");
            }else{
                System.err.println("Adding to order_items table failed.");
            }
            //LoginTableAccess.getConnection().add(orderItem.getUserName(), orderItem.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(Order_itemsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void addOrderItemList(ArrayList<OrderItems> list){
        
        while(!list.isEmpty()){
        
            addOrderItem(list.remove(0));
                    
        }
        
        
    }
    
    public void update(OrderItems orderItem){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
           //remove(orderItem.getOrderId(),orderItem.getItemStockId());
             pstmt = con.prepareStatement("UPDATE order_items SET item_stock_id=?,qty_amt=?,price=? WHERE order_id=?");
            pstmt.setInt(4, orderItem.getOrderId());
            pstmt.setInt(1, orderItem.getItemStockId());
             pstmt.setInt(2, orderItem.getQtyAmt());
            pstmt.setBigDecimal(3, orderItem.getPrice());

            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(orderItem.getIndexNo(), orderItem.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(Order_itemsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public OrderItems get(int id){
        makeConnection();
        PreparedStatement pStmt = null;
        OrderItems orderItem=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM item_stocks WHERE order_id=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                orderItem = new OrderItems();
                orderItem.setOrderId(rs.getInt(1));
                orderItem.setItemStockId(rs.getInt(2));
                orderItem.setQtyAmt(rs.getInt(3));
                orderItem.setPrice(rs.getBigDecimal(4));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_itemsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderItem;
    }

    public void remove(int orderId, int itemStockId){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM order_items WHERE order_id='" + orderId + "' AND item_stock_id '"+ itemStockId + "'");
            //Order_itemsTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(Order_itemsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Order_itemsTableAccess getConnection(){
        if(orderItemTableAccess == null){
            orderItemTableAccess = new Order_itemsTableAccess();
        }
        return orderItemTableAccess;
    }
    
    
    
}
