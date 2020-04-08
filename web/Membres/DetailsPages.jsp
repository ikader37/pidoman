<%-- 
    Document   : DetailsPages
    Created on : 7 oct. 2019, 21:39:41
    Author     : ilboudo
--%>
<%@include file="../Headers/Headers.jsp" %>
<div class="row" style="overflow-y: scroll">
    <%@include file="../Menus/MenuMembres.jsp" %>

    <div class="col-lg-6 col-xs-6 col-md-6 col-sm-6">
        <div class="row">
            <div class="col-lg-12 col-xl-12 col-sm-12 col-md-12">
                <center><h1><strong>Details</strong></h1></center>
            </div>
        </div>
        <c:if test="${page!=null}">
            <div class="row">
                <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12 col-xs-12" >
                    <center><h2><a>${page.titre}</a></h2></center>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12 col-xs-12" >
                    <p style="color: red"><strong>Mot(s) clé(s):</strong> ${page.keyWords}</p>
                </div>
            </div>
                
            <div class="row">
                <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12 col-xs-12" >
                    <p style="color: green">
                        <strong>Catégories:</strong>
                        <c:choose>
                            <c:when test="${page.pageHasCategorieList.size()>0}">
                                <c:forEach items="${page.pageHasCategorieList}" var="c">
                                    ${c.idCategorie.nom};
                                </c:forEach>
                            </c:when>
                            <c:when test="${page.pageHasCategorieList.size()<=0}">
                                Aucune
                            </c:when>
                        </c:choose>
                    </p>
                </div>
            </div>
                
                <div class="row">
                    <div class="col-lg-3 col-md-3 col-xl-3 col-sm-3">
                        <img  height="100%" width="100%" src="${page.getImg1()}"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-xl-3 col-sm-3">
                        <img height="100%" width="100%" src="${page.getImg2()}"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-xl-3 col-sm-3">
                        <img height="100%" width="100%" src="${page.getImg3()}"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-xl-3 col-sm-3">
                        <img height="100%" width="100%" src="${page.getImg4()}"/>
                    </div>
                </div>
                    
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-xl-6 col-xl-6">
                             <i class="fa fa-cloud-download"></i><a class="primary" type="button" href="${page.getDoc()}" >Telecharger</a>
                        </div>
                    </div>
                        <br/>
            <div class="row">
                <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12">
                    <p><strong>Contenu : </strong>${page.contenu}</p>
                </div>
            </div>
                <br/>
                <div class="row">
                        <div class="col-lg-12 col-md-12 col-xs-12 col-xl-12">
                            <center><h2><strong>Commentaires</strong></h2></center>
                        </div>
                    </div>
            <c:choose>
                <c:when test="${page.commentaireList.size()>0}">
                    
                    <div class="row">
                        <div  class="col-lg-12 col-md-12 col-xs-12 col-sm-12 col-xl-12">
                            <c:forEach  items="${page.commentaireList}" var="cc">
                                <div class="row">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                                        <div class="row">
                                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 col-xl-12">
                                                <p>
                                                    <strong>Sujet:</strong> ${cc.sujet}
                                                </p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-12 col-xs-12 col-md-12 col-sm-12 col-xl-12">
                                                <p>
                                                    <strong>Contenu:</strong>${cc.comment}
                                                </p>
                                                <hr/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:when>
                <c:when test="${page.commentaireList.size()<=0}">
                    <p class="danger" >
                        <strong>Aucun commentaire</strong>
                    </p>
                </c:when>
            </c:choose>
                    <br/>
            <div class="row">
                <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Commentaire</div>
                        <c:if test="${bon ==true}">
                            <p  class="text-success" style="background: greenyellow">
                                Commentaire réussi!!!
                            </p>
                        </c:if>
                        <c:if test="${bon ==false}">
                            <p class="text-danger" style="background: red">
                                Erreur!!!Problème d'ajout de commentaire
                            </p>
                        </c:if>
                        <div class="panel-body">
                            <form role="form" name="form" action="details" method="post">
                                <div class="form-group">
                                    <label>Nom et prénom(s) *:</label>
                                    <input type="text" name="name" class="form-control"  placeholder="Nom et prenom(s)">
                                </div>
                                <div class="form-group">
                                    <label>Email*:</label>
                                    <input type="text" name="email" class="form-control" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <label>Sujet*:</label>
                                    <input type="text" name="sujet" class="form-control" placeholder="Sujet">
                                </div>
                                <div class="form-group">
                                    <label>Commentaire:</label>
                                    <textarea name="comment" class="form-control" rows="7"></textarea>
                                </div>
                                <input type="hidden" name="id" value="${page.id}"/>
                                <button type="submit" class="btn btn-primary"><i class="icon icon-check icon-lg"></i> Valider</button>
                                <button type="reset" class="btn btn-inverse"><i class="icon icon-times icon-lg"></i> Annuler</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    
    <%@include  file="../right/right.jsp" %>
   
</div>
    
</body>
</html>
