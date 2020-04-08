<%-- 
    Document   : FichSansCat
    Created on : 12 oct. 2018, 10:53:36
    Author     : pavilion15
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>



<!DOCTYPE html>
<html
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="bootstrap.min.css">
    <script type="text/javascript" src="jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="bootstrap.js"></script>
    <script type="text/javascript" src="bootstrap-tab.js"></script>
    <script type="text/javascript" src="bootstrap-modal.js"></script>
    <style>
        .reche {margin-top: 10px;}
    </style>

    <body>
        <div class="row">
            <div class="col-lg-10 offset-1">
                <h1>Pages sans categories</h1>
            </div>
        </div>
        <div class="col-lg-10 offset-1">
           <table class="table table-hover table-bordered table-striped">
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
                            List<Page> pages = dao.getPageWithoutCat();
                            for (Page p : pages)
                            {
                        %>
                        <tr>
                            <td><%=p.getTitre()%> </td>
                            <td><%=dao.getMotsCleString(p.getId())%></td>
                            <td><%=p.getContenu()%></td>
                            <td>
                                <a href="View_page.jsp?idpage=<%=p.getId()%>">Voir plus</a>
                            </td>

                        </tr>
                        <% }
                        %>


                    </tbody>
                </table>
        </div>
        <div class="col-lg-2 offset-5">
            <a href="index.jsp" style="font-size: 15px;">Acceuil</a>
        </div>
    </body>
</html>
