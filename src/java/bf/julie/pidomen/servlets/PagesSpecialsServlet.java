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

/**
 *
 * @author ilboudo
 */
public class PagesSpecialsServlet extends HttpServlet {

    public String vue="";
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        vue="/Membres/PagesSpecialsContenu.jsp";
        this.getServletContext().getRequestDispatcher(vue).forward(req, resp);
        

    //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
