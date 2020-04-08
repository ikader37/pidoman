
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
                <h1>Mots clé les plus utilisés</h1>
                <div class="row"> 
                    <div class="reche col-lg-10 offset-1">
                        <form method="get" class="form-inline" action="MotsClePlusDemandes.jsp "  placeholder="search" > 
                            <label class="col-lg-3" for="search">Entrez un seuil</label>
                            <input class="form-control col-lg-6" type="text" name="seuil" id="txtKeyword"/>
                            <button class="col-lg-2" type="submit" class="btn btn-primary">Rechercher</button>
                        </form>
                    </div>
                </div><br>

                <div class = "col-md-10">
                    <table class = "table table-bordered table-striped">
                        <tr>
                            <th>Mot clé</th>
                            <th>Nombre de fois utilisé</th>

                        </tr>

                        <%
                            int seuil;
                            try{
                                seuil = Integer.valueOf(request.getParameter("seuil"));
                            }
                            
                            catch (Exception ex) {
                                seuil = 5;
                            }
                            MotsClesDAO dao = new MotsClesDAO(); 
                            for (Object[] c : dao.getMotClesCountByLabel(seuil)) {
                                %>
                                <tr>
                                    <td> <%= c[0]%> </td>
                                    <td> <%= c[1]%> </td>
                                </tr>
                        <%}
                        %>

                    </table>

                </div>     
            </div>
        </div>

    </body>
</html>