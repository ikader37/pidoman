<%-- 
    Document   : modif
    Created on : 14 sept. 2018, 23:59:35
    Author     : Julie
--%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>
<%
    Long idPage = Long.valueOf((String)session.getAttribute("idpage"));
    String titre = request.getParameter("titre");
    String contenu = request.getParameter("contenu");
    String motsCle = request.getParameter("motcle");
    PageDAO dao = new PageDAO();
    //MotsClesDAO m=new MotsClesDAO();
   // MotCle mot=new MotCle();
    Page p = dao.getById(idPage);
    p.setContenu(contenu);
    p.setTitre(titre);
    dao.mettreAJour(p , motsCle);
    out.print("Modification reussie");
%>
