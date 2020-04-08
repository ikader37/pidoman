/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.dao;

import bf.julie.pidomen.entites.MotCle;
import java.util.List;

/**
 *
 * @author hp
 */
public class MotsClesDAO extends DAOAbstrait<MotCle, Long> {

    @Override
    public Class<MotCle> getClassType() {
        return MotCle.class;
    }

    public List<Object[]> getMotClesCountByLabel(int seuil) {
        List<Object[]> resultList = em.createNativeQuery("SELECT libelle , count(*) as nb_occurences FROM"
                + " `page_has_mot_cle` phmc inner join mot_cle mc on mc.ID = phmc.id_mot"
                + "_cle group by mc.LIBELLE HAVING nb_occurences >= " + seuil)
                .getResultList();
        return resultList;
    }
    
    public List<Object[]> getDistinct() {
        List<Object[]> resultList = em.createNativeQuery("SELECT DISTINCT LIBELLE FROM `mot_cle`")
                .getResultList();
        return resultList;
    }
}
