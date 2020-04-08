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
import bf.julie.pidomen.entites.MotCle;
import bf.julie.pidomen.entites.Page;
import bf.julie.pidomen.entites.PageHasMotCle;
import bf.julie.pidomen.exec.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ilboudo
 */
public class PageHasMotCleJpaController implements Serializable {

    public PageHasMotCleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PageHasMotCle pageHasMotCle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MotCle idMotCle = pageHasMotCle.getIdMotCle();
            if (idMotCle != null) {
                idMotCle = em.getReference(idMotCle.getClass(), idMotCle.getId());
                pageHasMotCle.setIdMotCle(idMotCle);
            }
            Page idPage = pageHasMotCle.getIdPage();
            if (idPage != null) {
                idPage = em.getReference(idPage.getClass(), idPage.getId());
                pageHasMotCle.setIdPage(idPage);
            }
            em.persist(pageHasMotCle);
            if (idMotCle != null) {
                idMotCle.getPageHasMotCleList().add(pageHasMotCle);
                idMotCle = em.merge(idMotCle);
            }
            if (idPage != null) {
                idPage.getPageHasMotCleList().add(pageHasMotCle);
                idPage = em.merge(idPage);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PageHasMotCle pageHasMotCle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PageHasMotCle persistentPageHasMotCle = em.find(PageHasMotCle.class, pageHasMotCle.getId());
            MotCle idMotCleOld = persistentPageHasMotCle.getIdMotCle();
            MotCle idMotCleNew = pageHasMotCle.getIdMotCle();
            Page idPageOld = persistentPageHasMotCle.getIdPage();
            Page idPageNew = pageHasMotCle.getIdPage();
            if (idMotCleNew != null) {
                idMotCleNew = em.getReference(idMotCleNew.getClass(), idMotCleNew.getId());
                pageHasMotCle.setIdMotCle(idMotCleNew);
            }
            if (idPageNew != null) {
                idPageNew = em.getReference(idPageNew.getClass(), idPageNew.getId());
                pageHasMotCle.setIdPage(idPageNew);
            }
            pageHasMotCle = em.merge(pageHasMotCle);
            if (idMotCleOld != null && !idMotCleOld.equals(idMotCleNew)) {
                idMotCleOld.getPageHasMotCleList().remove(pageHasMotCle);
                idMotCleOld = em.merge(idMotCleOld);
            }
            if (idMotCleNew != null && !idMotCleNew.equals(idMotCleOld)) {
                idMotCleNew.getPageHasMotCleList().add(pageHasMotCle);
                idMotCleNew = em.merge(idMotCleNew);
            }
            if (idPageOld != null && !idPageOld.equals(idPageNew)) {
                idPageOld.getPageHasMotCleList().remove(pageHasMotCle);
                idPageOld = em.merge(idPageOld);
            }
            if (idPageNew != null && !idPageNew.equals(idPageOld)) {
                idPageNew.getPageHasMotCleList().add(pageHasMotCle);
                idPageNew = em.merge(idPageNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pageHasMotCle.getId();
                if (findPageHasMotCle(id) == null) {
                    throw new NonexistentEntityException("The pageHasMotCle with id " + id + " no longer exists.");
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
            PageHasMotCle pageHasMotCle;
            try {
                pageHasMotCle = em.getReference(PageHasMotCle.class, id);
                pageHasMotCle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pageHasMotCle with id " + id + " no longer exists.", enfe);
            }
            MotCle idMotCle = pageHasMotCle.getIdMotCle();
            if (idMotCle != null) {
                idMotCle.getPageHasMotCleList().remove(pageHasMotCle);
                idMotCle = em.merge(idMotCle);
            }
            Page idPage = pageHasMotCle.getIdPage();
            if (idPage != null) {
                idPage.getPageHasMotCleList().remove(pageHasMotCle);
                idPage = em.merge(idPage);
            }
            em.remove(pageHasMotCle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PageHasMotCle> findPageHasMotCleEntities() {
        return findPageHasMotCleEntities(true, -1, -1);
    }

    public List<PageHasMotCle> findPageHasMotCleEntities(int maxResults, int firstResult) {
        return findPageHasMotCleEntities(false, maxResults, firstResult);
    }

    private List<PageHasMotCle> findPageHasMotCleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PageHasMotCle.class));
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

    public PageHasMotCle findPageHasMotCle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PageHasMotCle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPageHasMotCleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PageHasMotCle> rt = cq.from(PageHasMotCle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
