/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 090007J
 */
@Entity
@Table(name = "mailorder_clients")
@NamedQueries({
    @NamedQuery(name = "MailorderClients.findAll", query = "SELECT m FROM MailorderClients m"),
    @NamedQuery(name = "MailorderClients.findByCustomerId", query = "SELECT m FROM MailorderClients m WHERE m.customerId = :customerId"),
    @NamedQuery(name = "MailorderClients.findByTrn", query = "SELECT m FROM MailorderClients m WHERE m.trn = :trn")})
public class MailorderClients implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "customer_id")
    private Integer customerId;
    @Basic(optional = false)
    @Column(name = "TRN")
    private String trn;

    public MailorderClients() {
    }

    public MailorderClients(Integer customerId) {
        this.customerId = customerId;
    }

    public MailorderClients(Integer customerId, String trn) {
        this.customerId = customerId;
        this.trn = trn;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTrn() {
        return trn;
    }

    public void setTrn(String trn) {
        this.trn = trn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MailorderClients)) {
            return false;
        }
        MailorderClients other = (MailorderClients) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shop.MailorderClients[customerId=" + customerId + "]";
    }

}
