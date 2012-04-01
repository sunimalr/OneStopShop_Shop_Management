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
import entities.WalkinClients;

/**
 *
 * @author User
 */
public class Walkin_clientsTableAccess {
    
    
     
    private static Walkin_clientsTableAccess walkinClientTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public Walkin_clientsTableAccess() {
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS walkin_clients (customer_id INT NOT NULL, TRN char(16) NOT NULL)");
            System.out.println("Database and walkin_clients created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(WalkinClients walkinClient){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO walkin_clients(customer_id,TRN) "+"values(?,?)");
            pstmt.setInt(1, walkinClient.getCustomerId());
            pstmt.setString(2, walkinClient.getTrn());
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to walkin_clients table");
            }else{
                System.err.println("Adding to walikin_clients table failed.");
            }
            //LoginTableAccess.getConnection().add(walkinClient.getUserName(), walkinClient.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(Walkin_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(WalkinClients walkinClient){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            //remove(walkinClient.getCustomerId());
            pstmt = con.prepareStatement("UPDATE walkin_clients SET TRN=? WHERE customer_id=?");
            pstmt.setInt(2, walkinClient.getCustomerId());
            pstmt.setString(1, walkinClient.getTrn());
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(walkinClient.getIndexNo(), walkinClient.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(Walkin_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public WalkinClients get(int customer_id){
        makeConnection();
        PreparedStatement pStmt = null;
        WalkinClients walkinClient=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM walkin_clients WHERE customer_id=?");
            pStmt.setInt(1, customer_id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                walkinClient = new WalkinClients();
                walkinClient.setCustomerId(rs.getInt(1));
                walkinClient.setTrn(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Walkin_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return walkinClient;
    }

    public void remove(int customer_id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM walkin_clients WHERE customer_id='" + customer_id + "'");
            //Walkin_clientsTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(Walkin_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Walkin_clientsTableAccess getConnection(){
        if(walkinClientTableAccess == null){
            walkinClientTableAccess = new Walkin_clientsTableAccess();
        }
        return walkinClientTableAccess;
    }
    
    
}
