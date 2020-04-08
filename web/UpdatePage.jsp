<%-- 
    Document   : UpdatePage
    Created on : 12 sept. 2018, 22:45:10
    Author     : Julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
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

    <!--%= session.getAttribute( "id" ) %--!>
    <%
        String keyword = "";
        if (request.getParameter("txtKeyword") != null) {
            keyword = request.getParameter("txtKeyword");
        }
    %>
            <div class="row">
                <div class="rech col-lg-12">
                     <form method="get" class="form-inline" action="UpdatePage.jsp "  placeholder="search" > 
                        <label class="col-lg-3" for="search">Entrez le titre de la page</label>
                        <input class="form-control col-lg-6" type="text" name="txtKeyword" id="txtKeyword" value="<%=keyword%>"/>
                       <button class="col-lg-2" type="submit" class="btn btn-primary">Rechercher</button>
                    </form></div>
                </div>
    <%

        Connection connect = null;
        Statement s = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidomen_db?" + "user=root&password=");
            s = connect.createStatement();
            String sql = "SELECT * FROM  page WHERE titre = '" + keyword + "'";
            System.out.println(sql);
            ResultSet rec = s.executeQuery(sql);

    %>
        
        
    <%while ((rec != null) && (rec.next())) {
    %>
           <div class="row">
                <div class="col-lg-10"><br>
            <form  method="post" action="modif.jsp" class="form-inline">
                <div class="form-group col-lg-12">
                    <label for="titre" class="col-lg-2" >Titre</label>
                    <input type="text" class="form-control col-lg-10" id="titre" name="titre" placeholder="Entrez le titre de la page" value="<%=rec.getString("titre")%>">
                </div>
                <div class="form-group col-lg-12">
                    <label for="motcle" class="col-lg-2" >Mots clés</label>
                    <input type="text" class="form-control col-lg-10" id="motcle" name="motcle" placeholder="Entrez le mot clé" value="<%=rec.getString("motcle")%>">
                </div>
                <div class="form-group col-lg-12">
                    <label for="contenu" class="col-lg-2" >Contenu</label>
                    <textarea type="text" class="form-control col-lg-10" id="contenu" name="contenu" placeholder="Entrez le contenu " rows="15"><%=rec.getString("contenu")%></textarea>
                </div>
                
                <div class="form-group col-lg-12" style="">
                     <button class="col-lg-5" type="submit" class="btn btn-primary" data-dismiss="modal">Enregistrer</button>
                     <div class="col-lg-2"></div>
                    <button class="col-lg-5" type="submit" class="btn btn-primary" data-dismiss="modal">Annuler</button>
                </div>
            </form>
            
        </div></div>
    
    <%}%>

    <%
        } catch (Exception e) {
            out.println(e.getMessage());
            e.printStackTrace();
        }
        try {
            if (s != null) {
                s.close();
                connect.close();
            }
        } catch (SQLException e) {
            out.println(e.getMessage());
            e.printStackTrace();
        }
    %>
            </div>
        </body>
    </html>
