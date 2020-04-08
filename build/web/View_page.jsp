<%-- 
    Document   : UpdatePage
    Created on : 12 sept. 2018, 22:45:10
    Author     : Julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>
<!DOCTYPE html>
<html>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="bootstrap.min.css">
    <script ype="text/javascript" src="jquery-1.10.2.min.js"></script>
    <script ype="text/javascript" src="bootstrap.js"></script>
    <script ype="text/javascript" src="bootstrap-tab.js"></script>
    <script ype="text/javascript" src="bootstrap-modal.js"></script>
    <style>
        .rech {margin-top: 5px;}
    </style>
    <body>
        
        <%
            String keyword = "";
            if (request.getParameter("txtKeyword") != null) {
                keyword = request.getParameter("txtKeyword");
            }
        %>

        <%
            PageDAO dao = new PageDAO();
            Long idPage = Long.valueOf(request.getParameter("idpage"));
            request.getSession().setAttribute("idpage" , request.getParameter("idpage"));
            Page p = dao.getById(idPage);
        %>
        <div class="row">
            <div class="col-lg-10"><br>
                <form  method="post" action="modif.jsp" class="form-inline">
                    <div class="form-group col-lg-12">
                        <label for="titre" class="col-lg-2" >Titre</label>
                        <input type="text" class="form-control col-lg-10" id="titre" name="titre" placeholder="Entrez le titre de la page" value="<%=p.getTitre()%>">
                    </div>
                    <div class="form-group col-lg-12">
                        <label for="motcle" class="col-lg-2" >Mots clés</label>
                        <input type="text" class="form-control col-lg-10" id="motcle" name="motcle" placeholder="Entrez le mot clé" value="<%=dao.getMotsCleString(p.getId())%>">
                    </div>
                    <div class="form-group col-lg-12">
                        <label for="contenu" class="col-lg-2" >Contenu</label>
                        <textarea type="text" class="form-control col-lg-10" id="contenu" name="contenu" placeholder="Entrez le contenu " rows="15"><%=p.getContenu()%></textarea>
                    </div>
                </form>

            </div></div>

    </body>
</html>
