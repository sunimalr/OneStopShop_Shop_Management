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
@Table(name = "order_items")
@NamedQueries({
    @NamedQuery(name = "OrderItems.findAll", query = "SELECT o FROM OrderItems o"),
    @NamedQuery(name = "OrderItems.findById", query = "SELECT o FROM OrderItems o WHERE o.id = :id"),
    @NamedQuery(name = "OrderItems.findByOrderId", query = "SELECT o FROM OrderItems o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OrderItems.findByItemStockId", query = "SELECT o FROM OrderItems o WHERE o.itemStockId = :itemStockId"),
    @NamedQuery(name = "OrderItems.findByQtyAmt", query = "SELECT o FROM OrderItems o WHERE o.qtyAmt = :qtyAmt"),
    @NamedQuery(name = "OrderItems.findByPrice", query = "SELECT o FROM OrderItems o WHERE o.price = :price")})
public class OrderItems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "order_id")
    private int orderId;
    @Basic(optional = false)
    @Column(name = "item_stock_id")
    private int itemStockId;
    @Column(name = "qty_amt")
    private Integer qtyAmt;
    @Column(name = "price")
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
