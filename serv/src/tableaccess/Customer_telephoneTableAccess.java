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
import entities.CustomerTelephones;
/**
 *
 * @author User
 */
public class Customer_telephoneTableAccess {
    
     
   
    private static Customer_telephoneTableAccess customerTelephoneTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public Customer_telephoneTableAccess() {
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS customer_telephones ( customer_id INT NOT NULL, `number` char(20) NOT NULL)");
            System.out.println("Database and customer_telephones created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void add(CustomerTelephones customerTelephone){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO customer_telephones(customer_id,`number`) "+"values(?,?)");
            pstmt.setInt(1, customerTelephone.getCustomerid());
            pstmt.setString(2, customerTelephone.getNumber());
            
            
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to customer_telephones table");
            }else{
                System.err.println("Adding to customer_telephones table failed.");
            }
            //LoginTableAccess.getConnection().add(customerTelephone.getUserName(), customerTelephone.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(Customer_telephoneTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(CustomerTelephones customerTelephone){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            //remove(customerTelephone.getCustomerid());
             pstmt = con.prepareStatement("UPDATE customer_telephones SET `number`=? WHERE customer_id=? ");
            pstmt.setInt(2, customerTelephone.getCustomerid());
            pstmt.setString(1, customerTelephone.getNumber());
             

            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(customerTelephone.getIndexNo(), customerTelephone.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(Customer_telephoneTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CustomerTelephones get(int id){
        makeConnection();
        PreparedStatement pStmt = null;
        CustomerTelephones customerTelephone=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM customer_telephones WHERE customer_id=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                customerTelephone = new CustomerTelephones();
                customerTelephone.setCustomerid(rs.getInt(1));
                customerTelephone.setNumber(rs.getString(2));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer_telephoneTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customerTelephone;
    }
    
    public void remove(int id, String no){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM customer_telephones WHERE customer_id='" + id + "' AND `number`='"+no+"'");
            
        } catch (SQLException ex) {
            Logger.getLogger(Customer_telephoneTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(int id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM customer_telephones WHERE customer_id='" + id + "'");
            
        } catch (SQLException ex) {
            Logger.getLogger(Customer_telephoneTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Customer_telephoneTableAccess getConnection(){
        if(customerTelephoneTableAccess == null){
            customerTelephoneTableAccess = new Customer_telephoneTableAccess();
        }
        return customerTelephoneTableAccess;
    }
    
    
    
}
