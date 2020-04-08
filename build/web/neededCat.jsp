
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
                <h1>Categories les plus demandées</h1>
                <div class="row"> 
                    <div class="reche col-lg-10 offset-1">
                        <form method="get" class="form-inline" action="neededCat.jsp "  placeholder="search" > 
                            <label class="col-lg-3" for="search">Entrez le titre de la page</label>
                            <input class="form-control col-lg-6" type="text" name="seuil" id="txtKeyword"/>
                            <button class="col-lg-2" type="submit" class="btn btn-primary">Rechercher</button>
                        </form>
                    </div>
                </div>

                <div class = "col-md-10">
                    <table class = "table table-bordered table-striped">
                        <tr>
                            <th>Nom de la categorie</th>
                            <th>Nombre de fois utilisé</th>

                        </tr>

                        <%
                            int seuil = (request.getParameter("seuil") == null) ? 10 : Integer.valueOf(request.getParameter("seuil"));
                                CategorieDAO dao = new CategorieDAO(); 
                                for (Categorie c : dao.getAll()) {
                                List<PageHasCategorie> pageHasCategories = c.getHasCategories();
                                if (pageHasCategories.size() < seuil) {
                                    continue;
                                }
                                
                      %>
                                <tr>
                                    <td> <%= c.getNom()%> </td>
                                    <td> <%= pageHasCategories.size()%> </td>
                                </tr>
                        <%}
                        %>

                    </table>

                </div>     
            </div>
        </div>

    </body>
</html>