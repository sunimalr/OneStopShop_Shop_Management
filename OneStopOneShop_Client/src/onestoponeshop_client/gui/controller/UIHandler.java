/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onestoponeshop_client.gui.controller;

import javax.swing.JPanel;
import onestoponeshop_client.gui.CustomerPage;
import onestoponeshop_client.gui.EditCustomerPage;
import onestoponeshop_client.gui.EditSalespersonPage;
import onestoponeshop_client.gui.FrontDeskAdmin;
import onestoponeshop_client.gui.FrontDeskSalesperson;
import onestoponeshop_client.gui.ListCustomersPage;
import onestoponeshop_client.gui.ListOrdersPage;
import onestoponeshop_client.gui.ListSalespersonsPage;
import onestoponeshop_client.gui.ListStockPage;
import onestoponeshop_client.gui.LoginPage;
import onestoponeshop_client.gui.NewAdminPage;
import onestoponeshop_client.gui.NewCustomerPage;
import onestoponeshop_client.gui.NewItemPage;
import onestoponeshop_client.gui.NewOrderPage;
import onestoponeshop_client.gui.NewSalespersonPage;
import onestoponeshop_client.gui.OrderPage;
import onestoponeshop_client.gui.RemoveCustomerPage;
import onestoponeshop_client.gui.RemoveSalespersonPage;
import onestoponeshop_client.gui.SearchCustomersPage;
import onestoponeshop_client.gui.SearchOrderPage;
import onestoponeshop_client.gui.SearchStockPage;
import onestoponeshop_client.gui.StockPage;
import onestoponeshop_client.gui.UserManagementPanel;
import onestoponeshop_client.gui.frames.HomePage;

/**
 *
 * @author Gaiz
 */
public class UIHandler implements UIHandlerInterface{
    private HomePage hp;
    private LoginPage lp;
    private FrontDeskAdmin fad;
    private FrontDeskSalesperson fds;
    private CustomerPage cp;
    private OrderPage op;
    private StockPage sp;
    private UserManagementPanel ump;
    private EditCustomerPage ecp;
    private EditSalespersonPage esp;
    private ListCustomersPage lcp;
    private ListOrdersPage lop;
    private ListSalespersonsPage lsp;
    private ListStockPage lstp;
    private NewAdminPage nap;
    private NewCustomerPage ncp;
    private NewItemPage nip;
    private NewOrderPage nop;
    private NewSalespersonPage nsp;
    private RemoveCustomerPage rcp;
    private RemoveSalespersonPage rsp;
    private SearchCustomersPage scp;
    private SearchOrderPage sop;
    private SearchStockPage ssp;
    
    private boolean userType;       //true = admin, false = salesperson
    
    private static UIHandler instance = null;
    
    public UIHandler() {
        hp = new HomePage();
        System.out.println("this is awful");
        //getLoginPage();
        userType = true;
        //getSalesFD();
        //getAdminFD();
        //getCustomerPage();
        //getOrderPage();
        //getStockPage();
        //getUserMgmtPage();
        //getNewCustomerPage();
        //hp.setVisible(true);
    }
    
    public static synchronized UIHandler getInstance(){
        if(instance == null){
            instance = new UIHandler();
        }
        return instance;
    }
    
    
    public void setUserType(boolean state){
        this.userType = state;
    }
    
    @Override 
    //<editor-fold defaultstate="collapsed" desc="Popup pages">
    public void getLoginPage() {
        lp = new LoginPage();
        hp.addPanel(lp);
    }
    
    @Override
    public void getSalesFD() {
        fds = new FrontDeskSalesperson();
        hp.addPanel(fds);
    }
    
    @Override
    public void getAdminFD() {
        fad = new FrontDeskAdmin();
        hp.addPanel(fad);
    }
    
    @Override
    public void getCustomerPage() {
        cp = new CustomerPage();
        if(userType==true){
            fad.addPanel(cp);
        }else{
            fds.addPanel(cp);
        }
    }
    
    @Override
    public void getOrderPage() {
        op = new OrderPage();
        if(userType==true){
            fad.addPanel(op);
        }else{
            fds.addPanel(op);
        }
    }
    
    @Override
    public void getStockPage() {
        sp = new StockPage();
        if(userType==true){
            fad.addPanel(sp);
        }else{
            fds.addPanel(sp);
        }
    }
    
    @Override
    public void getSalespersonPage() {
    }
    
    @Override
    public void getAdministratorPage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void getUserMgmtPage() {
        ump = new UserManagementPanel();
        fad.addPanel(ump);
    }
    
