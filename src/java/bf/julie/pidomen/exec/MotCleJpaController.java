/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.exec;

import bf.julie.pidomen.entites.MotCle;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import bf.julie.pidomen.entites.PageHasMotCle;
import bf.julie.pidomen.exec.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ilboudo
 */
public class MotCleJpaController implements Serializable {

    public MotCleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MotCle motCle) {
        if (motCle.getPageHasMotCleList() == null) {
            motCle.setPageHasMotCleList(new ArrayList<PageHasMotCle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PageHasMotCle> attachedPageHasMotCleList = new ArrayList<PageHasMotCle>();
            for (PageHasMotCle pageHasMotCleListPageHasMotCleToAttach : motCle.getPageHasMotCleList()) {
                pageHasMotCleListPageHasMotCleToAttach = em.getReference(pageHasMotCleListPageHasMotCleToAttach.getClass(), pageHasMotCleListPageHasMotCleToAttach.getId());
                attachedPageHasMotCleList.add(pageHasMotCleListPageHasMotCleToAttach);
            }
            motCle.setPageHasMotCleList(attachedPageHasMotCleList);
            em.persist(motCle);
            for (PageHasMotCle pageHasMotCleListPageHasMotCle : motCle.getPageHasMotCleList()) {
                MotCle oldIdMotCleOfPageHasMotCleListPageHasMotCle = pageHasMotCleListPageHasMotCle.getIdMotCle();
                pageHasMotCleListPageHasMotCle.setIdMotCle(motCle);
                pageHasMotCleListPageHasMotCle = em.merge(pageHasMotCleListPageHasMotCle);
                if (oldIdMotCleOfPageHasMotCleListPageHasMotCle != null) {
                    oldIdMotCleOfPageHasMotCleListPageHasMotCle.getPageHasMotCleList().remove(pageHasMotCleListPageHasMotCle);
                    oldIdMotCleOfPageHasMotCleListPageHasMotCle = em.merge(oldIdMotCleOfPageHasMotCleListPageHasMotCle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MotCle motCle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MotCle persistentMotCle = em.find(MotCle.class, motCle.getId());
            List<PageHasMotCle> pageHasMotCleListOld = persistentMotCle.getPageHasMotCleList();
            List<PageHasMotCle> pageHasMotCleListNew = motCle.getPageHasMotCleList();
            List<PageHasMotCle> attachedPageHasMotCleListNew = new ArrayList<PageHasMotCle>();
            for (PageHasMotCle pageHasMotCleListNewPageHasMotCleToAttach : pageHasMotCleListNew) {
                pageHasMotCleListNewPageHasMotCleToAttach = em.getReference(pageHasMotCleListNewPageHasMotCleToAttach.getClass(), pageHasMotCleListNewPageHasMotCleToAttach.getId());
                attachedPageHasMotCleListNew.add(pageHasMotCleListNewPageHasMotCleToAttach);
            }
            pageHasMotCleListNew = attachedPageHasMotCleListNew;
            motCle.setPageHasMotCleList(pageHasMotCleListNew);
            motCle = em.merge(motCle);
            for (PageHasMotCle pageHasMotCleListOldPageHasMotCle : pageHasMotCleListOld) {
                if (!pageHasMotCleListNew.contains(pageHasMotCleListOldPageHasMotCle)) {
                    pageHasMotCleListOldPageHasMotCle.setIdMotCle(null);
                    pageHasMotCleListOldPageHasMotCle = em.merge(pageHasMotCleListOldPageHasMotCle);
                }
            }
            for (PageHasMotCle pageHasMotCleListNewPageHasMotCle : pageHasMotCleListNew) {
                if (!pageHasMotCleListOld.contains(pageHasMotCleListNewPageHasMotCle)) {
                    MotCle oldIdMotCleOfPageHasMotCleListNewPageHasMotCle = pageHasMotCleListNewPageHasMotCle.getIdMotCle();
                    pageHasMotCleListNewPageHasMotCle.setIdMotCle(motCle);
                    pageHasMotCleListNewPageHasMotCle = em.merge(pageHasMotCleListNewPageHasMotCle);
                    if (oldIdMotCleOfPageHasMotCleListNewPageHasMotCle != null && !oldIdMotCleOfPageHasMotCleListNewPageHasMotCle.equals(motCle)) {
                        oldIdMotCleOfPageHasMotCleListNewPageHasMotCle.getPageHasMotCleList().remove(pageHasMotCleListNewPageHasMotCle);
                        oldIdMotCleOfPageHasMotCleListNewPageHasMotCle = em.merge(oldIdMotCleOfPageHasMotCleListNewPageHasMotCle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = motCle.getId();
                if (findMotCle(id) == null) {
                    throw new NonexistentEntityException("The motCle with id " + id + " no longer exists.");
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
            MotCle motCle;
            try {
                motCle = em.getReference(MotCle.class, id);
                motCle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The motCle with id " + id + " no longer exists.", enfe);
            }
            List<PageHasMotCle> pageHasMotCleList = motCle.getPageHasMotCleList();
            for (PageHasMotCle pageHasMotCleListPageHasMotCle : pageHasMotCleList) {
                pageHasMotCleListPageHasMotCle.setIdMotCle(null);
                pageHasMotCleListPageHasMotCle = em.merge(pageHasMotCleListPageHasMotCle);
            }
            em.remove(motCle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MotCle> findMotCleEntities() {
        return findMotCleEntities(true, -1, -1);
    }

    public List<MotCle> findMotCleEntities(int maxResults, int firstResult) {
        return findMotCleEntities(false, maxResults, firstResult);
    }

    private List<MotCle> findMotCleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MotCle.class));
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

    public MotCle findMotCle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MotCle.class, id);
        } finally {
            em.close();
        }
    }

    public int getMotCleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MotCle> rt = cq.from(MotCle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
