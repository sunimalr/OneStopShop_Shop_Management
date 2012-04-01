/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import entities.Admin;
import onestoponeshop_client.gui.NewAdminPage;
import onestoponeshop_client.gui.controller.UIHandler;

/**
 *
 * @author Gaiz
 */
public class UserMgmtHandler {
    private static UserMgmtHandler umHandler = null;
    
    public static synchronized UserMgmtHandler getUserMgmtHandler(){
        if(umHandler==null){
            umHandler = new UserMgmtHandler();
        }
        return umHandler;
    }

    public void getNewAdminDetails() {
        Admin admin = new Admin();
        NewAdminPage nap = UIHandler.getInstance().getNap();
        admin.setId(nap.getID());
        admin.setName(nap.getAdminName());
        admin.setTelephone(nap.getTelephone());
        System.out.println(admin.getName());
        UIHandler.getInstance().getUserMgmtPage();
    }
}
