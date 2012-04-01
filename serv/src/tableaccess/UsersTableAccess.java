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
import java.util.logging.Level;
import java.sql.SQLException;
import java.util.logging.Logger;
import entities.Users;

/**
 *
 * @author User
 */
public class UsersTableAccess {
    
    
   
    private static UsersTableAccess userTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public UsersTableAccess() {
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (username varchar(16) NOT NULL PRIMARY KEY, password varchar(16) NOT NULL, access_level INT(1) NOT NULL)");
            System.out.println("Database and users created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(Users user){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO users(username,password,access_level) "+"values(?,?,?)");
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getAccessLevel());
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to users table");
            }else{
                System.err.println("Adding to users table failed.");
            }
            //LoginTableAccess.getConnection().add(user.getUserName(), user.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(UsersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Users user){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            remove(user.getUsername());
             pstmt = con.prepareStatement("UPDATE users SET password=?,access_level=? WHERE username =?");
            pstmt.setString(3, user.getUsername());
            pstmt.setString(1, user.getPassword());
            pstmt.setInt(2, user.getAccessLevel());

            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(user.getIndexNo(), user.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(UsersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Users get(String username){
        makeConnection();
        PreparedStatement pStmt = null;
        Users user=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM users WHERE id=?");
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                
                user = new Users();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setAccessLevel(rs.getInt(3));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void remove(String username){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM item_stocks WHERE id='" + username + "'");
            //UsersTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(UsersTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized UsersTableAccess getConnection(){
        if(userTableAccess == null){
            userTableAccess = new UsersTableAccess();
        }
        return userTableAccess;
    }
    
}
