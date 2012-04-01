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
import entities.LargeorderClients;

/**
 *
 * @author User
 */
public class Largeorder_clientsTableAccess {
    
    
    private static Largeorder_clientsTableAccess largeorderClientTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public Largeorder_clientsTableAccess() {
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS largeorder_clients (customer_id INT NOT NULL, personal_sales_representative_id INT NOT NULL, BRN char(16) NOT NULL)");
            System.out.println("Database and largeorder_clients created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(LargeorderClients largeorderClient){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO largeorder_clients(customer_id,personal_sales_representative_id,BRN) "+"values(?,?,?)");
            pstmt.setInt(1, largeorderClient.getCustomerId());
            pstmt.setInt(2, largeorderClient.getPersonalSalesRepresentativeId());
             pstmt.setString(3, largeorderClient.getBrn());
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to largeorder_clients table");
            }else{
                System.err.println("Adding to largeorder_clients table failed.");
            }
            //LoginTableAccess.getConnection().add(largeorderClient.getUserName(), largeorderClient.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(Largeorder_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(LargeorderClients largeorderClient){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            //remove(largeorderClient.getCustomerId());
             pstmt = con.prepareStatement("UPDATE largeorder_clients SET personal_sales_representative_id=?,BRN=? WHERE customer_id=?");
            pstmt.setInt(3, largeorderClient.getCustomerId());
            pstmt.setInt(1, largeorderClient.getPersonalSalesRepresentativeId());
             pstmt.setString(2, largeorderClient.getBrn());
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(largeorderClient.getIndexNo(), largeorderClient.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(Largeorder_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LargeorderClients get(int customer_id){
        makeConnection();
        PreparedStatement pStmt = null;
        LargeorderClients largeorderClient=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM largeorder_clients WHERE customer_id=?");
            pStmt.setInt(1, customer_id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                largeorderClient = new LargeorderClients();
                largeorderClient.setCustomerId(rs.getInt(1));
                largeorderClient.setPersonalSalesRepresentativeId(rs.getInt(2));
                largeorderClient.setBrn(rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Largeorder_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return largeorderClient;
    }

    public void remove(int customer_id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM largeorder_clients WHERE id='" + customer_id + "'");
            //Largeorder_clientsTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(Largeorder_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Largeorder_clientsTableAccess getConnection(){
        if(largeorderClientTableAccess == null){
            largeorderClientTableAccess = new Largeorder_clientsTableAccess();
        }
        return largeorderClientTableAccess;
    }
    
}
