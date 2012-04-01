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
import entities.Types;
/**
 *
 * @author User
 */
public class TypesTableAccess {
    
    
    private static TypesTableAccess typeTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public TypesTableAccess() {
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS types ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(30) NOT NULL )");
            System.out.println("Database and types created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(Types type){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO types(name) "+"values(?)");
            pstmt.setString(2, type.getName());
           
             
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to types table");
            }else{
                System.err.println("Adding to types table failed.");
            }
            //LoginTableAccess.getConnection().add(type.getUserName(), type.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(TypesTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(Types type){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            remove(type.getId());
            pstmt = con.prepareStatement("INSERT INTO types(name) "+"values(?)");
            pstmt.setString(2, type.getName());
            
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(type.getIndexNo(), type.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(TypesTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Types get(int id){
        makeConnection();
        PreparedStatement pStmt = null;
        Types type=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM types WHERE id=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                type = new Types();
                type.setId(rs.getInt(1));
                type.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypesTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }

    public void remove(int id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM types WHERE id='" + id + "'");
            //TypesTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(TypesTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized TypesTableAccess getConnection(){
        if(typeTableAccess == null){
            typeTableAccess = new TypesTableAccess();
        }
        return typeTableAccess;
    }
    
}
