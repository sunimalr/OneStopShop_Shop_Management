
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 090007j
 */
@Entity
@Table(name = "accounts")
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findById", query = "SELECT a FROM Accounts a WHERE a.id = :id"),
    @NamedQuery(name = "Accounts.findByCustomerId", query = "SELECT a FROM Accounts a WHERE a.customerId = :customerId"),
    @NamedQuery(name = "Accounts.findByBalance", query = "SELECT a FROM Accounts a WHERE a.balance = :balance"),
    @NamedQuery(name = "Accounts.findByCreditLimit", query = "SELECT a FROM Accounts a WHERE a.creditLimit = :creditLimit")})
public class Accounts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    //private Integer id;
    //@Basic(optional = false)
    //@Column(name = "customer_id")
    private int customerId;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    public Accounts() {
    }

//    public Accounts(Integer id) {
//        this.id = id;
//    }

    public Accounts(int customerId) {
        //this.id = id;
        this.customerId = customerId;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    
   

    

}
