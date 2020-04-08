/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilboudo
 */
@Entity
@Table(name = "page_has_categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PageHasCategorie.findAll", query = "SELECT p FROM PageHasCategorie p")
    , @NamedQuery(name = "PageHasCategorie.findById", query = "SELECT p FROM PageHasCategorie p WHERE p.id = :id")})
public class PageHasCategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "id_page", referencedColumnName = "id")
    @ManyToOne
    private Page idPage;
    @JoinColumn(name = "id_categorie", referencedColumnName = "id")
    @ManyToOne
    private Categorie idCategorie;

    public PageHasCategorie() {
    }

    public PageHasCategorie(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Page getIdPage() {
        return idPage;
    }

    public void setIdPage(Page idPage) {
        this.idPage = idPage;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
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
        if (!(object instanceof PageHasCategorie)) {
            return false;
        }
        PageHasCategorie other = (PageHasCategorie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test2.PageHasCategorie[ id=" + id + " ]";
    }
    
}
