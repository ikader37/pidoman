/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import bf.julie.pidomen.entites.Categorie;
import static bf.julie.pidomen.servlets.AccueilMembre.vue;
/**
 *
 * @author ilboudo
 */


public class CategoriesServlet extends HttpServlet {

    
    public static final String vue="/Administrateur/listCategorie.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LIB  "+req.getParameter("idlibcat"));
        
        System.err.println("DESC  "+req.getParameter("iddesccat"));
        doGet(req, resp);
       // super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
        List<Categorie> listCat=Utilities.Utiles.catJpa.findCategorieEntities();
        req.setAttribute("categories", listCat);
        this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
    }
    
    
}
