/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
public class OrderItems implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private int orderId;
    private int itemStockId;
    private Integer qtyAmt;
    private BigDecimal price;

    public OrderItems() {
    }

    public OrderItems(Integer id) {
        this.id = id;
    }

    public OrderItems(Integer id, int orderId, int itemStockId) {
        this.id = id;
        this.orderId = orderId;
        this.itemStockId = itemStockId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemStockId() {
        return itemStockId;
    }

    public void setItemStockId(int itemStockId) {
        this.itemStockId = itemStockId;
    }

    public Integer getQtyAmt() {
        return qtyAmt;
    }

    public void setQtyAmt(Integer qtyAmt) {
        this.qtyAmt = qtyAmt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        if (!(object instanceof OrderItems)) {
            return false;
        }
        OrderItems other = (OrderItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shop.OrderItems[id=" + id + "]";
    }

}
