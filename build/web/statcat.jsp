<%-- 
    Document   : statcat
    Created on : 10 déc. 2018, 20:59:00
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String imageName = bf.julie.pidomen.util.Util.generateCategoriesPieChart(request.getServletContext().getRealPath("/"));
        %>
        <div class="row"> 
            <div class="col-lg-10">
                <div class="col-lg-10 offset-1" style="height: 30%;border: 1px solid whitesmoke">
                    <img src="<%= imageName %>">  
               </div>
            </div> 
        </div>
    </body>
</html>
