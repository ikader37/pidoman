/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.entites;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ilboudo
 */
@Entity
@Table(name = "page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Page.findAll", query = "SELECT p FROM Page p")
    , @NamedQuery(name = "Page.findById", query = "SELECT p FROM Page p WHERE p.id = :id")
    , @NamedQuery(name = "Page.findByContenu", query = "SELECT p FROM Page p WHERE p.contenu = :contenu")
    , @NamedQuery(name = "Page.findByDateCreation", query = "SELECT p FROM Page p WHERE p.dateCreation = :dateCreation")
    , @NamedQuery(name = "Page.findByTitre", query = "SELECT p FROM Page p WHERE p.titre = :titre")})
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "contenu")
    private String contenu;
    
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    
    @Column(name = "titre")
    private String titre;
    
    @OneToMany(mappedBy = "idPage")
    private List<PageHasMotCle> pageHasMotCleList;
    
    @OneToMany(mappedBy = "idPage")
    private List<PageHasCategorie> pageHasCategorieList;
    
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id")
    @ManyToOne
    private Utilisateur idUtilisateur;
    
    @JoinColumn(name = "modifier_par", referencedColumnName = "id")
    @ManyToOne
    private Utilisateur modifier_par;
    
    @Column(name = "date_modification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_modification;
    @Column(name = "doc")
    private byte[] doc;
    @Column(name = "img1")
    private byte[] img1;
    @Column(name = "img2")
    private byte[] img2;
    @Column(name = "img3")
    private byte[] img3;
    @Column(name = "img4")
    private byte[] img4;
    
    
    @Column(name = "keyword")//Designe les mots cle de la pages
    private String keyWords;
    @OneToMany(mappedBy = "idpage")
    private List<Commentaire> commentaireList;
    
    public Page() {
    }

    public Page(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @XmlTransient
    public List<PageHasMotCle> getPageHasMotCleList() {
        return pageHasMotCleList;
    }

    public void setPageHasMotCleList(List<PageHasMotCle> pageHasMotCleList) {
        this.pageHasMotCleList = pageHasMotCleList;
    }

    @XmlTransient
    public List<PageHasCategorie> getPageHasCategorieList() {
        return pageHasCategorieList;
    }

    public void setPageHasCategorieList(List<PageHasCategorie> pageHasCategorieList) {
        this.pageHasCategorieList = pageHasCategorieList;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur getModifier_par() {
        return modifier_par;
    }

    public void setModifier_par(Utilisateur modifier_par) {
        this.modifier_par = modifier_par;
    }

    public Date getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(Date date_modification) {
        this.date_modification = date_modification;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public byte[] getDoc() {
        return doc;
    }

    public void setDoc(byte[] doc) {
        this.doc = doc;
    }

    public byte[] getImg1() {
        return img1;
    }

    public void setImg1(byte[] img1) {
        this.img1 = img1;
    }

    public byte[] getImg2() {
        return img2;
    }

    public void setImg2(byte[] img2) {
        this.img2 = img2;
    }

    public byte[] getImg3() {
        return img3;
    }

    public void setImg3(byte[] img3) {
        this.img3 = img3;
    }

    public byte[] getImg4() {
        return img4;
    }

    public void setImg4(byte[] img4) {
        this.img4 = img4;
    }
    
    @XmlTransient
    public List<Commentaire> getCommentaireList() {
        return commentaireList;
    }

    public void setCommentaireList(List<Commentaire> commentaireList) {
        this.commentaireList = commentaireList;
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
        if (!(object instanceof Page)) {
            return false;
        }
        Page other = (Page) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test2.Page[ id=" + id + " ]";
    }

    public Page(String titre,String contenu, Utilisateur idUtilisateur) {
        this.contenu = contenu;
        this.titre = titre;
        this.idUtilisateur = idUtilisateur;
    }
    
    public String getNbConenue(){
        
        return this.contenu.substring(10);
    }
    
}
