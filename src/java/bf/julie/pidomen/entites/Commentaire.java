/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilboudo
 */
@Entity
@Table(name = "commentaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commentaire.findAll", query = "SELECT c FROM Commentaire c")
    , @NamedQuery(name = "Commentaire.findById", query = "SELECT c FROM Commentaire c WHERE c.id = :id")
    , @NamedQuery(name = "Commentaire.findByNompersonne", query = "SELECT c FROM Commentaire c WHERE c.nompersonne = :nompersonne")
    , @NamedQuery(name = "Commentaire.findByPrenompersonne", query = "SELECT c FROM Commentaire c WHERE c.prenompersonne = :prenompersonne")
    , @NamedQuery(name = "Commentaire.findBySujet", query = "SELECT c FROM Commentaire c WHERE c.sujet = :sujet")
    , @NamedQuery(name = "Commentaire.findByDatecomment", query = "SELECT c FROM Commentaire c WHERE c.datecomment = :datecomment")})
public class Commentaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nompersonne")
    private String nompersonne;
    @Column(name = "prenompersonne")
    private String prenompersonne;
    @Column(name = "sujet")
    private String sujet;
    @Lob
    @Column(name = "comment")
    private String comment;
    @Column(name = "datecomment")
    @Temporal(TemporalType.DATE)
    private Date datecomment;
    @JoinColumn(name = "idpage", referencedColumnName = "id")
    @ManyToOne
    private Page idpage;
    @Column(name = "email")
    private String email;
    

    public Commentaire() {
    }

    public Commentaire(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNompersonne() {
        return nompersonne;
    }

    public void setNompersonne(String nompersonne) {
        this.nompersonne = nompersonne;
    }

    public String getPrenompersonne() {
        return prenompersonne;
    }

    public void setPrenompersonne(String prenompersonne) {
        this.prenompersonne = prenompersonne;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDatecomment() {
        return datecomment;
    }

    public void setDatecomment(Date datecomment) {
        this.datecomment = datecomment;
    }

    public Page getIdpage() {
        return idpage;
    }

    public void setIdpage(Page idpage) {
        this.idpage = idpage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Commentaire[ id=" + id + " ]";
    }
    
}
