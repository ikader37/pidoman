<%-- 
    Document   : search
    Created on : 14 sept. 2018, 18:46:04
    Author     : Julie
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

        <%
            String keyword = "";
            if (request.getParameter("txtKeyword") != null) {
                keyword = request.getParameter("txtKeyword");
            }
        %>
        <div class="row"> 
            <div class="reche col-lg-10 offset-1">
                <div class="reche col-lg-10 offset-1">
                    <form method="get" class="form-inline" action="search.jsp "  placeholder="search" > 
                        <label class="col-lg-3" for="search">Afficher toutes les pages commencant par</label>
                        <input class="form-control col-lg-6" type="text" name="txtKeyword" id="txtKeyword" value="<%=keyword%>"/>
                        <button class="col-lg-2" type="submit" class="btn btn-primary">Rechercher</button>
                    </form></div>
            </div>
        </div>
        <div class="row" style = "margin-top:20px;margin-left: 10px;"> 
            <table class = "table table-bordered table-striped">
                <tr>
                    <th>Titre</th>
                    <th>Mot clés</th>
                    <th>Contenu</th>
                </tr>
                <%
                    PageDAO dao = new PageDAO();
                    List<Page> pages = dao.getByTitle(keyword);
                    for (Page p : pages) {
                %>
                <tr>
                    <td><%=p.getTitre()%></td>
                    <td><%=dao.getMotsCleString(p.getId())%></td>
                    <td><%=p.getDisplayableContent()%></td>

                </tr>
                <%}%>
            </table>
        </div>


    </body>
    <div class="col-lg-2 offset-5">
        <a href="index.jsp" style="font-size: 15px;">Acceuil</a>
    </div>
</html>
