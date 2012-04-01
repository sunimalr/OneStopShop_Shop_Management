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
import entities.PersonalSalesRepresentatives;
/**
 *
 * @author User
 */
public class Personal_sales_representativesTableAccess {
    
    
   
    private static Personal_sales_representativesTableAccess personalSalesRepresentativeTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public Personal_sales_representativesTableAccess() {
        con = null;
        pStatement = null;
        statement = null;
        this.url = DEFAULT_URL;
        
    }

    private void makeConnection() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            System.out.println("Connection: " + con);
            statement = con.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS group7_db");
            statement.executeUpdate("use group7_db");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS personal_sales_representatives(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(35) NOT NULL, telephone char(10) NOT NULL)");
            System.out.println("Database and order_items created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(PersonalSalesRepresentatives personalSalesRepresentative){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO personal_sales_representatives(id,name,telephone) "+"values(?,?,?)");
            pstmt.setInt(1, personalSalesRepresentative.getId());
            pstmt.setString(2, personalSalesRepresentative.getName());
            pstmt.setString(3, personalSalesRepresentative.getTelephone());
           
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to personal_sales_representatives table");
            }else{
                System.err.println("Adding to personal_sales_representatives table failed.");
            }
            //LoginTableAccess.getConnection().add(personalSalesRepresentative.getUserName(), personalSalesRepresentative.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(Personal_sales_representativesTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(PersonalSalesRepresentatives personalSalesRepresentative){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            //remove(personalSalesRepresentative.getId());
            pstmt = con.prepareStatement("UPDATE personal_sales_representatives SET name=?,telephone=? WHERE id=?");
            pstmt.setInt(3, personalSalesRepresentative.getId());
            pstmt.setString(1, personalSalesRepresentative.getName());
            pstmt.setString(2, personalSalesRepresentative.getTelephone());
            // pstmt.setBigDecimal(4, personalSalesRepresentative.getPrice);

            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(personalSalesRepresentative.getIndexNo(), personalSalesRepresentative.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(Personal_sales_representativesTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PersonalSalesRepresentatives get(int id){
        makeConnection();
        PreparedStatement pStmt = null;
        PersonalSalesRepresentatives personalSalesRepresentative=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM personal_sales_representatives WHERE id=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                personalSalesRepresentative = new PersonalSalesRepresentatives();
                personalSalesRepresentative.setId(rs.getInt(1));
                personalSalesRepresentative.setName(rs.getString(2));
                personalSalesRepresentative.setTelephone(rs.getString(3));
               
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Personal_sales_representativesTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personalSalesRepresentative;
    }

    public void remove(int id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM personal_sales_representatives WHERE id='" + id + "'");
            //Personal_sales_representativesTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(Personal_sales_representativesTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Personal_sales_representativesTableAccess getConnection(){
        if(personalSalesRepresentativeTableAccess == null){
            personalSalesRepresentativeTableAccess = new Personal_sales_representativesTableAccess();
        }
        return personalSalesRepresentativeTableAccess;
    }
    
    
}
