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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ilboudo
 */
public class AccueilAdminServlet extends HttpServlet {

    
    public  String vue="/Administrateur/AccueilAdmin.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        doGet(req, resp);
         //this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
       // super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         HttpSession session=req.getSession();
        vue="/Connexion.jsp";
       // session.removeAttribute("login");
        if(session.getAttribute("login")==null){
             this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        }else{
            
          
            
            if(session.getAttribute("role").equals("Membre")){
                vue="/Membres/AccueilMembre.jsp";
                 this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
            }
            if(session.getAttribute("role").equals("Administrateur")){
                 vue="/AccueilAdmin";
                 this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
                 
            }
        }
      //  super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
