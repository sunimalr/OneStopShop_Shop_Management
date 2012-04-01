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
@Table(name = "largeorder_clients")
@NamedQueries({
    @NamedQuery(name = "LargeorderClients.findAll", query = "SELECT l FROM LargeorderClients l"),
    @NamedQuery(name = "LargeorderClients.findByCustomerId", query = "SELECT l FROM LargeorderClients l WHERE l.customerId = :customerId"),
    @NamedQuery(name = "LargeorderClients.findByPersonalSalesRepresentativeId", query = "SELECT l FROM LargeorderClients l WHERE l.personalSalesRepresentativeId = :personalSalesRepresentativeId"),
    @NamedQuery(name = "LargeorderClients.findByBrn", query = "SELECT l FROM LargeorderClients l WHERE l.brn = :brn")})
public class LargeorderClients implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "customer_id")
    private Integer customerId;
    @Basic(optional = false)
    @Column(name = "personal_sales_representative_id")
    private int personalSalesRepresentativeId;
    @Basic(optional = false)
    @Column(name = "BRN")
    private String brn;

    public LargeorderClients() {
    }

    public LargeorderClients(Integer customerId) {
        this.customerId = customerId;
    }

    public LargeorderClients(Integer customerId, int personalSalesRepresentativeId, String brn) {
        this.customerId = customerId;
        this.personalSalesRepresentativeId = personalSalesRepresentativeId;
        this.brn = brn;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public int getPersonalSalesRepresentativeId() {
        return personalSalesRepresentativeId;
    }

    public void setPersonalSalesRepresentativeId(int personalSalesRepresentativeId) {
        this.personalSalesRepresentativeId = personalSalesRepresentativeId;
    }

    public String getBrn() {
        return brn;
    }

    public void setBrn(String brn) {
        this.brn = brn;
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
        if (!(object instanceof LargeorderClients)) {
            return false;
        }
        LargeorderClients other = (LargeorderClients) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shop.LargeorderClients[customerId=" + customerId + "]";
    }

}
