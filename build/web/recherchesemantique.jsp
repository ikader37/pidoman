<%-- 
    Document   : recherchesemantique
    Created on : 28 sept. 2018, 13:51:41
    Author     : ORDINATEUR
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stl" uri="http://ns.inria.fr/sewese/semtags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<!DOCTYPE html>
<%@include file="Headers/Headers.jsp" %>
<div class="row">
    <%@include file="Menus/MenuMembres.jsp" %>
    <div class="col-lg-6 col-sm-6 col-xl-6 col-md-6 col-xs-6">
        <div class="row">
            <div class="rech col-lg-12">
                <form method="get" class="form-inline" action="recherchesemantique.jsp "   > 
                    <label class="col-lg-3" for="search"></label>
                    <input class="form-control col-lg-6" placeholder="Recherche sémantique" type="text" name="mot_cle_r"/>
                    <button class="col-lg-2" type="submit" class="btn btn-primary">Rechercher</button>
                </form>
            </div>
        </div>
        <div align=""> <br/><br/> 
            <c:choose>

                <c:when test="${!empty param['mot_cle_r']}">
                    <ul>
                        <li><b>Vous recherchez:</b>  ${param['mot_cle_r']}</li>
                            <stl:for-each-result query="SELECT ?x ?mots ?contenu ?titre WHERE{ ?x rdf:type plateforme:Page . ?x plateforme:motcle ?mots. ?x plateforme:contenu ?contenu. ?x plateforme:nom ?titre. FILTER ((?mots ~ '${param['mot_cle_r']}') ).}">
                  <li> <!--a href="recherchesemantique.jsp?uri3=${stl:encode(R)}"> ${x}</a--><br/><u><b>Titre:</b></u> ${titre}</li><br/><u><b>Mots Cles:</b></u> ${mots}</li><br/><u><b>Contenu:</b></u> ${contenu}</li><br/>
                      <!--li><u>${x}(${l})</u></li-->  
                            <%--								
                          <stl:for-each-result query="SELECT ?x ?l WHERE{ ?x ?t owl:Class .?x rdfs:label ?l . FILTER (?x ~ 'idomen') . FILTER (?l ~ '${param['mot_cle_r']}'). filter (lang(?l) = 'fr')}">
                  <!--    <li><u>${x}(${l})</u></li>  -->
                            --%>  
                            <%--     <ul>
                               
                             <stl:for-each-result query="SELECT ?mots ?P ?R WHERE { ?R catalog:declarer_par ?P . ?R catalog:mots_cles ?mots . ?R rdf:type catalog:Expertise. FILTER ((?mots ~ '${l}') || (?mots ^ '${l}'))}">
                                     <li> <a href="recherchesemantique.jsp?uri3=${stl:encode(R)}"> ${R}</a> <br/><u><b>Decrit par:</b></u><a href="browseCatalog.jsp?uri=${stl:encode(P)}">(${P})</a> <br/><u><b>Mots Cles:</b></u> ${mots}</li><br/>
                             </stl:for-each-result>
                                
                           </ul>--%>
                        </stl:for-each-result>
                    </ul>                                                 
                </c:when>
            </c:choose>
        </div>

    </div>
    <%@include  file="right/right.jsp" %>
    <%@include file="Footers/Footer.jsp" %>
</div>












