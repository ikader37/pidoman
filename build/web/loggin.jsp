<%-- 
    Document   : loggin.jsp
    Created on : 10 sept. 2018, 15:09:08
    Author    : Julie
--%>
<%@ page import ="bf.julie.pidomen.entites.*" %>
<%@ page import ="bf.julie.pidomen.dao.*" %>
<%

    try {

        UtilisateurDAO dao = new UtilisateurDAO();
        String email = request.getParameter("email");
        String motpass = request.getParameter("motpass");
        Utilisateur u = dao.getUtilisateur(email, motpass);
        if (u != null) {
            session.setAttribute("id", u.getId());
            session.setAttribute("login", u.getLogin());
            session.setAttribute("utilisateur", u);
            out.print(u);
            out.println("Valid login credentials");
            response.sendRedirect("index1.jsp");

        } 
        else 
        {
            out.println("invalid");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println(e.getLocalizedMessage());

    }
%>