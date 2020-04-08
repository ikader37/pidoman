/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ilboudo
 */
@Entity
@Table(name = "mot_cle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotCle.findAll", query = "SELECT m FROM MotCle m")
    , @NamedQuery(name = "MotCle.findById", query = "SELECT m FROM MotCle m WHERE m.id = :id")
    , @NamedQuery(name = "MotCle.findByLibelle", query = "SELECT m FROM MotCle m WHERE m.libelle = :libelle")})
public class MotCle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "LIBELLE")
    private String libelle;
    @OneToMany(mappedBy = "idMotCle")
    private List<PageHasMotCle> pageHasMotCleList;

    public MotCle() {
    }

    public MotCle(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<PageHasMotCle> getPageHasMotCleList() {
        return pageHasMotCleList;
    }

    public void setPageHasMotCleList(List<PageHasMotCle> pageHasMotCleList) {
        this.pageHasMotCleList = pageHasMotCleList;
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
        if (!(object instanceof MotCle)) {
            return false;
        }
        MotCle other = (MotCle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test2.MotCle[ id=" + id + " ]";
    }

    public MotCle(String libelle) {
        this.libelle = libelle;
    }
    
    
    
}
