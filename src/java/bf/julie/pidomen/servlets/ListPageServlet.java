/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import bf.julie.pidomen.exec.*;
import java.io.IOException;
import bf.julie.pidomen.entites.*;
import bf.julie.pidomen.exec.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author ilboudo
 */
@WebServlet(name = "myPages",urlPatterns = "/myPages")
public class ListPageServlet extends HttpServlet{

    public String vue="";
    
    EntityManagerFactory emf;
    PageJpaController pjpaC;
    UtilisateurJpaController utJpaC;
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        
        emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
        
        pjpaC=new PageJpaController(emf);
        String idS=req.getParameter("idsup");
        Long id=Long.parseLong(idS);
        try {
            pjpaC.destroy(id);
            
           req.setAttribute("erreur", "false");
            
        } catch (NonexistentEntityException ex) {
            
            req.setAttribute("erreur", "true");
            
            Logger.getLogger(ListPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        doGet(req, resp);
        
       // super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         HttpSession session=req.getSession();
        emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
        
        pjpaC=new PageJpaController(emf);
        utJpaC=new UtilisateurJpaController(emf);
        
        if(session.getAttribute("login")==null){
            vue="/Connexion.jsp";
            
            this.getServletContext().getRequestDispatcher( vue ).forward( req, resp );
        }else{
            //Retrouvons l'utilisateur en question
            vue="/Membres/ListMesPages.jsp";
            
            Utilisateur user=utJpaC.findUtilisateur(Long.valueOf(session.getAttribute("id").toString()));
            
            req.setAttribute("utilisateur", user);
            this.getServletContext().getRequestDispatcher( vue ).forward( req, resp );
        }
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
