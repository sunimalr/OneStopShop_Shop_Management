/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import entities.ItemStocks;
import communicator.ClientGateway;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import onestoponeshop_client.gui.NewItemPage;
import onestoponeshop_client.gui.controller.UIHandler;

/**
 *
 * @author Gaiz
 */
public class StockHandler {
    
    private static StockHandler instance = null;
    //private ClientGateway clientgateway;
    //private Customers customer;
    private UIHandler uihandler;
    
    protected StockHandler() {
        //clientgateway = ClientGateway.getInstance();
        //customer = new Customers();
        uihandler = UIHandler.getInstance();
    }

    public static StockHandler getStockHandler() {
        if (instance == null) {
            instance = new StockHandler();
        }
        return instance;
    }
    
    public ArrayList<ItemStocks> getStockList(){
        ArrayList<ItemStocks> items = new ArrayList<ItemStocks>();
        try {
            items = ClientGateway.getInstance().getStockList();
        } catch (IOException ex) {
            Logger.getLogger(StockHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public void getNewItemDetails(){
        
        NewItemPage nip= UIHandler.getInstance().getNip();
        ItemStocks is=new ItemStocks();
        is.setId(nip.getID());
        is.setItemType(nip.getItemType());
        is.setRemainingStock(nip.getStock());
        is.setUnitPrice(nip.getUnitPrice());
        try {
            ClientGateway.getInstance().createStockItem(is);
        } catch (IOException ex) {
            Logger.getLogger(StockHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ItemStocks searchItem(int id){
        ItemStocks item = new ItemStocks();
        try {
            item = ClientGateway.getInstance().viewStockItem(id);
        } catch (IOException ex) {
            Logger.getLogger(StockHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    public void searchItem(String text) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
