
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
                <h1 style="font-weight: bold">Bienvenue sur WikiMeningitis, Votre wiki sur la méningite</h1><br>
                <p style="text-align: justify;">Le Centre de recherche en santé de Nouna a connu ses débuts en 1992 en collaboration avec
                    les partenaires Allemands de l?Institut de Santé Publique de l?Université de Heidelberg sous
                    la dénomination PRAPASS qui est le projet de recherche action pour l?amélioration des soins
                    de santé qui couvrait la Grande Kossi dont les limites s?étendaient du Sourou actuel aux Banwa.
                    Les réponses apportées aux questions et préoccupations de santé par le PRAPASS ont conduit le Ministère
                    de la Santé du Burkina Faso à la transformation institutionnelle en 1999 du PRAPASS en Centre de Recherche en 
                    Santé de Nouna. Cette transformation lui a conféré un encrage institutionnel plus fort et une compétence élargie 
                    sur l?ensemble du territoire national et au delà des frontières du Burkina Faso. Au niveau national,
                    le CRSN entretient un partenariat scientifique et technique avec tous les instituts de recherche du Ministère de la Santé et les Universités au niveau national</p>
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

