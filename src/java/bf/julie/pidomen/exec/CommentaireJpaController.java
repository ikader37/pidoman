/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.exec;

import bf.julie.pidomen.entites.Commentaire;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import bf.julie.pidomen.entites.Page;
import bf.julie.pidomen.exec.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ilboudo
 */
public class CommentaireJpaController implements Serializable {

    public CommentaireJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Commentaire commentaire) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Page idpage = commentaire.getIdpage();
            if (idpage != null) {
                idpage = em.getReference(idpage.getClass(), idpage.getId());
                commentaire.setIdpage(idpage);
            }
            em.persist(commentaire);
            if (idpage != null) {
                idpage.getCommentaireList().add(commentaire);
                idpage = em.merge(idpage);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Commentaire commentaire) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commentaire persistentCommentaire = em.find(Commentaire.class, commentaire.getId());
            Page idpageOld = persistentCommentaire.getIdpage();
            Page idpageNew = commentaire.getIdpage();
            if (idpageNew != null) {
                idpageNew = em.getReference(idpageNew.getClass(), idpageNew.getId());
                commentaire.setIdpage(idpageNew);
            }
            commentaire = em.merge(commentaire);
            if (idpageOld != null && !idpageOld.equals(idpageNew)) {
                idpageOld.getCommentaireList().remove(commentaire);
                idpageOld = em.merge(idpageOld);
            }
            if (idpageNew != null && !idpageNew.equals(idpageOld)) {
                idpageNew.getCommentaireList().add(commentaire);
                idpageNew = em.merge(idpageNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = commentaire.getId();
                if (findCommentaire(id) == null) {
                    throw new NonexistentEntityException("The commentaire with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commentaire commentaire;
            try {
                commentaire = em.getReference(Commentaire.class, id);
                commentaire.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commentaire with id " + id + " no longer exists.", enfe);
            }
            Page idpage = commentaire.getIdpage();
            if (idpage != null) {
                idpage.getCommentaireList().remove(commentaire);
                idpage = em.merge(idpage);
            }
            em.remove(commentaire);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Commentaire> findCommentaireEntities() {
        return findCommentaireEntities(true, -1, -1);
    }

    public List<Commentaire> findCommentaireEntities(int maxResults, int firstResult) {
        return findCommentaireEntities(false, maxResults, firstResult);
    }

    private List<Commentaire> findCommentaireEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Commentaire.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Commentaire findCommentaire(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Commentaire.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommentaireCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Commentaire> rt = cq.from(Commentaire.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
