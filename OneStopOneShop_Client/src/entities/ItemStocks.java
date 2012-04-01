/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemStocks implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String itemType;
    private BigDecimal remainingStock;
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
