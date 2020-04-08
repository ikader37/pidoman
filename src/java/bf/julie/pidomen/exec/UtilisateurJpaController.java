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
import bf.julie.pidomen.entites.Role;
import bf.julie.pidomen.entites.Page;
import bf.julie.pidomen.entites.Utilisateur;
import bf.julie.pidomen.exec.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ilboudo
 */
public class UtilisateurJpaController implements Serializable {

    public UtilisateurJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Utilisateur utilisateur) {
        if (utilisateur.getPageList() == null) {
            utilisateur.setPageList(new ArrayList<Page>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Role idRole = utilisateur.getIdRole();
            if (idRole != null) {
                idRole = em.getReference(idRole.getClass(), idRole.getId());
                utilisateur.setIdRole(idRole);
            }
            List<Page> attachedPageList = new ArrayList<Page>();
            for (Page pageListPageToAttach : utilisateur.getPageList()) {
                pageListPageToAttach = em.getReference(pageListPageToAttach.getClass(), pageListPageToAttach.getId());
                attachedPageList.add(pageListPageToAttach);
            }
            utilisateur.setPageList(attachedPageList);
            em.persist(utilisateur);
            if (idRole != null) {
                idRole.getUtilisateurList().add(utilisateur);
                idRole = em.merge(idRole);
            }
            for (Page pageListPage : utilisateur.getPageList()) {
                Utilisateur oldIdUtilisateurOfPageListPage = pageListPage.getIdUtilisateur();
                pageListPage.setIdUtilisateur(utilisateur);
                pageListPage = em.merge(pageListPage);
                if (oldIdUtilisateurOfPageListPage != null) {
                    oldIdUtilisateurOfPageListPage.getPageList().remove(pageListPage);
                    oldIdUtilisateurOfPageListPage = em.merge(oldIdUtilisateurOfPageListPage);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Utilisateur utilisateur) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilisateur persistentUtilisateur = em.find(Utilisateur.class, utilisateur.getId());
            Role idRoleOld = persistentUtilisateur.getIdRole();
            Role idRoleNew = utilisateur.getIdRole();
            List<Page> pageListOld = persistentUtilisateur.getPageList();
            List<Page> pageListNew = utilisateur.getPageList();
            if (idRoleNew != null) {
                idRoleNew = em.getReference(idRoleNew.getClass(), idRoleNew.getId());
                utilisateur.setIdRole(idRoleNew);
            }
            List<Page> attachedPageListNew = new ArrayList<Page>();
            for (Page pageListNewPageToAttach : pageListNew) {
                pageListNewPageToAttach = em.getReference(pageListNewPageToAttach.getClass(), pageListNewPageToAttach.getId());
                attachedPageListNew.add(pageListNewPageToAttach);
            }
            pageListNew = attachedPageListNew;
            utilisateur.setPageList(pageListNew);
            utilisateur = em.merge(utilisateur);
            if (idRoleOld != null && !idRoleOld.equals(idRoleNew)) {
                idRoleOld.getUtilisateurList().remove(utilisateur);
                idRoleOld = em.merge(idRoleOld);
            }
            if (idRoleNew != null && !idRoleNew.equals(idRoleOld)) {
                idRoleNew.getUtilisateurList().add(utilisateur);
                idRoleNew = em.merge(idRoleNew);
            }
            for (Page pageListOldPage : pageListOld) {
                if (!pageListNew.contains(pageListOldPage)) {
                    pageListOldPage.setIdUtilisateur(null);
                    pageListOldPage = em.merge(pageListOldPage);
                }
            }
            for (Page pageListNewPage : pageListNew) {
                if (!pageListOld.contains(pageListNewPage)) {
                    Utilisateur oldIdUtilisateurOfPageListNewPage = pageListNewPage.getIdUtilisateur();
                    pageListNewPage.setIdUtilisateur(utilisateur);
                    pageListNewPage = em.merge(pageListNewPage);
                    if (oldIdUtilisateurOfPageListNewPage != null && !oldIdUtilisateurOfPageListNewPage.equals(utilisateur)) {
                        oldIdUtilisateurOfPageListNewPage.getPageList().remove(pageListNewPage);
                        oldIdUtilisateurOfPageListNewPage = em.merge(oldIdUtilisateurOfPageListNewPage);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = utilisateur.getId();
                if (findUtilisateur(id) == null) {
                    throw new NonexistentEntityException("The utilisateur with id " + id + " no longer exists.");
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
            Utilisateur utilisateur;
            try {
                utilisateur = em.getReference(Utilisateur.class, id);
                utilisateur.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utilisateur with id " + id + " no longer exists.", enfe);
            }
            Role idRole = utilisateur.getIdRole();
            if (idRole != null) {
                idRole.getUtilisateurList().remove(utilisateur);
                idRole = em.merge(idRole);
            }
            List<Page> pageList = utilisateur.getPageList();
            for (Page pageListPage : pageList) {
                pageListPage.setIdUtilisateur(null);
                pageListPage = em.merge(pageListPage);
            }
            em.remove(utilisateur);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Utilisateur> findUtilisateurEntities() {
        return findUtilisateurEntities(true, -1, -1);
    }

    public List<Utilisateur> findUtilisateurEntities(int maxResults, int firstResult) {
        return findUtilisateurEntities(false, maxResults, firstResult);
    }

    private List<Utilisateur> findUtilisateurEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Utilisateur.class));
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

    public Utilisateur findUtilisateur(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Utilisateur.class, id);
        } finally {
            em.close();
        }
    }

    public int getUtilisateurCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Utilisateur> rt = cq.from(Utilisateur.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
