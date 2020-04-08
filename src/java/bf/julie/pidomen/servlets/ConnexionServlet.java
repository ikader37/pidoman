/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import bf.julie.pidomen.dao.UtilisateurDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import bf.julie.pidomen.entites.Utilisateur;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bf.julie.FormsControl.SignUpForm;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ilboudo
 */
public class ConnexionServlet extends HttpServlet{

    public String vue="/Connexion.jsp";
    UtilisateurDAO udao=new UtilisateurDAO();
    public String menu="";
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm sg=new SignUpForm();
        System.out.println("BIN");
         HttpSession session=req.getSession();
        
        Utilisateur u= sg.connection(req);
        
        if(u==null){
            
            req.setAttribute("form", sg);
            req.setAttribute("user", u);
            req.setAttribute("erreur", true);
            
           
                
            this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
            
        }else{
            System.out.println(u);
            
            if(u.getIdRole().getLibelle().equalsIgnoreCase("Membre")){
                
                vue="/Membres/AccueilMembre.jsp";
                
                
                req.setAttribute("form", sg);
                req.setAttribute("user", (Utilisateur)u);
                req.setAttribute("menu", "../Menus/MenuMembres.jsp");
                
                session.setAttribute("login", u.getEmail());
                session.setAttribute("role", u.getIdRole().getLibelle());
                session.setAttribute("id", u.getId());
                //session.setAttribute(vue, u);
                
                this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
                
            }
            if(u.getIdRole().getLibelle().equalsIgnoreCase("Administrateur")){
                vue="AccueilAdmin";
                 session.setAttribute("login", u.getEmail());
                session.setAttribute("role", u.getIdRole().getLibelle());
                session.setAttribute("id", u.getId());
                resp.sendRedirect(vue);
               // this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
                
            }
            
        }
        
      //  super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
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
       
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
