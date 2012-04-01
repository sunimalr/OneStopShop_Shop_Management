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
import entities.Accounts;

/**
 *
 * @author User
 */
public class AccountsTableAccess {
    
   
    private static AccountsTableAccess accountTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public AccountsTableAccess() {
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS accounts (customer_id INT NOT NULL PRIMARY KEY,balance decimal(7,2),credit_limit decimal(7,2))");
            System.out.println("Database and accounts created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(Accounts account){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO accounts(customer_id,balance,credit_limit) "+"values(?,?,?)");
            pstmt.setInt(1, account.getCustomerId());
            pstmt.setBigDecimal(2, account.getBalance());
             pstmt.setBigDecimal(3, account.getCreditLimit());
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to account table");
            }else{
                System.err.println("Adding to account table failed.");
            }
            //LoginTableAccess.getConnection().add(account.getUserName(), account.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(AccountsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Accounts account){
        makeConnection();
        PreparedStatement pStmt = null;
        try{
            //remove(account.getCustomerId());
            pStmt = con.prepareStatement("UPDATE accounts SET balance=?,credit_limit=? WHERE customer_id=?");
            
            pStmt.setBigDecimal(1, account.getBalance());
             pStmt.setBigDecimal(2, account.getCreditLimit());
             pStmt.setInt(3, account.getCustomerId());

            int done = pStmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pStmt.close();

            //LoginTableAccess.getConnection().add(account.getIndexNo(), account.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(AccountsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Accounts get(int id){
        makeConnection();
        PreparedStatement pStmt = null;
        Accounts account=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM accounts WHERE customer_id=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                account = new Accounts();
                //account.setId(rs.getInt(1));
                account.setCustomerId(rs.getInt(1));
                account.setBalance(rs.getBigDecimal(2));
                account.setCreditLimit(rs.getBigDecimal(3));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    public void remove(int id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM accounts WHERE customer_id='" + id + "'");
            //AccountsTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(AccountsTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized AccountsTableAccess getConnection(){
        if(accountTableAccess == null){
            accountTableAccess = new AccountsTableAccess();
        }
        return accountTableAccess;
    }
}
    

