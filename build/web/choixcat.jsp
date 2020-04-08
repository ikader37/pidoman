<%-- 
    Document   : choixcat
    Created on : 20 oct. 2018, 09:12:13
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
         <H1>Reading Multiple Selection Select Controls</H1>
        You selected:
        <BR>
        <%  
            String[] items = request.getParameterValues("select1");
            for(int loopIndex = 0; loopIndex < items.length; loopIndex++){
            out.println(items[loopIndex] + "<BR>");
        }
        %>
    </body>
</html>