    @Override
    public void getNewCustomerPage() {
        ncp = new NewCustomerPage();
        if(userType==true){
            fad.addPanel(ncp);
        }else{
            fds.addPanel(ncp);
        }
    }
    
    @Override
    public void getEditCustomerPage() {
        ecp = new EditCustomerPage();
        if(userType==true){
            fad.addPanel(ecp);
        }else{
            fds.addPanel(ecp);
        }
    }
    
    
    @Override
    public void getSearchCustomerPage() {
        scp = new SearchCustomersPage();
        if(userType==true){
            fad.addPanel(scp);
        }else{
            fds.addPanel(scp);
        }
    }
    
    @Override
    public void getListCustomerPage() {
        lcp = new ListCustomersPage();
        if(userType==true){
            fad.addPanel(lcp);
        }else{
            fds.addPanel(lcp);
        }
    }
    
    @Override
    public void getRemoveCustomerPage() {
        rcp = new RemoveCustomerPage();
        if(userType==true){
            fad.addPanel(rcp);
        }else{
            fds.addPanel(rcp);
        }
        
    }
    
    @Override
    public void getNewOrderPage() {
        nop = new NewOrderPage();
        if(userType==true){
            fad.addPanel(nop);
        }else{
            fds.addPanel(nop);
        }
    }
    
    @Override
    public void getSearchOrderPage() {
        sop = new SearchOrderPage();
        if(userType==true){
            fad.addPanel(sop);
        }else{
            fds.addPanel(sop);
        }
        
    }
    
    @Override
    public void getListOrdersPage() {
        lop = new ListOrdersPage();
        if(userType==true){
            fad.addPanel(lop);
        }else{
            fds.addPanel(lop);
        }
        
    }
    
    @Override
    public void getListStockPage() {
        lstp = new ListStockPage();
        if(userType==true){
            fad.addPanel(lstp);
        }else{
            fds.addPanel(lstp);
        }
        
    }
    
    @Override
    public void getSearchStockPage() {
        ssp = new SearchStockPage();
        if(userType==true){
            fad.addPanel(ssp);
        }else{
            fds.addPanel(ssp);
        }
        
    }
    //<editor-fold defaultstate="collapsed" desc="Get and set methods for the attributes">
    /**
     * @return the hp
     */
    public HomePage getHp() {
        return hp;
    }
    
    /**
     * @param hp the hp to set
     */
    public void setHp(HomePage hp) {
        this.hp = hp;
    }
    
    /**
     * @return the lp
     */
    public LoginPage getLp() {
        return lp;
    }
    
    /**
     * @param lp the lp to set
     */
    public void setLp(LoginPage lp) {
        this.lp = lp;
    }
    
    /**
     * @return the fad
     */
    public FrontDeskAdmin getFad() {
        return fad;
    }
    
    /**
     * @param fad the fad to set
     */
    public void setFad(FrontDeskAdmin fad) {
        this.fad = fad;
    }
    
    /**
     * @return the fds
     */
    public FrontDeskSalesperson getFds() {
        return fds;
    }
    
    /**
     * @param fds the fds to set
     */
    public void setFds(FrontDeskSalesperson fds) {
        this.fds = fds;
    }
    
    /**
     * @return the cp
     */
    public CustomerPage getCp() {
        return cp;
    }
    
    /**
     * @param cp the cp to set
     */
    public void setCp(CustomerPage cp) {
        this.cp = cp;
    }
    
    /**
     * @return the op
     */
    public OrderPage getOp() {
        return op;
    }
    
    /**
     * @param op the op to set
     */
    public void setOp(OrderPage op) {
        this.op = op;
    }
    
    /**
     * @return the sp
     */
    public StockPage getSp() {
        return sp;
    }
    
    /**
     * @param sp the sp to set
     */
    public void setSp(StockPage sp) {
        this.sp = sp;
    }
    
    /**
     * @return the ump
     */
    public UserManagementPanel getUmp() {
        return ump;
    }
    
    /**
     * @param ump the ump to set
     */
    public void setUmp(UserManagementPanel ump) {
        this.ump = ump;
    }
    
    /**
     * @return the ecp
     */
    public EditCustomerPage getEdc() {
        return ecp;
    }
    
    /**
     * @param ecp the ecp to set
     */
    public void setEdc(EditCustomerPage edc) {
        this.ecp = edc;
    }
    
    /**
     * @return the esp
     */
    public EditSalespersonPage getEsp() {
        return esp;
    }
    
    /**
     * @param esp the esp to set
     */
    public void setEsp(EditSalespersonPage esp) {
        this.esp = esp;
    }
    
