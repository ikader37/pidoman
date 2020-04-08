/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.exec;

import bf.julie.pidomen.entites.Role;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class RoleJpaController implements Serializable {

    public RoleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Role role) {
        if (role.getUtilisateurList() == null) {
            role.setUtilisateurList(new ArrayList<Utilisateur>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Utilisateur> attachedUtilisateurList = new ArrayList<Utilisateur>();
            for (Utilisateur utilisateurListUtilisateurToAttach : role.getUtilisateurList()) {
                utilisateurListUtilisateurToAttach = em.getReference(utilisateurListUtilisateurToAttach.getClass(), utilisateurListUtilisateurToAttach.getId());
                attachedUtilisateurList.add(utilisateurListUtilisateurToAttach);
            }
            role.setUtilisateurList(attachedUtilisateurList);
            em.persist(role);
            for (Utilisateur utilisateurListUtilisateur : role.getUtilisateurList()) {
                Role oldIdRoleOfUtilisateurListUtilisateur = utilisateurListUtilisateur.getIdRole();
                utilisateurListUtilisateur.setIdRole(role);
                utilisateurListUtilisateur = em.merge(utilisateurListUtilisateur);
                if (oldIdRoleOfUtilisateurListUtilisateur != null) {
                    oldIdRoleOfUtilisateurListUtilisateur.getUtilisateurList().remove(utilisateurListUtilisateur);
                    oldIdRoleOfUtilisateurListUtilisateur = em.merge(oldIdRoleOfUtilisateurListUtilisateur);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Role role) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Role persistentRole = em.find(Role.class, role.getId());
            List<Utilisateur> utilisateurListOld = persistentRole.getUtilisateurList();
            List<Utilisateur> utilisateurListNew = role.getUtilisateurList();
            List<Utilisateur> attachedUtilisateurListNew = new ArrayList<Utilisateur>();
            for (Utilisateur utilisateurListNewUtilisateurToAttach : utilisateurListNew) {
                utilisateurListNewUtilisateurToAttach = em.getReference(utilisateurListNewUtilisateurToAttach.getClass(), utilisateurListNewUtilisateurToAttach.getId());
                attachedUtilisateurListNew.add(utilisateurListNewUtilisateurToAttach);
            }
            utilisateurListNew = attachedUtilisateurListNew;
            role.setUtilisateurList(utilisateurListNew);
            role = em.merge(role);
            for (Utilisateur utilisateurListOldUtilisateur : utilisateurListOld) {
                if (!utilisateurListNew.contains(utilisateurListOldUtilisateur)) {
                    utilisateurListOldUtilisateur.setIdRole(null);
                    utilisateurListOldUtilisateur = em.merge(utilisateurListOldUtilisateur);
                }
            }
            for (Utilisateur utilisateurListNewUtilisateur : utilisateurListNew) {
                if (!utilisateurListOld.contains(utilisateurListNewUtilisateur)) {
                    Role oldIdRoleOfUtilisateurListNewUtilisateur = utilisateurListNewUtilisateur.getIdRole();
                    utilisateurListNewUtilisateur.setIdRole(role);
                    utilisateurListNewUtilisateur = em.merge(utilisateurListNewUtilisateur);
                    if (oldIdRoleOfUtilisateurListNewUtilisateur != null && !oldIdRoleOfUtilisateurListNewUtilisateur.equals(role)) {
                        oldIdRoleOfUtilisateurListNewUtilisateur.getUtilisateurList().remove(utilisateurListNewUtilisateur);
                        oldIdRoleOfUtilisateurListNewUtilisateur = em.merge(oldIdRoleOfUtilisateurListNewUtilisateur);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = role.getId();
                if (findRole(id) == null) {
                    throw new NonexistentEntityException("The role with id " + id + " no longer exists.");
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
            Role role;
            try {
                role = em.getReference(Role.class, id);
                role.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The role with id " + id + " no longer exists.", enfe);
            }
            List<Utilisateur> utilisateurList = role.getUtilisateurList();
            for (Utilisateur utilisateurListUtilisateur : utilisateurList) {
                utilisateurListUtilisateur.setIdRole(null);
                utilisateurListUtilisateur = em.merge(utilisateurListUtilisateur);
            }
            em.remove(role);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Role> findRoleEntities() {
        return findRoleEntities(true, -1, -1);
    }

    public List<Role> findRoleEntities(int maxResults, int firstResult) {
        return findRoleEntities(false, maxResults, firstResult);
    }

    private List<Role> findRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Role.class));
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

    public Role findRole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Role.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Role> rt = cq.from(Role.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
