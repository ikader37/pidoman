/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import Utilities.Utiles;
import bf.julie.pidomen.dao.PageDAO;
import bf.julie.pidomen.entites.Commentaire;
import bf.julie.pidomen.entites.Page;
import static bf.julie.pidomen.servlets.DetailsServlet.vue;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ilboudo
 */
@WebServlet(name = "commentaire" ,urlPatterns = "/commentaires")
public class CommentaireServlet extends  HttpServlet{
    
    public static String vue="/Membres/DetailsPages.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        
        this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
