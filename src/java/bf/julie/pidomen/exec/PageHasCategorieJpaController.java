/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.exec;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import bf.julie.pidomen.entites.Page;
import bf.julie.pidomen.entites.Categorie;
import bf.julie.pidomen.entites.PageHasCategorie;
import bf.julie.pidomen.exec.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ilboudo
 */
public class PageHasCategorieJpaController implements Serializable {

    public PageHasCategorieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PageHasCategorie pageHasCategorie) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Page idPage = pageHasCategorie.getIdPage();
            if (idPage != null) {
                idPage = em.getReference(idPage.getClass(), idPage.getId());
                pageHasCategorie.setIdPage(idPage);
            }
            Categorie idCategorie = pageHasCategorie.getIdCategorie();
            if (idCategorie != null) {
                idCategorie = em.getReference(idCategorie.getClass(), idCategorie.getId());
                pageHasCategorie.setIdCategorie(idCategorie);
            }
            em.persist(pageHasCategorie);
            if (idPage != null) {
                idPage.getPageHasCategorieList().add(pageHasCategorie);
                idPage = em.merge(idPage);
            }
            if (idCategorie != null) {
                idCategorie.getPageHasCategorieList().add(pageHasCategorie);
                idCategorie = em.merge(idCategorie);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PageHasCategorie pageHasCategorie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PageHasCategorie persistentPageHasCategorie = em.find(PageHasCategorie.class, pageHasCategorie.getId());
            Page idPageOld = persistentPageHasCategorie.getIdPage();
            Page idPageNew = pageHasCategorie.getIdPage();
            Categorie idCategorieOld = persistentPageHasCategorie.getIdCategorie();
            Categorie idCategorieNew = pageHasCategorie.getIdCategorie();
            if (idPageNew != null) {
                idPageNew = em.getReference(idPageNew.getClass(), idPageNew.getId());
                pageHasCategorie.setIdPage(idPageNew);
            }
            if (idCategorieNew != null) {
                idCategorieNew = em.getReference(idCategorieNew.getClass(), idCategorieNew.getId());
                pageHasCategorie.setIdCategorie(idCategorieNew);
            }
            pageHasCategorie = em.merge(pageHasCategorie);
            if (idPageOld != null && !idPageOld.equals(idPageNew)) {
                idPageOld.getPageHasCategorieList().remove(pageHasCategorie);
                idPageOld = em.merge(idPageOld);
            }
            if (idPageNew != null && !idPageNew.equals(idPageOld)) {
                idPageNew.getPageHasCategorieList().add(pageHasCategorie);
                idPageNew = em.merge(idPageNew);
            }
            if (idCategorieOld != null && !idCategorieOld.equals(idCategorieNew)) {
                idCategorieOld.getPageHasCategorieList().remove(pageHasCategorie);
                idCategorieOld = em.merge(idCategorieOld);
            }
            if (idCategorieNew != null && !idCategorieNew.equals(idCategorieOld)) {
                idCategorieNew.getPageHasCategorieList().add(pageHasCategorie);
                idCategorieNew = em.merge(idCategorieNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pageHasCategorie.getId();
                if (findPageHasCategorie(id) == null) {
                    throw new NonexistentEntityException("The pageHasCategorie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PageHasCategorie pageHasCategorie;
            try {
                pageHasCategorie = em.getReference(PageHasCategorie.class, id);
                pageHasCategorie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pageHasCategorie with id " + id + " no longer exists.", enfe);
            }
            Page idPage = pageHasCategorie.getIdPage();
            if (idPage != null) {
                idPage.getPageHasCategorieList().remove(pageHasCategorie);
                idPage = em.merge(idPage);
            }
            Categorie idCategorie = pageHasCategorie.getIdCategorie();
            if (idCategorie != null) {
                idCategorie.getPageHasCategorieList().remove(pageHasCategorie);
                idCategorie = em.merge(idCategorie);
            }
            em.remove(pageHasCategorie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PageHasCategorie> findPageHasCategorieEntities() {
        return findPageHasCategorieEntities(true, -1, -1);
    }

    public List<PageHasCategorie> findPageHasCategorieEntities(int maxResults, int firstResult) {
        return findPageHasCategorieEntities(false, maxResults, firstResult);
    }

    private List<PageHasCategorie> findPageHasCategorieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PageHasCategorie.class));
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

    public PageHasCategorie findPageHasCategorie(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PageHasCategorie.class, id);
        } finally {
            em.close();
        }
    }

    public int getPageHasCategorieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PageHasCategorie> rt = cq.from(PageHasCategorie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
