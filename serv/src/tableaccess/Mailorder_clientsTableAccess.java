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
import entities.MailorderClients;
/**
 *
 * @author User
 */
public class Mailorder_clientsTableAccess {
    
     
    private static Mailorder_clientsTableAccess mailorderCilentTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public Mailorder_clientsTableAccess() {
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS mailorder_clients (customer_id INT NOT NULL, TRN char(16) NOT NULL)");
            System.out.println("Database and mailorder_clients created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(MailorderClients mailorderCilent){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO mailorder_clients(customer_id,TRN) "+"values(?,?)");
            pstmt.setInt(1, mailorderCilent.getCustomerId());
            pstmt.setString(2, mailorderCilent.getTrn());
            
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to mailorder_clients table");
            }else{
                System.err.println("Adding to largeorder_clients table failed.");
            }
            //LoginTableAccess.getConnection().add(mailorderCilent.getUserName(), mailorderCilent.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(Mailorder_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(MailorderClients mailorderCilent){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
           // remove(mailorderCilent.getCustomerId());
            pstmt = con.prepareStatement("UPDATE mailorder_clients SET TRN=? WHERE customer_id=?");
            pstmt.setInt(2, mailorderCilent.getCustomerId());
            pstmt.setString(1, mailorderCilent.getTrn());
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(mailorderCilent.getIndexNo(), mailorderCilent.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(Mailorder_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MailorderClients get(int customer_id){
        makeConnection();
        PreparedStatement pStmt = null;
        MailorderClients mailorderCilent=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM mailorder_clients WHERE customer_id=?");
            pStmt.setInt(1, customer_id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                mailorderCilent = new MailorderClients();
                mailorderCilent.setCustomerId(rs.getInt(1));
                mailorderCilent.setTrn(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mailorder_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mailorderCilent;
    }

    public void remove(int customer_id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM largeorder_clients WHERE id='" + customer_id + "'");
            //Mailorder_clientsTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(Mailorder_clientsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Mailorder_clientsTableAccess getConnection(){
        if(mailorderCilentTableAccess == null){
            mailorderCilentTableAccess = new Mailorder_clientsTableAccess();
        }
        return mailorderCilentTableAccess;
    }
    
    
}