    /**
     * @return the lcp
     */
    public ListCustomersPage getLcp() {
        return lcp;
    }
    
    /**
     * @param lcp the lcp to set
     */
    public void setLcp(ListCustomersPage lcp) {
        this.lcp = lcp;
    }
    
    /**
     * @return the lop
     */
    public ListOrdersPage getLop() {
        return lop;
    }
    
    /**
     * @param lop the lop to set
     */
    public void setLop(ListOrdersPage lop) {
        this.lop = lop;
    }
    
    /**
     * @return the lsp
     */
    public ListSalespersonsPage getLsp() {
        return lsp;
    }
    
    /**
     * @param lsp the lsp to set
     */
    public void setLsp(ListSalespersonsPage lsp) {
        this.lsp = lsp;
    }
    
    /**
     * @return the lstp
     */
    public ListStockPage getLstp() {
        return lstp;
    }
    
    /**
     * @param lstp the lstp to set
     */
    public void setLstp(ListStockPage lstp) {
        this.lstp = lstp;
    }
    
    /**
     * @return the nap
     */
    public NewAdminPage getNap() {
        return nap;
    }
    
    /**
     * @param nap the nap to set
     */
    public void setNap(NewAdminPage nap) {
        this.nap = nap;
    }
    
    /**
     * @return the ncp
     */
    public NewCustomerPage getNcp() {
        return ncp;
    }
    
    /**
     * @param ncp the ncp to set
     */
    public void setNcp(NewCustomerPage ncp) {
        this.ncp = ncp;
    }
    
    /**
     * @return the nip
     */
    public NewItemPage getNip() {
        return nip;
    }
    
    /**
     * @param nip the nip to set
     */
    public void setNip(NewItemPage nip) {
        this.nip = nip;
    }
    
    /**
     * @return the nop
     */
    public NewOrderPage getNop() {
        return nop;
    }
    
    /**
     * @param nop the nop to set
     */
    public void setNop(NewOrderPage nop) {
        this.nop = nop;
    }
    
    /**
     * @return the nsp
     */
    public NewSalespersonPage getNsp() {
        return nsp;
    }
    
    /**
     * @param nsp the nsp to set
     */
    public void setNsp(NewSalespersonPage nsp) {
        this.nsp = nsp;
    }
    
    /**
     * @return the rcp
     */
    public RemoveCustomerPage getRcp() {
        return rcp;
    }
    
    /**
     * @param rcp the rcp to set
     */
    public void setRcp(RemoveCustomerPage rcp) {
        this.rcp = rcp;
    }
    
    /**
     * @return the rsp
     */
    public RemoveSalespersonPage getRsp() {
        return rsp;
    }
    
    /**
     * @param rsp the rsp to set
     */
    public void setRsp(RemoveSalespersonPage rsp) {
        this.rsp = rsp;
    }
    
    /**
     * @return the scp
     */
    public SearchCustomersPage getScp() {
        return scp;
    }
    
    /**
     * @param scp the scp to set
     */
    public void setScp(SearchCustomersPage scp) {
        this.scp = scp;
    }
    
    /**
     * @return the sop
     */
    public SearchOrderPage getSop() {
        return sop;
    }
    
    /**
     * @param sop the sop to set
     */
    public void setSop(SearchOrderPage sop) {
        this.sop = sop;
    }
    
    /**
     * @return the ssp
     */
    public SearchStockPage getSsp() {
        return ssp;
    }
    
    /**
     * @param ssp the ssp to set
     */
    public void setSsp(SearchStockPage ssp) {
        this.ssp = ssp;
    }
    //</editor-fold>
    @Override
    public void getEditSalespersonPage() {
        esp = new EditSalespersonPage();
        fad.addPanel(esp);
    }
    
    @Override
    public void getListSalespersonPage() {
        lsp = new ListSalespersonsPage();
        fad.addPanel(lsp);
    }
    
    @Override
    public void getNewSalespersonPage() {
        nsp = new NewSalespersonPage();
        fad.addPanel(nsp);
    }
    
    @Override
    public void getRemoveSalespersonPage() {
        rsp = new RemoveSalespersonPage();
        fad.addPanel(rsp);
    }
    
    @Override
    public void getNewItemPage() {
        nip = new NewItemPage();
        if(userType==true){
            fad.addPanel(nip);
        }else{
            fds.addPanel(nip);
        }
    }
    
    @Override
    public void getNewAdminPage() {
        nap = new NewAdminPage();
        fad.addPanel(nap);
    }
    //</editor-fold>
    
}
