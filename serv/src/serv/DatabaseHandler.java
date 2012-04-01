/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import tableaccess.AccountsTableAccess;


/**
 *
 * @author User
 */
public class DatabaseHandler {
    
    java.sql.Connection con;
    java.sql.Statement stm;
    java.sql.Statement usercheck;
      
    
    String url;
    
    private static DatabaseHandler databaseHandler=null;
	
    private DatabaseHandler(){
    
        
        
    }
    
    public static DatabaseHandler getDatabaseHandler() {
      if(databaseHandler == null) {
         databaseHandler = new DatabaseHandler();
      }
      return databaseHandler;
   }

    
    
    
    public void initConnection(){
        url = "jdbc:mysql://localhost:3306/student";
        try {
            con = DriverManager.getConnection(url, "root", "");
            stm = con.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void closeConnection() throws SQLException{
        con.close();
               
    }
    
    public void updateCustomer(){
    
             
        
    }
    
    
    
    
    
    
}
