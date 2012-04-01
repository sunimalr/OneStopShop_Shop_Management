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
import entities.Orders;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class OrdersTableAccess {
    
    
    
   
    private static OrdersTableAccess orderTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public OrdersTableAccess() {
        con = null;
        pStatement = null;
        statement = null;
        this.url = DEFAULT_URL;
        //makeConnection();
    }

    private void makeConnection() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            System.out.println("Connection: " + con);
            statement = con.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS group7_db");
            statement.executeUpdate("use group7_db");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, customer_id INT NOT NULL, total_amt decimal(7,2) NOT NULL, order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            System.out.println("Database and orders created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public int addOrder(Orders order){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO orders(customer_id,total_amt) "+"values(?,?)");
            //pstmt.setInt(1, order.getId());
            pstmt.setInt(1, order.getCustomerId());
             pstmt.setBigDecimal(2, order.getTotalAmt());
             //Timestamp not included
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to item_stock table");
            }else{
                System.err.println("Adding to order table failed.");
            }
            
//            try {
//            PreparedStatement pStmt = null;
//            pStmt = con.prepareStatement("SELECT id FROM orders WHERE customer_id=?,total_amt=?");
//            pStmt.setInt(1, order.getCustomerId());
//            rs = pStmt.executeQuery();
//            
//            while(rs.next()){
//                order = new Orders();
//                order.setId(rs.getInt(1));
//                order.setCustomerId(rs.getInt(2));
//                order.setTotalAmt(rs.getBigDecimal(3));
//                order.setOrderDate(rs.getTimestamp(4));
//                
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(OrdersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
//        }
            //LoginTableAccess.getConnection().add(order.getUserName(), order.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(OrdersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pstmt.RETURN_GENERATED_KEYS;
    }
    
    public void update(Orders order){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            //remove(order.getId());
             pstmt = con.prepareStatement("UPDATE orders SET customer_id=?,total_amt=? WHERE id=?");
            pstmt.setInt(3, order.getId());
            pstmt.setInt(1, order.getCustomerId());
             pstmt.setBigDecimal(2, order.getTotalAmt());
             
             //Timestamp not included

            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(order.getIndexNo(), order.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(OrdersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Orders get(int id){
        makeConnection();
        PreparedStatement pStmt = null;
        Orders order=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM orders WHERE id=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                order = new Orders();
                order.setId(rs.getInt(1));
                order.setCustomerId(rs.getInt(2));
                order.setTotalAmt(rs.getBigDecimal(3));
                order.setOrderDate(rs.getTimestamp(4));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
    
    

    public void remove(int id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM orders WHERE id='" + id + "'");
            //OrdersTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(OrdersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized OrdersTableAccess getConnection(){
        if(orderTableAccess == null){
            orderTableAccess = new OrdersTableAccess();
        }
        return orderTableAccess;
    }
    
    
    
}
