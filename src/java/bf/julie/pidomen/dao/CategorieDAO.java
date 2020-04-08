/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bf.julie.pidomen.dao;

import bf.julie.pidomen.entites.Categorie;
import java.util.List;

/**
 *
 * @author hp
 */
public class CategorieDAO extends DAOAbstrait<Categorie , Long>{

    @Override
    public Class<Categorie> getClassType() {
       return Categorie.class;
    }
    
    public List<Categorie> getUnusedCategories(){
    
        return em.createQuery("from Categorie c where c.id not in (select p.categorie.id from PageHasCategorie p)" , Categorie.class).getResultList();
        
    }
    
    public List<Categorie> getCatWithoutCat(){
        return em.createQuery("from Categorie c where c.sousCategorie is null",Categorie.class).getResultList();
    }
    
    public List<Categorie> getAllCat(){
    return em.createQuery("from Categorie c",Categorie.class).getResultList();
    }
}
