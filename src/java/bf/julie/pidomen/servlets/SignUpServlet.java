/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import bf.julie.pidomen.dao.UtilisateurDAO;
import bf.julie.pidomen.entites.Utilisateur;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bf.julie.FormsControl.SignUpForm;

/**
 *
 * @author ilboudo
 */
public class SignUpServlet extends HttpServlet {
    UtilisateurDAO dao = new UtilisateurDAO();
    
    public String vue="/Inscription.jsp";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        SignUpForm g=new SignUpForm();
        g.inscrireUtilisateur(request);
        request.setAttribute("form", g);
        if(g.getResultat().equalsIgnoreCase("bien")){
            //vue="/index.jsp";
            this.getServletContext().getRequestDispatcher(vue).forward( request, response );
        }else{
            this.getServletContext().getRequestDispatcher(vue).forward( request, response );
        }
        
       
      
        
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( vue ).forward( request, response );
    }
    
   
    
    
}
