<%-- 
    Document   : AllPage
    Created on : 30 sept. 2018, 15:57:20
    Author     : pavilion15
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>



<link rel="stylesheet" href="bootstrap.min.css">
<script type="text/javascript" src="jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="bootstrap.js"></script>
<script type="text/javascript" src="bootstrap-tab.js"></script>
<script type="text/javascript" src="bootstrap-modal.js"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pidomen</title>
    </head>
    <body>
        <div class="row">

            <div class=" table-responsive col-lg-10 offset-1" style="margin-top:10px;">
                <h1>Liste de toutes les pages</h1><br>
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>Titre</th>
                            <th>Mot clé</th>   
                            <th>Contenu</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            PageDAO dao = new PageDAO();
                            List<Page> pages = dao.getAllPag();
                            for (Page p : dao.getAllPag()) {
                        %>
                        <tr>
                            <td><%=p.getTitre()%> </td>
                            <td><%=dao.getMotsCleString(p.getId()) %></td>
                            <td><%=p.getContenu()%></td>
                            <td> <a href="View_page.jsp?idpage=<%=p.getId()%>">Voir plus</a></td>
                        </tr>
                        <% }
                        %>


                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
