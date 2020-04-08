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
@WebServlet(name = "details", urlPatterns = "/details")
public class DetailsServlet extends HttpServlet {

    public static final String vue = "/Membres/DetailsPages.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idPage = req.getParameter("id");
        PageDAO daoP = new PageDAO();
        Page page = daoP.getById(Long.parseLong(idPage));

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String comment = req.getParameter("comment");
        String sujet=req.getParameter("sujet");
        
        Commentaire comm = new Commentaire();
        comm.setSujet(sujet);
        comm.setComment(comment);
        comm.setEmail(email);
        comm.setNompersonne(name);
        comm.setIdpage(page);
        try {

            Utiles.commentJp.create(comm);
            req.setAttribute("bon", true);

        } catch (Exception e) {
            req.setAttribute("bon", false);
        }
        PageDAO pageDAO = new PageDAO();
        Page p = pageDAO.getById(Long.parseLong(idPage));
        req.setAttribute("page", p);
        this.getServletContext().getRequestDispatcher(vue).forward(req, resp);

        //super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        // doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        PageDAO pageDAO = new PageDAO();
        Page p = pageDAO.getById(Long.parseLong(id));
        req.setAttribute("page", p);
        this.getServletContext().getRequestDispatcher(vue).forward(req, resp);

        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
