/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
public class MailorderClients implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer customerId;
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
