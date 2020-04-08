/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bf.julie.pidomen.dao;

import bf.julie.pidomen.entites.Utilisateur;
import javax.persistence.NoResultException;

/**
 *
 * @author hp
 */
public class UtilisateurDAO extends DAOAbstrait<Utilisateur , Long>{
    
    public Utilisateur getUtilisateur(String email , String password) {
        try
        {
            return em.createQuery("from Utilisateur u where u.email =:email AND u.motDePasse =:password" , Utilisateur.class)
                .setParameter("email" , email)
                .setParameter("password" , password)
                .getSingleResult();
        }
        
        catch (NoResultException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        
    }
    
    //Pour verifier si lutilisateur existe ou pas
    public Utilisateur getUtilisateurByEmail(String email){
        try {
            return em.createQuery("from Utilisateur u where u.email =:email" , Utilisateur.class)
                .setParameter("email" , email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Class<Utilisateur> getClassType() {
        return Utilisateur.class;
    }
    
    public void getRoleUtilisateur(){
        
    }
    
}
