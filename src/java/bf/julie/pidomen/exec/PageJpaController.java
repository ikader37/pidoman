/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.exec;

import bf.julie.pidomen.entites.Page;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import bf.julie.pidomen.entites.Utilisateur;
import bf.julie.pidomen.entites.PageHasMotCle;
import java.util.ArrayList;
import java.util.List;
import bf.julie.pidomen.entites.PageHasCategorie;
import bf.julie.pidomen.exec.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ilboudo
 */
public class PageJpaController implements Serializable {

    public PageJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Page page) {
        if (page.getPageHasMotCleList() == null) {
            page.setPageHasMotCleList(new ArrayList<PageHasMotCle>());
        }
        if (page.getPageHasCategorieList() == null) {
            page.setPageHasCategorieList(new ArrayList<PageHasCategorie>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilisateur idUtilisateur = page.getIdUtilisateur();
            if (idUtilisateur != null) {
                idUtilisateur = em.getReference(idUtilisateur.getClass(), idUtilisateur.getId());
                page.setIdUtilisateur(idUtilisateur);
            }
            List<PageHasMotCle> attachedPageHasMotCleList = new ArrayList<PageHasMotCle>();
            for (PageHasMotCle pageHasMotCleListPageHasMotCleToAttach : page.getPageHasMotCleList()) {
                pageHasMotCleListPageHasMotCleToAttach = em.getReference(pageHasMotCleListPageHasMotCleToAttach.getClass(), pageHasMotCleListPageHasMotCleToAttach.getId());
                attachedPageHasMotCleList.add(pageHasMotCleListPageHasMotCleToAttach);
            }
            page.setPageHasMotCleList(attachedPageHasMotCleList);
            List<PageHasCategorie> attachedPageHasCategorieList = new ArrayList<PageHasCategorie>();
            for (PageHasCategorie pageHasCategorieListPageHasCategorieToAttach : page.getPageHasCategorieList()) {
                pageHasCategorieListPageHasCategorieToAttach = em.getReference(pageHasCategorieListPageHasCategorieToAttach.getClass(), pageHasCategorieListPageHasCategorieToAttach.getId());
                attachedPageHasCategorieList.add(pageHasCategorieListPageHasCategorieToAttach);
            }
            page.setPageHasCategorieList(attachedPageHasCategorieList);
            em.persist(page);
            if (idUtilisateur != null) {
                idUtilisateur.getPageList().add(page);
                idUtilisateur = em.merge(idUtilisateur);
            }
            for (PageHasMotCle pageHasMotCleListPageHasMotCle : page.getPageHasMotCleList()) {
                Page oldIdPageOfPageHasMotCleListPageHasMotCle = pageHasMotCleListPageHasMotCle.getIdPage();
                pageHasMotCleListPageHasMotCle.setIdPage(page);
                pageHasMotCleListPageHasMotCle = em.merge(pageHasMotCleListPageHasMotCle);
                if (oldIdPageOfPageHasMotCleListPageHasMotCle != null) {
                    oldIdPageOfPageHasMotCleListPageHasMotCle.getPageHasMotCleList().remove(pageHasMotCleListPageHasMotCle);
                    oldIdPageOfPageHasMotCleListPageHasMotCle = em.merge(oldIdPageOfPageHasMotCleListPageHasMotCle);
                }
            }
            for (PageHasCategorie pageHasCategorieListPageHasCategorie : page.getPageHasCategorieList()) {
                Page oldIdPageOfPageHasCategorieListPageHasCategorie = pageHasCategorieListPageHasCategorie.getIdPage();
                pageHasCategorieListPageHasCategorie.setIdPage(page);
                pageHasCategorieListPageHasCategorie = em.merge(pageHasCategorieListPageHasCategorie);
                if (oldIdPageOfPageHasCategorieListPageHasCategorie != null) {
                    oldIdPageOfPageHasCategorieListPageHasCategorie.getPageHasCategorieList().remove(pageHasCategorieListPageHasCategorie);
                    oldIdPageOfPageHasCategorieListPageHasCategorie = em.merge(oldIdPageOfPageHasCategorieListPageHasCategorie);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Page page) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Page persistentPage = em.find(Page.class, page.getId());
            Utilisateur idUtilisateurOld = persistentPage.getIdUtilisateur();
            Utilisateur idUtilisateurNew = page.getIdUtilisateur();
            List<PageHasMotCle> pageHasMotCleListOld = persistentPage.getPageHasMotCleList();
            List<PageHasMotCle> pageHasMotCleListNew = page.getPageHasMotCleList();
            List<PageHasCategorie> pageHasCategorieListOld = persistentPage.getPageHasCategorieList();
            List<PageHasCategorie> pageHasCategorieListNew = page.getPageHasCategorieList();
            if (idUtilisateurNew != null) {
                idUtilisateurNew = em.getReference(idUtilisateurNew.getClass(), idUtilisateurNew.getId());
                page.setIdUtilisateur(idUtilisateurNew);
            }
            List<PageHasMotCle> attachedPageHasMotCleListNew = new ArrayList<PageHasMotCle>();
            for (PageHasMotCle pageHasMotCleListNewPageHasMotCleToAttach : pageHasMotCleListNew) {
                pageHasMotCleListNewPageHasMotCleToAttach = em.getReference(pageHasMotCleListNewPageHasMotCleToAttach.getClass(), pageHasMotCleListNewPageHasMotCleToAttach.getId());
                attachedPageHasMotCleListNew.add(pageHasMotCleListNewPageHasMotCleToAttach);
            }
            pageHasMotCleListNew = attachedPageHasMotCleListNew;
            page.setPageHasMotCleList(pageHasMotCleListNew);
            List<PageHasCategorie> attachedPageHasCategorieListNew = new ArrayList<PageHasCategorie>();
            for (PageHasCategorie pageHasCategorieListNewPageHasCategorieToAttach : pageHasCategorieListNew) {
                pageHasCategorieListNewPageHasCategorieToAttach = em.getReference(pageHasCategorieListNewPageHasCategorieToAttach.getClass(), pageHasCategorieListNewPageHasCategorieToAttach.getId());
                attachedPageHasCategorieListNew.add(pageHasCategorieListNewPageHasCategorieToAttach);
            }
            pageHasCategorieListNew = attachedPageHasCategorieListNew;
            page.setPageHasCategorieList(pageHasCategorieListNew);
            page = em.merge(page);
            if (idUtilisateurOld != null && !idUtilisateurOld.equals(idUtilisateurNew)) {
                idUtilisateurOld.getPageList().remove(page);
                idUtilisateurOld = em.merge(idUtilisateurOld);
            }
            if (idUtilisateurNew != null && !idUtilisateurNew.equals(idUtilisateurOld)) {
                idUtilisateurNew.getPageList().add(page);
                idUtilisateurNew = em.merge(idUtilisateurNew);
            }
            for (PageHasMotCle pageHasMotCleListOldPageHasMotCle : pageHasMotCleListOld) {
                if (!pageHasMotCleListNew.contains(pageHasMotCleListOldPageHasMotCle)) {
                    pageHasMotCleListOldPageHasMotCle.setIdPage(null);
                    pageHasMotCleListOldPageHasMotCle = em.merge(pageHasMotCleListOldPageHasMotCle);
                }
            }
            for (PageHasMotCle pageHasMotCleListNewPageHasMotCle : pageHasMotCleListNew) {
                if (!pageHasMotCleListOld.contains(pageHasMotCleListNewPageHasMotCle)) {
                    Page oldIdPageOfPageHasMotCleListNewPageHasMotCle = pageHasMotCleListNewPageHasMotCle.getIdPage();
                    pageHasMotCleListNewPageHasMotCle.setIdPage(page);
                    pageHasMotCleListNewPageHasMotCle = em.merge(pageHasMotCleListNewPageHasMotCle);
                    if (oldIdPageOfPageHasMotCleListNewPageHasMotCle != null && !oldIdPageOfPageHasMotCleListNewPageHasMotCle.equals(page)) {
                        oldIdPageOfPageHasMotCleListNewPageHasMotCle.getPageHasMotCleList().remove(pageHasMotCleListNewPageHasMotCle);
                        oldIdPageOfPageHasMotCleListNewPageHasMotCle = em.merge(oldIdPageOfPageHasMotCleListNewPageHasMotCle);
                    }
                }
            }
            for (PageHasCategorie pageHasCategorieListOldPageHasCategorie : pageHasCategorieListOld) {
                if (!pageHasCategorieListNew.contains(pageHasCategorieListOldPageHasCategorie)) {
                    pageHasCategorieListOldPageHasCategorie.setIdPage(null);
                    pageHasCategorieListOldPageHasCategorie = em.merge(pageHasCategorieListOldPageHasCategorie);
                }
            }
            for (PageHasCategorie pageHasCategorieListNewPageHasCategorie : pageHasCategorieListNew) {
                if (!pageHasCategorieListOld.contains(pageHasCategorieListNewPageHasCategorie)) {
                    Page oldIdPageOfPageHasCategorieListNewPageHasCategorie = pageHasCategorieListNewPageHasCategorie.getIdPage();
                    pageHasCategorieListNewPageHasCategorie.setIdPage(page);
                    pageHasCategorieListNewPageHasCategorie = em.merge(pageHasCategorieListNewPageHasCategorie);
                    if (oldIdPageOfPageHasCategorieListNewPageHasCategorie != null && !oldIdPageOfPageHasCategorieListNewPageHasCategorie.equals(page)) {
                        oldIdPageOfPageHasCategorieListNewPageHasCategorie.getPageHasCategorieList().remove(pageHasCategorieListNewPageHasCategorie);
                        oldIdPageOfPageHasCategorieListNewPageHasCategorie = em.merge(oldIdPageOfPageHasCategorieListNewPageHasCategorie);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = page.getId();
                if (findPage(id) == null) {
                    throw new NonexistentEntityException("The page with id " + id + " no longer exists.");
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
            Page page;
            try {
                page = em.getReference(Page.class, id);
                page.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The page with id " + id + " no longer exists.", enfe);
            }
            Utilisateur idUtilisateur = page.getIdUtilisateur();
            if (idUtilisateur != null) {
                idUtilisateur.getPageList().remove(page);
                idUtilisateur = em.merge(idUtilisateur);
            }
            List<PageHasMotCle> pageHasMotCleList = page.getPageHasMotCleList();
            for (PageHasMotCle pageHasMotCleListPageHasMotCle : pageHasMotCleList) {
                pageHasMotCleListPageHasMotCle.setIdPage(null);
                pageHasMotCleListPageHasMotCle = em.merge(pageHasMotCleListPageHasMotCle);
            }
            List<PageHasCategorie> pageHasCategorieList = page.getPageHasCategorieList();
            for (PageHasCategorie pageHasCategorieListPageHasCategorie : pageHasCategorieList) {
                pageHasCategorieListPageHasCategorie.setIdPage(null);
                pageHasCategorieListPageHasCategorie = em.merge(pageHasCategorieListPageHasCategorie);
            }
            em.remove(page);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Page> findPageEntities() {
        return findPageEntities(true, -1, -1);
    }

    public List<Page> findPageEntities(int maxResults, int firstResult) {
        return findPageEntities(false, maxResults, firstResult);
    }

    private List<Page> findPageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Page.class));
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

    public Page findPage(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Page.class, id);
        } finally {
            em.close();
        }
    }

    public int getPageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Page> rt = cq.from(Page.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
