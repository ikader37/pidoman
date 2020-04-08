/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bf.julie.pidomen.dao;

import bf.julie.pidomen.entites.PageHasCategorie;
import java.util.List;

/**
 *
 * @author hp
 */
public class PageHasCategorieDAO extends DAOAbstrait<PageHasCategorie , Long>{

    @Override
    public Class<PageHasCategorie> getClassType() {
        return PageHasCategorie.class;
    }
    
    public List<Object[]> getTenMostUsedCategories() {
        String query = "SELECT c.nom, count(id_categorie) as nbOccurences , c.id FROM page_has_categorie phc"
                + " RIGHT JOIN categorie c on c.id = phc.id_categorie GROUP BY id_categorie,c.nom"
                + " ORDER BY nbOccurences DESC LIMIT 10";        
        return em.createNativeQuery(query).getResultList();
    }
    
    public Object getOtherCategoriesCount(String excludeList) {
        String query = String.format("SELECT count(id_categorie) FROM `page_has_categorie` where id_categorie NOT IN (%s)" , excludeList);
        return em.createNativeQuery(query).getSingleResult();
    }
    
    
}
