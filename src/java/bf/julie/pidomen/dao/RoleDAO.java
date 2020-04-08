/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.dao;

import bf.julie.pidomen.entites.Role;
import bf.julie.pidomen.entites.Utilisateur;

/**
 *
 * @author ilboudo
 */
public class RoleDAO extends DAOAbstrait<Role, Long>{

    @Override
    public Class<Role> getClassType() {
        return Role.class;
    }
    
    public Role getRole(int id){
        try {
            return em.find(Role.class, id);
            
        } catch (Exception e) {
            return null;
        }
       
       // return null;
    }

    public RoleDAO() {
        
    }
    
    
    
}
