/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import bf.julie.pidomen.entites.Categorie;
import bf.julie.pidomen.exec.CategorieJpaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ilboudo
 */
public class ListCategorieServlet extends HttpServlet{

    
    public String vue="/Membres/ListCategoriesSimple.jsp";
    
    public EntityManagerFactory emf ;//= null;
     
    CategorieJpaController catJPA;
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        System.out.println("POOOOOO!!!!");
        
         emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
        catJPA=new CategorieJpaController(emf);
        
        List<Categorie> listCat=new ArrayList<>();
        listCat=catJPA.findCategorieEntities();
        req.setAttribute("categories", listCat);
        System.out.println("POOOOOO!!!!");
         this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        
        
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
