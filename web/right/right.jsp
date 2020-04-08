<%-- 
    Document   : right
    Created on : 24 oct. 2019, 18:32:38
    Author     : ilboudo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stl" uri="http://ns.inria.fr/sewese/semtags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Base64"%>
<%@page import="java.io.OutputStream"%>
<%@page import="bf.julie.pidomen.entites.Page"%>
<%@page import="java.util.List"%>
<div class="col-lg-3 col-xl-3 col-md-3 col-xs-3" style="background:  whitesmoke; overflow-x: auto">
    <div  class="row">
        <div class="col-lg-12 col-xl-12 col-sm-12 col-xs-12">
            <h1>Pages recentes</h1>
        </div>
    </div>
    <% List<Page> pList = Utilities.Utiles.pdao.pageRecent();
        if (pList.size() > 0) {

            for (int i = 0; i < pList.size(); i++) {
               // byte[] img1=pList.get(i).getImg1();
               // response.setContentType("UTF-8");
               // String encodage=Base64.getEncoder().encodeToString(img1);
                
                
               // System.err.println("IMG 1:"+img1+" \n encode :" +encodage);
%>
    <form action="details" method="get">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-xl-8 col-xs-8">
                <input name="id" value=<%=pList.get(i).getId()%> type="hidden">
                <li><a  href="#"><%=pList.get(i).getTitre()%></a></li>
               
                <p><%= pList.get(i).getKeyWords()%></p>
            </div>
            <div class="col-lg-4 col-xl-4 col-sm-4 col-xs-4 col-md-4">
                <button type="submit" class="btn btn-primary">Lire la suite...</button>
            </div>
        </div>
    </form>
    <%
            }

        } else {
            System.out.println("AUCUN RESULTAT.");
        }
    %>
</div>
