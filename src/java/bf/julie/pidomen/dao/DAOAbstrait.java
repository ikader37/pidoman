/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bf.julie.pidomen.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author hp
 * @param <T>
 * @param <P>
 */
public abstract class DAOAbstrait <T , P> {
    protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("PlateformeMeningitPU");
    protected EntityManager em = factory.createEntityManager();
    protected EntityTransaction transaction = em.getTransaction();
    
    public void creer(T t)
    {
        transaction.begin();
        em.persist(t);
        transaction.commit();
    }
    
    public List<T> getAll() {
        String className = getClassType().getSimpleName();
        String query = String.format("from %s %s" , className , className.toLowerCase().charAt(0));
        return em.createQuery(query , getClassType()).getResultList();
    }
    
    public T getById(P id) {
        return em.find(getClassType() , id);
    }
    
    public void mettreAJour(T t) {
        transaction.begin();
        em.merge(t);
        transaction.commit();
    }
    
    public abstract Class<T> getClassType();
    
}
