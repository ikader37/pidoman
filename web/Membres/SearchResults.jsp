<%-- 
    Document   : SearchResults
    Created on : 7 oct. 2019, 18:55:13
    Author     : ilboudo
--%>
<%@include file="../Headers/Headers.jsp" %>
<div class="row" style="overflow-y: scroll">
    <%@include file="../Menus/MenuMembres.jsp" %>

    <div class="col-lg-6 col-xs-6 col-md-6 col-sm-6">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
                <center>
                    <h1>RESULTATS DE LA RECHERCHE</h1>
                </center>
            </div>
        </div>

        <c:if test="${results.size()>0}">

            <c:forEach items="${results}" var="p">
                <form action="details" method="get">
                    <div class="row">
                        <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12">
                            <div class="row">
                                <div class="col-lg-12">

                                    <h3><center>${p.getTitre()}</center></h3>

                                </div>

                            </div>
                            <div class="row">
                                <div class="col-lg-5 col-md-5 col-xs-5 col-sm-5 col-xl-5">
                                    <span class="text-danger">Mot(s) clé(s): ${p.getKeyWords()}</span>
                                </div>
                               
                                <div class="col-lg-2 col-md-2">
                                    <input name="id" value="${p.getId()}" type="hidden">
                                    <right><button class="btn btn-primary" type="submit">Lire la suite ...</button></right>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
            </c:forEach>

        </c:if>
        <c:if test="${results.size()<=0}">
            <div class="row">
                <div class="col-lg-12">
                    AUCUN RESULTAT
                </div>
            </div>
        </c:if>


    </div>
    <%@include  file="../right/right.jsp" %>
    <%@include file="../Footers/Footer.jsp" %>
</div>
</body>
</html>
