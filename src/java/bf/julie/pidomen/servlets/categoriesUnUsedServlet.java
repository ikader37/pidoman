/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import bf.julie.pidomen.dao.CategorieDAO;
import bf.julie.pidomen.entites.*;
import java.util.*;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bf.julie.pidomen.exec.*;
import javax.persistence.Persistence;

/**
 *
 * @author ilboudo
 */
public class categoriesUnUsedServlet extends HttpServlet{
    
    public String vue="/Membres/CategoriesNonUsed.jsp";
    
    public EntityManagerFactory emf ;//= null;
     
    CategorieJpaController catJPA;
    
    
   // CategorieDAO caDAO=new CategorieDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
        catJPA=new CategorieJpaController(emf);
        
        List<Categorie> listCat=new ArrayList<>();
        listCat=catJPA.findCategorieEntities();
        List<Categorie> listCatUnUsed=new ArrayList<>();
        
        for(int i=0;i<listCat.size();i++){
            if(listCat.get(i).getPageHasCategorieList().size()<=0){
                
                listCatUnUsed.add(listCat.get(i));
                System.out.println(listCat.get(i).getNom());
            }
        }
        
        req.setAttribute("categories", listCatUnUsed);
        
         this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
