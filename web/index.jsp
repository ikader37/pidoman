
<%-- 
    Document   : index
    Created on : 20 juil. 2018, 15:27:48
    Author     : julie
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stl" uri="http://ns.inria.fr/sewese/semtags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page trimDirectiveWhitespaces="true" %>
 <c:if test="${empty applicationScope['defaultEngineWrapper']}">
            <stl:init ontoDir="WEB-INF/data/schemas/Ontology" annotDir="WEB-INF/data/annotations" ruleDir="WEB-INF/data/rules"  plateforme="http://www.semanticweb.org/ontologies/2017/9/IDOMEN/2.1/vcatalog.rdfs#"/>
        </c:if>
<%@include file="Headers/Headers.jsp" %>
<div class="row ">
    <%@include file="Menus/MenuMembres.jsp" %>
    
    <div class="col-lg-6 col-xs-6 col-md-6 col-sm-6">
        <div class="row">
            <div class="col-lg-12 col-sm-12 col-xl-12 col-md-12">
                <h1 style="font-weight: bold">Bienvenue sur WikiMeningitis, Votre wiki sur la m�ningite</h1><br>
                <p style="text-align: justify;">Le Centre de recherche en sant� de Nouna a connu ses d�buts en 1992 en collaboration avec
                    les partenaires Allemands de l?Institut de Sant� Publique de l?Universit� de Heidelberg sous
                    la d�nomination PRAPASS qui est le projet de recherche action pour l?am�lioration des soins
                    de sant� qui couvrait la Grande Kossi dont les limites s?�tendaient du Sourou actuel aux Banwa.
                    Les r�ponses apport�es aux questions et pr�occupations de sant� par le PRAPASS ont conduit le Minist�re
                    de la Sant� du Burkina Faso � la transformation institutionnelle en 1999 du PRAPASS en Centre de Recherche en 
                    Sant� de Nouna. Cette transformation lui a conf�r� un encrage institutionnel plus fort et une comp�tence �largie 
                    sur l?ensemble du territoire national et au del� des fronti�res du Burkina Faso. Au niveau national,
                    le CRSN entretient un partenariat scientifique et technique avec tous les instituts de recherche du Minist�re de la Sant� et les Universit�s au niveau national</p>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                <img src="images/D.jpg" width="100%">
            </div>
            <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                <img src="images/meni.jpg" width="100%">
                
            </div>
            <div class="col-lg-4 col-md-4 col-xs-4 col-sm-4">
                <img src="images/menip.jpg" width="100%">
                
            </div>
        </div>
    </div>

    <%@include  file="right/right.jsp" %>
    
</div>
<%@include file="Footers/Footer.jsp" %>
</body>

</html>

