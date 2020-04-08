<%-- 
    Document   : registration
    Created on : 10 sept. 2018, 17:23:48
    Author     : Julie
--%>
<%@ page import ="bf.julie.pidomen.entites.*" %>
<%@ page import ="bf.julie.pidomen.dao.*" %>
<%
 try {
     
      UtilisateurDAO dao = new UtilisateurDAO();
      //Cherchons d'abord si un utilisateur avec ce login existe deja
      
      
    //  
      String nom =request.getParameter("nom"); 
      String prenom=request.getParameter("prenom"); 
      String login=request.getParameter("login"); 
      String motpass=request.getParameter("motpass"); 
      String email=request.getParameter("email"); 
      Utilisateur u = new Utilisateur(nom, prenom, login, motpass, email);
      //Utilisateur ut=dao.getUtilisateurByEmail(email);
      
      dao.creer(u);
      response.sendRedirect("index.jsp");
    }
    catch(Exception e) {
        
        out.println("SQLException caught: " +e.getMessage());
    }
%>

