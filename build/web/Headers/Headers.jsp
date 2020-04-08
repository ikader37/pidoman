<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- 
    Document   : Headers
    Created on : 19 août 2019, 18:41:07
    Author     : ilboudo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stl" uri="http://ns.inria.fr/sewese/semtags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Pidomen</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css"> 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
    
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
    </head>

    <body id="container" class="container-fluid" style="background: white; color: black; " >
    <header>
        <div class="row">
            <div class="col-lg-2" >
                <a href="/PlateformeMeningit/" title="ACCUEIL"><img  src="images/ll.jpg" class="img-circle"></a>
            </div>
           
            <div class="col-lg-6 offset-1" style="background-color: whitesmoke">
                <center><form action="search" method="get">
                    <input name="sujet" type="text" placeholder="Rechercher" />
                    <input  class="btn btn-primary" type="submit" value="Rechercher" />
                </form></center>
            </div>
            <div class="col-lg-2">
                <div class="bouton" >

                    <%
                        System.out.println(session.getAttribute("id"));
                        if ((session.getAttribute("id") == null) || (session.getAttribute("id") == "")) {%>
                    <a type="button" class="btn btn-primary btn-sm btn but" href="Connexion">Se connecter</a>
                    <a type="button" class="btn btn-primary btn-sm" href="inscription"> S'inscrire</a>
                    <%
                    } else {%>
                    <div class="bouton" style="margin-left: 120px;">
                        <a href="accueil" type="button" class="btn btn-primary btn-sm"> Welcome <%=session.getAttribute("login")%></a>

                        <a type="button" href="deconnexion" class="btn btn-danger btn-sm">Deconnexion</a>
                    </div>

                    <%   }

                    %> 

                    <c:if test="${sessionScope.login!=null}">
                        <a></a>
                    </c:if>
                    <c:if test="${sessionScope.login==null}">
                    </c:if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xl-12">
                <img src="images/Cp.PNG" width="100%" height="10%">
            </div>
        </div>
    </header>