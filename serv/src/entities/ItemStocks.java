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
@Table(name = "item_stocks")
@NamedQueries({
    @NamedQuery(name = "ItemStocks.findAll", query = "SELECT i FROM ItemStocks i"),
    @NamedQuery(name = "ItemStocks.findById", query = "SELECT i FROM ItemStocks i WHERE i.id = :id"),
    @NamedQuery(name = "ItemStocks.findByItemType", query = "SELECT i FROM ItemStocks i WHERE i.itemType = :itemType"),
    @NamedQuery(name = "ItemStocks.findByRemainingStock", query = "SELECT i FROM ItemStocks i WHERE i.remainingStock = :remainingStock"),
    @NamedQuery(name = "ItemStocks.findByUnitPrice", query = "SELECT i FROM ItemStocks i WHERE i.unitPrice = :unitPrice")})
public class ItemStocks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "item_type")
    private String itemType;
    @Column(name = "remaining_stock")
    private BigDecimal remainingStock;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    public ItemStocks() {
    }

    public ItemStocks(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(BigDecimal remainingStock) {
        this.remainingStock = remainingStock;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemStocks)) {
            return false;
        }
        ItemStocks other = (ItemStocks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shop.ItemStocks[id=" + id + "]";
    }

}
