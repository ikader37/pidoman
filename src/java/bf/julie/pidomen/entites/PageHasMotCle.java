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
@Table(name = "page_has_mot_cle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PageHasMotCle.findAll", query = "SELECT p FROM PageHasMotCle p")
    , @NamedQuery(name = "PageHasMotCle.findById", query = "SELECT p FROM PageHasMotCle p WHERE p.id = :id")})
public class PageHasMotCle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "id_mot_cle", referencedColumnName = "ID")
    @ManyToOne
    private MotCle idMotCle;
    @JoinColumn(name = "id_page", referencedColumnName = "id")
    @ManyToOne
    private Page idPage;

    public PageHasMotCle() {
    }

    public PageHasMotCle(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MotCle getIdMotCle() {
        return idMotCle;
    }

    public void setIdMotCle(MotCle idMotCle) {
        this.idMotCle = idMotCle;
    }

    public Page getIdPage() {
        return idPage;
    }

    public void setIdPage(Page idPage) {
        this.idPage = idPage;
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
        if (!(object instanceof PageHasMotCle)) {
            return false;
        }
        PageHasMotCle other = (PageHasMotCle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test2.PageHasMotCle[ id=" + id + " ]";
    }

    public PageHasMotCle(MotCle idMotCle, Page idPage) {
        this.idMotCle = idMotCle;
        this.idPage = idPage;
    }
    
    
    
}
