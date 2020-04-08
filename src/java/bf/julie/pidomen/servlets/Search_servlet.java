/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import bf.julie.pidomen.dao.PageDAO;
import bf.julie.pidomen.entites.Page;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ilboudo
 */

@WebServlet(name = "search",urlPatterns = "/search")
public class Search_servlet extends HttpServlet{

    
    public static String vue="/Membres/SearchResults.jsp";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
        doGet(req, resp);
      super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        String sujet=req.getParameter("sujet");
        PageDAO dao=new PageDAO();
        List<Page> p= dao.resultSearch(sujet);
        req.setAttribute("results",p);
        System.out.println("OK :"+p.size());
        this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        
        
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
}
