/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import bf.julie.pidomen.dao.PageDAO;
import bf.julie.pidomen.exec.CategorieJpaController;
import bf.julie.pidomen.exec.CommentaireJpaController;
import bf.julie.pidomen.exec.PageJpaController;
import bf.julie.pidomen.exec.RoleJpaController;
import bf.julie.pidomen.exec.UtilisateurJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ilboudo
 */
public class Utiles {
    
    public static EntityManagerFactory emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
    public static PageDAO pdao=new PageDAO();
    public static CommentaireJpaController commentJp=new CommentaireJpaController(emf);
    public static  RoleJpaController rjpa=new RoleJpaController(emf);
    public static UtilisateurJpaController uJpa=new UtilisateurJpaController(emf);
    public static PageJpaController pJpa=new PageJpaController(emf);
    public static CategorieJpaController catJpa=new CategorieJpaController(emf);
    
}
