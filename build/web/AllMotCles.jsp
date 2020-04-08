<%-- 
    Document   : AllCategorie
    Created on : 2 oct. 2018, 16:02:40
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
<style>
    .form-group{margin-top: 2px;}
</style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pidomen</title>
    </head>
    <body>
        <div class="row">


            <div class="col-lg-10 offset-1" style="margin-top:10px;">
                <h1>Liste des mots cles</h1><br>
                <ul>
                    <%
                        MotsClesDAO dao = new MotsClesDAO();
                        List<Object[]> distinct = dao.getDistinct();
                        for (int i = 0, l = distinct.size(); i < l; i++) {
                    %>

                    <li><%=distinct.get(i)%></li>
                        <% }
                        %>
                </ul>


            </div>
        </div>
    </body>
</html>

