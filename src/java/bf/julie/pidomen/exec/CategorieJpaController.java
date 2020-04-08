/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.exec;

import bf.julie.pidomen.entites.Categorie;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import bf.julie.pidomen.entites.PageHasCategorie;
import bf.julie.pidomen.exec.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ilboudo
 */
public class CategorieJpaController implements Serializable {

    public CategorieJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categorie categorie) {
        if (categorie.getPageHasCategorieList() == null) {
            categorie.setPageHasCategorieList(new ArrayList<PageHasCategorie>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PageHasCategorie> attachedPageHasCategorieList = new ArrayList<PageHasCategorie>();
            for (PageHasCategorie pageHasCategorieListPageHasCategorieToAttach : categorie.getPageHasCategorieList()) {
                pageHasCategorieListPageHasCategorieToAttach = em.getReference(pageHasCategorieListPageHasCategorieToAttach.getClass(), pageHasCategorieListPageHasCategorieToAttach.getId());
                attachedPageHasCategorieList.add(pageHasCategorieListPageHasCategorieToAttach);
            }
            categorie.setPageHasCategorieList(attachedPageHasCategorieList);
            em.persist(categorie);
            for (PageHasCategorie pageHasCategorieListPageHasCategorie : categorie.getPageHasCategorieList()) {
                Categorie oldIdCategorieOfPageHasCategorieListPageHasCategorie = pageHasCategorieListPageHasCategorie.getIdCategorie();
                pageHasCategorieListPageHasCategorie.setIdCategorie(categorie);
                pageHasCategorieListPageHasCategorie = em.merge(pageHasCategorieListPageHasCategorie);
                if (oldIdCategorieOfPageHasCategorieListPageHasCategorie != null) {
                    oldIdCategorieOfPageHasCategorieListPageHasCategorie.getPageHasCategorieList().remove(pageHasCategorieListPageHasCategorie);
                    oldIdCategorieOfPageHasCategorieListPageHasCategorie = em.merge(oldIdCategorieOfPageHasCategorieListPageHasCategorie);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categorie categorie) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorie persistentCategorie = em.find(Categorie.class, categorie.getId());
            List<PageHasCategorie> pageHasCategorieListOld = persistentCategorie.getPageHasCategorieList();
            List<PageHasCategorie> pageHasCategorieListNew = categorie.getPageHasCategorieList();
            List<PageHasCategorie> attachedPageHasCategorieListNew = new ArrayList<PageHasCategorie>();
            for (PageHasCategorie pageHasCategorieListNewPageHasCategorieToAttach : pageHasCategorieListNew) {
                pageHasCategorieListNewPageHasCategorieToAttach = em.getReference(pageHasCategorieListNewPageHasCategorieToAttach.getClass(), pageHasCategorieListNewPageHasCategorieToAttach.getId());
                attachedPageHasCategorieListNew.add(pageHasCategorieListNewPageHasCategorieToAttach);
            }
            pageHasCategorieListNew = attachedPageHasCategorieListNew;
            categorie.setPageHasCategorieList(pageHasCategorieListNew);
            categorie = em.merge(categorie);
            for (PageHasCategorie pageHasCategorieListOldPageHasCategorie : pageHasCategorieListOld) {
                if (!pageHasCategorieListNew.contains(pageHasCategorieListOldPageHasCategorie)) {
                    pageHasCategorieListOldPageHasCategorie.setIdCategorie(null);
                    pageHasCategorieListOldPageHasCategorie = em.merge(pageHasCategorieListOldPageHasCategorie);
                }
            }
            for (PageHasCategorie pageHasCategorieListNewPageHasCategorie : pageHasCategorieListNew) {
                if (!pageHasCategorieListOld.contains(pageHasCategorieListNewPageHasCategorie)) {
                    Categorie oldIdCategorieOfPageHasCategorieListNewPageHasCategorie = pageHasCategorieListNewPageHasCategorie.getIdCategorie();
                    pageHasCategorieListNewPageHasCategorie.setIdCategorie(categorie);
                    pageHasCategorieListNewPageHasCategorie = em.merge(pageHasCategorieListNewPageHasCategorie);
                    if (oldIdCategorieOfPageHasCategorieListNewPageHasCategorie != null && !oldIdCategorieOfPageHasCategorieListNewPageHasCategorie.equals(categorie)) {
                        oldIdCategorieOfPageHasCategorieListNewPageHasCategorie.getPageHasCategorieList().remove(pageHasCategorieListNewPageHasCategorie);
                        oldIdCategorieOfPageHasCategorieListNewPageHasCategorie = em.merge(oldIdCategorieOfPageHasCategorieListNewPageHasCategorie);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = categorie.getId();
                if (findCategorie(id) == null) {
                    throw new NonexistentEntityException("The categorie with id " + id + " no longer exists.");
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
            Categorie categorie;
            try {
                categorie = em.getReference(Categorie.class, id);
                categorie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categorie with id " + id + " no longer exists.", enfe);
            }
            List<PageHasCategorie> pageHasCategorieList = categorie.getPageHasCategorieList();
            for (PageHasCategorie pageHasCategorieListPageHasCategorie : pageHasCategorieList) {
                pageHasCategorieListPageHasCategorie.setIdCategorie(null);
                pageHasCategorieListPageHasCategorie = em.merge(pageHasCategorieListPageHasCategorie);
            }
            em.remove(categorie);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categorie> findCategorieEntities() {
        return findCategorieEntities(true, -1, -1);
    }

    public List<Categorie> findCategorieEntities(int maxResults, int firstResult) {
        return findCategorieEntities(false, maxResults, firstResult);
    }

    private List<Categorie> findCategorieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categorie.class));
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

    public Categorie findCategorie(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categorie.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategorieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categorie> rt = cq.from(Categorie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
