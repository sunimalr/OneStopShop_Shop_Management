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
import entities.ItemStocks;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class Item_stocksTableAccess {
    
    
   
    private static Item_stocksTableAccess itemStockTableAccess = null;
    private Connection con;
    private PreparedStatement pStatement;
    private Statement statement;
    private ResultSet resultSet;
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/group7_db";
    private String url;

    public Item_stocksTableAccess() {
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS item_stocks (	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, item_type varchar(30) NOT NULL, remaining_stock decimal(7,2) NOT NULL, unit_price decimal(7,2) NOT NULL)");
            System.out.println("Database and item_stocks created.");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(ItemStocks itemStock){
        makeConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement("INSERT INTO item_stocks(item_type,remaining_stock,unit_price) "+"values(?,?,?)");
            //pstmt.setInt(1, itemStock.getId());
            pstmt.setString(1, itemStock.getItemType());
             pstmt.setBigDecimal(2, itemStock.getRemainingStock());
             pstmt.setBigDecimal(3, itemStock.getUnitPrice());
            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Successfully added to item_stock table");
            }else{
                System.err.println("Adding to itemStock table failed.");
            }
            //LoginTableAccess.getConnection().add(itemStock.getUserName(), itemStock.getPassword(),true);
        } catch (SQLException ex) {
            Logger.getLogger(Item_stocksTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(ItemStocks itemStock){
        makeConnection();
        PreparedStatement pstmt = null;
        try{
            //remove(itemStock.getId());
             pstmt = con.prepareStatement("UPDATE item_stocks SET item_type=?,remaining_stock=?,unit_price=? WHERE id=?");
            pstmt.setInt(4, itemStock.getId());
            pstmt.setString(1, itemStock.getItemType());
             pstmt.setBigDecimal(2, itemStock.getRemainingStock());
             pstmt.setBigDecimal(3, itemStock.getUnitPrice());

            int done = pstmt.executeUpdate();
            if(done>0){
                System.out.println("Update successfull.");
            }else{
                System.err.println("Update failed.");
            }

            pstmt.close();

            //LoginTableAccess.getConnection().add(itemStock.getIndexNo(), itemStock.getPassword(),false);
        }catch(SQLException ex) {
            Logger.getLogger(Item_stocksTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ItemStocks get(int id){
        makeConnection();
        PreparedStatement pStmt = null;
        ItemStocks itemStock=null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement("SELECT * FROM item_stocks WHERE id=?");
            pStmt.setInt(1, id);
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                itemStock = new ItemStocks();
                itemStock.setId(rs.getInt(1));
                itemStock.setItemType(rs.getString(2));
                itemStock.setRemainingStock(rs.getBigDecimal(3));
                itemStock.setUnitPrice(rs.getBigDecimal(4));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Item_stocksTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemStock;
    }
    
    
    public ArrayList<ItemStocks> getItemList(){
        makeConnection();
        PreparedStatement pStmt = null;
        ItemStocks itemStock=null;
        ResultSet rs = null;
        ArrayList<ItemStocks> itemarray=new ArrayList<ItemStocks>();
        try {
            pStmt = con.prepareStatement("SELECT * FROM item_stocks ");
            
            rs = pStmt.executeQuery();
            
            while(rs.next()){
                itemStock = new ItemStocks();
                itemStock.setId(rs.getInt(1));
                itemStock.setItemType(rs.getString(2));
                itemStock.setRemainingStock(rs.getBigDecimal(3));
                itemStock.setUnitPrice(rs.getBigDecimal(4));
                itemarray.add(itemStock);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Item_stocksTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemarray;
    }

    public void remove(int id){
        makeConnection();
        Statement stmt = null;
        try {
            statement = con.createStatement();
            statement.executeUpdate("DELETE FROM item_stocks WHERE id='" + id + "'");
            //Item_stocksTableAccess.getConnection().remove(id);
        } catch (SQLException ex) {
            Logger.getLogger(Item_stocksTableAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Item_stocksTableAccess getConnection(){
        if(itemStockTableAccess == null){
            itemStockTableAccess = new Item_stocksTableAccess();
        }
        return itemStockTableAccess;
    }
    
}
