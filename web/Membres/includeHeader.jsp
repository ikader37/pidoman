<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Idomen</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">


    </head>

    <body style="background: white; color: black; overflow: scroll" >

    <header>
        <div class="row">
            <div class="col-lg-2" >
                <img  src="images/ll.jpg" class="img-circle">
            </div>
            <div class="col-lg-4 col-xl-4 col-md-4 col-sm-4">
                <form action="search" method="get">
                    <input name="sujet" type="text" placeholder="Rechercher" />
                    <input  class="btn btn-primary" type="submit" value="Rechercher" />
                </form>
            </div>
            <div class=" col-lg-offset-2  col-lg-4 col-xl-4 col-md-4 col-sm-4">
                <div class="bouton" >

                    <%
                        System.out.println(session.getAttribute("id"));
                        if ((session.getAttribute("id") == null) || (session.getAttribute("id") == "")) {%>
                    <a type="button" class="btn btn-primary btn-sm btn" href="Connexion">Se connecter</a>
                    <a type="button" class="btn btn-primary btn-sm" href="inscription"> S'inscrire</a>
                    <%
                    } else {%>
                    <div class="bouton" style="margin-left: 120px;">
                        Welcome <%=session.getAttribute("login")%>

                        <a type="button" href="deconnexion">Deconnexion</a>
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
    </header>