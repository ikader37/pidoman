/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.dao;

import bf.julie.pidomen.entites.PageHasMotCle;

/**
 *
 * @author hp
 */
public class PageHasMotsClesDAO extends DAOAbstrait<PageHasMotCle, Long> {

    @Override
    public Class<PageHasMotCle> getClassType() {
        return PageHasMotCle.class;
    }
}
