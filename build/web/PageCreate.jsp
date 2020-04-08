<%-- 
    Document   : PageCreate
    Created on : 12 sept. 2018, 18:32:25
    Author     : Julie
--%>
<%@page import="bf.julie.pidomen.dao.CategorieDAO"%>
<%@ page import="java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="java.util.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>



<!DOCTYPE html>
<html>
    <!-- Bootstrap core CSS -->
    
    <style>
        .form-group{margin-top: 2px;}
    </style>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="img/favicon.png">
        
  <link href="Admin/css/bootstrap.min.css" rel="stylesheet">
  <!-- bootstrap theme -->
  <link href="Admin/css/bootstrap-theme.css" rel="stylesheet">
  <!--external css-->
  <!-- font icon -->
  <link href="Admin/css/elegant-icons-style.css" rel="stylesheet" />
  <link href="Admin/css/font-awesome.min.css" rel="stylesheet" />
  <link href="Admin/css/daterangepicker.css" rel="stylesheet" />
  <link href="Admin/css/bootstrap-datepicker.css" rel="stylesheet" />
  <link href="Admin/css/bootstrap-colorpicker.css" rel="stylesheet" />
  <!-- date picker -->

  <!-- color picker -->

  <!-- Custom styles -->
  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet" />
    </head>
    <body>


        <!--%= session.getAttribute( "id" ) %-->
        
        <div class="container">
        <%
            System.out.println(session.getAttribute("id"));
            if ((session.getAttribute("id") == null) || (session.getAttribute("id") == "")) {

        %>       
        <script>alert("veuillez vous Connecter");</script>

        <%} else {
        %>



        <script>
            $(function () {
                $('#categories').change(function(e) {
                    var tab = $(this).val() || [];
                    var finalTab = [];
                    for(var i = 0 , l = tab.length; i < l ; i++) {
                        finalTab.push($('#' + tab[i]).html());
                    }
                    $('#selected-categories').html(finalTab.join(' , '));
                });
            });
        </script>
        </div>
    </body>
    <% }%>
    
</html>

