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
import entities.Customers;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CustomerTableAccess {
    
    
    private static CustomerTableAccess customerTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public CustomerTableAccess() {
        con = null;
        pStatement = null;
        statement = null;
        this.url = DEFAULT_URL;
        makeConnection();
    }

    private void makeConnection() {
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            System.out.println("Connection: " + con);
            statement = con.createStatement();
            //statement.executeUpdate("CREATE DATABASE IF NOT EXISTS group7_db");
            statement.executeUpdate("use group7_db");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS customers (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(50) NOT NULL, address_line_1 varchar(25) NOT NULL, address_line_2 varchar(25) NOT NULL, city varchar(20) NOT NULL, postal_code varchar(6), country varchar(20) NOT NULL, type_id INT NOT NULL)");
            System.out.println("Database and customers created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addCustomer(Customers customer){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //pstmt = con.prepareStatement("UPDATE customers (address_line_1,address_line_2,city,postal_code,country,type_id)"+"values(?,?,?,?,?,?,?) where id='"+customer.getId()+"'");
            pstmt = con.prepareStatement("INSERT INTO customers (name,address_line_1,address_line_2,city,postal_code,country,type_id)"+"values(?,?,?,?,?,?,?)");
            //pstmt.setInt(1, customer.getId());
            pstmt.setString(1, customer.getName());
             pstmt.setString(2, customer.getAddressLine1());
             pstmt.setString(3, customer.getAddressLine2());
             pstmt.setString(4, customer.getCity());
             pstmt.setString(5, customer.getPostalCode());
             pstmt.setString(6, customer.getCountry());
             pstmt.setInt(7, customer.getTypeID());
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to customer table");
            }else{
                System.err.println("Adding to customer table failed.");
            }
            //LoginTableAccess.getConnection().add(customer.getUserName(), customer.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Customers customer){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            //remove(customer.getId());
            // AUTO INCREMENT PROBLEM
            //"UPDATE movies SET title = ? WHERE year_made = ?";
           pstmt = con.prepareStatement("UPDATE customers SET name =?,address_line_1 = ?,address_line_2 =?,city=?,postal_code=?,country=?,type_id=? WHERE id= ?");
            //pstmt.setInt(1, customer.getId());
            pstmt.setString(1, customer.getName());
             pstmt.setString(2, customer.getAddressLine1());
             pstmt.setString(3, customer.getAddressLine2());
             pstmt.setString(4, customer.getCity());
             pstmt.setString(5, customer.getPostalCode());
             pstmt.setString(6, customer.getCountry());
             pstmt.setInt(7, customer.getTypeID());
            pstmt.setInt(8, customer.getId());


            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(customer.getIndexNo(), customer.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(CustomerTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Customers get(int id){ //Find using ID
        makeConnection();
        PreparedStatement pStmt = null;
        Customers customer=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM customers WHERE id=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                customer = new Customers();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setAddressLine1(rs.getString(3));
                customer.setAddressLine2(rs.getString(4));
                customer.setCity(rs.getString(5));
                customer.setPostalCode(rs.getString(6));
                customer.setCountry(rs.getString(7));
                customer.setTypeID(rs.getInt(8));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public ArrayList<Customers> getCustomerList(){
        makeConnection();
        PreparedStatement pStmt = null;
        Customers customer=null;
        ResultSet rs = null;
        ArrayList<Customers> cusList=new ArrayList<Customers>();
        
        try {
            pStmt = con.prepareStatement("SELECT * FROM customers");
            
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                customer = new Customers();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setAddressLine1(rs.getString(3));
                customer.setAddressLine2(rs.getString(4));
                customer.setCity(rs.getString(5));
                customer.setPostalCode(rs.getString(6));
                customer.setCountry(rs.getString(7));
                customer.setTypeID(rs.getInt(8));
                cusList.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cusList;
    }  
       
    
    public ArrayList<Customers> getMailOrderList(){ //Find using name
        makeConnection();
        ArrayList<Customers> cusList=new ArrayList<Customers>();
        PreparedStatement pStmt = null;
        Customers customer=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM customers WHERE type_id=?");
            pStmt.setInt(1, 2);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                customer = new Customers();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setAddressLine1(rs.getString(3));
                customer.setAddressLine2(rs.getString(4));
                customer.setCity(rs.getString(5));
                customer.setPostalCode(rs.getString(6));
                customer.setCountry(rs.getString(7));
                customer.setTypeID(rs.getInt(8));
                
                cusList.add(customer);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cusList;
    }
    
    public ArrayList<Customers> getLargeOrderList(){ //Find using name
        makeConnection();
        ArrayList<Customers> cusList=new ArrayList<Customers>();
        PreparedStatement pStmt = null;
        Customers customer=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM customers WHERE type_id=?");
            pStmt.setInt(1, 3);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                customer = new Customers();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setAddressLine1(rs.getString(3));
                customer.setAddressLine2(rs.getString(4));
                customer.setCity(rs.getString(5));
                customer.setPostalCode(rs.getString(6));
                customer.setCountry(rs.getString(7));
                customer.setTypeID(rs.getInt(8));
                
                cusList.add(customer);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cusList;
    }
    
    public ArrayList<Customers> getWalkinList(){ //Find using name
        makeConnection();
        ArrayList<Customers> cusList=new ArrayList<Customers>();
        PreparedStatement pStmt = null;
        Customers customer=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM customers WHERE type_id=?");
            pStmt.setInt(1, 1);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                customer = new Customers();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setAddressLine1(rs.getString(3));
                customer.setAddressLine2(rs.getString(4));
                customer.setCity(rs.getString(5));
                customer.setPostalCode(rs.getString(6));
                customer.setCountry(rs.getString(7));
                customer.setTypeID(rs.getInt(8));
                
                cusList.add(customer);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cusList;
    }

    public void remove(int id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM customers WHERE id='" + id + "'");
            //CustomerTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized CustomerTableAccess getConnection(){
        if(customerTableAccess == null){
            customerTableAccess = new CustomerTableAccess();
        }
        return customerTableAccess;
    }
}
