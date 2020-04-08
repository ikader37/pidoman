<%-- 
    Document   : logout.jsp
    Created on : 14 sept. 2018, 15:48:39
    Author     : Julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>