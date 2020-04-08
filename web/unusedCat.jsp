<%-- 
    Document   : unusedCat
    Created on : 12 oct. 2018, 10:49:56
    Author     : pavilion15
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>
<%@ page import="java.util.*" %>
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
                <h1>Categories inutilisées</h1>
                <div class="col-lg-12 form-group">
                    <label class="col-lg-4" for="search">Seuil de categorie:</label>
                    <input class="form-control col-lg-8" type="text" name="seuil"/>
                </div>
                <div class = "col-md-10">
                    <table class = "table table-bordered table-striped">
                        <tr>
                            <th>Nom Categorie</th>
                            <th>Sous categorie</th>

                        </tr>
                        <%
                            CategorieDAO dao = new CategorieDAO();
                            List<Categorie> categories = dao.getUnusedCategories();
                            for (Categorie c : categories) {
                            %>
                            <tr>
                                <td><%=c.getNom() %> </td>
                                <td><%=c.getSousCategorieString() %> </td>
                            </tr>
                        <%}
                        %>


                    </table>

                </div>            
            </div>
    </body>
</html>
