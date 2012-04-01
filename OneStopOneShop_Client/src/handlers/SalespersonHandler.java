/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import entities.PersonalSalesRepresentatives;
import communicator.ClientGateway;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import onestoponeshop_client.gui.EditSalespersonPage;
import onestoponeshop_client.gui.NewSalespersonPage;
import onestoponeshop_client.gui.controller.UIHandler;
import sun.misc.Cleaner;

/**
 *
 * @author User
 */
public class SalespersonHandler {
    
     private static SalespersonHandler instance = null;
    //private ClientGateway clientgateway;
    //private Customers customer;
    private UIHandler uihandler;
    
    protected SalespersonHandler() {
        //clientgateway = ClientGateway.getInstance();
        //customer = new Customers();
        uihandler = UIHandler.getInstance();
    }

    public static SalespersonHandler getSalespersonHandler() {
        if (instance == null) {
            instance = new SalespersonHandler();
        }
        return instance;
    }
    
    public void getEditSalespersonPage() {
        EditSalespersonPage esp = UIHandler.getInstance().getEsp();
        PersonalSalesRepresentatives psr = new PersonalSalesRepresentatives();
        psr.setId(esp.getID());
        psr.setName(esp.getName());
        psr.setTelephone(esp.getTelephone());
        try {
            ClientGateway.getInstance().updateSalesRep(psr);
        } catch (IOException ex) {
            Logger.getLogger(SalespersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<PersonalSalesRepresentatives> getSalespersonList(){
        ArrayList<PersonalSalesRepresentatives> psrs = new ArrayList<PersonalSalesRepresentatives>();
        try {
            psrs = ClientGateway.getInstance().getSalesRepList();
        } catch (IOException ex) {
            Logger.getLogger(SalespersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalespersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return psrs;
        
       
    }
    
    public void getNewSalespersonDetails(){
        
        NewSalespersonPage nsp= UIHandler.getInstance().getNsp();
        PersonalSalesRepresentatives sp=new PersonalSalesRepresentatives();
        sp.setId(nsp.getID());
        sp.setName(nsp.getName());
        sp.setTelephone(nsp.getTelephone());
        try {
            ClientGateway.getInstance().createSalesRep(sp);
        } catch (IOException ex) {
            Logger.getLogger(SalespersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PersonalSalesRepresentatives searchSalesperson(int id){
        PersonalSalesRepresentatives psr = new PersonalSalesRepresentatives();
        try {
            psr = ClientGateway.getInstance().viewSaleRep(id);
        } catch (IOException ex) {
            Logger.getLogger(SalespersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalespersonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return psr;
    }
    
}
