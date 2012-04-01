/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
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
@Table(name = "personal_sales_representatives")
@NamedQueries({
    @NamedQuery(name = "PersonalSalesRepresentatives.findAll", query = "SELECT p FROM PersonalSalesRepresentatives p"),
    @NamedQuery(name = "PersonalSalesRepresentatives.findById", query = "SELECT p FROM PersonalSalesRepresentatives p WHERE p.id = :id"),
    @NamedQuery(name = "PersonalSalesRepresentatives.findByName", query = "SELECT p FROM PersonalSalesRepresentatives p WHERE p.name = :name"),
    @NamedQuery(name = "PersonalSalesRepresentatives.findByTelephone", query = "SELECT p FROM PersonalSalesRepresentatives p WHERE p.telephone = :telephone")})
public class PersonalSalesRepresentatives implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;

    public PersonalSalesRepresentatives() {
    }

    public PersonalSalesRepresentatives(Integer id) {
        this.id = id;
    }

    public PersonalSalesRepresentatives(Integer id, String name, String telephone) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
        if (!(object instanceof PersonalSalesRepresentatives)) {
            return false;
        }
        PersonalSalesRepresentatives other = (PersonalSalesRepresentatives) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shop.PersonalSalesRepresentatives[id=" + id + "]";
    }

}
