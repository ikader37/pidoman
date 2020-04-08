<%-- 
    Document   : ListCategoriesSimple
    Created on : 27 août 2019, 01:08:30
    Author     : ilboudo
--%>
<%@include file="../Headers/Headers.jsp" %>
<div class="row" style="overflow-y: scroll">
    <%@include file="../Menus/MenuMembres.jsp" %>

    <div class="col-lg-6 col-xs-6 col-md-6 col-sm-6">
              <div class="row">
                    <div class="col-lg-12 col-md-12 col-xl-12">
                        <h1><center>PAGES SPECIALES >> Toutes les catégories</center></h1>
                    </div>
                </div>
              <section class="wrapper" id="contents">
                  
                  <div class="row">
                      <div class="col-lg-12 col-xl-12 col-md-12">
                          
                          <c:if test="${categories.size()==0}">
                              <div class="alert alert-danger">
                                  <p>Aucne categorie dans cette rubrique.</p>
                              </div>
                          </c:if>
                          <c:if test="${categories.size()>0}">
                              <ol>
                                  <div class="panel-group m-bot20" id="accordion">
                                    <c:forEach items="${categories}" var="categorie">
                                        <li>
                                            <a href="#"></a> 

                                                  <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                      <h4 class="panel-title">
                                                                          <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne${categorie.id}">
                                                                              ${categorie.nom}
                                                                          </a>
                                                                      </h4>
                                                    </div>
                                                    <div id="collapseOne${categorie.id}" class="panel-collapse collapse in">
                                                      <div class="panel-body">
                                                        Description:${categorie.description}
                                                        <br>
                                                        <p><span><a href="#">Nombre de pages:${categorie.pageHasCategorieList.size()}</a></span></p>
                                                      </div>
                                                    </div>
                                                  </div>
                                        </li>
                                        <br>
                                    </c:forEach>
                                  </div>
                          </ol>
                          </c:if>
                          
                      </div>
                  </div>         
              </section>
    </div>
    <%@include  file="../right/right.jsp" %>
    <%@include file="../Footers/Footer.jsp" %>
</div>
          <script src="../Admin/js/gritter.js" type="text/javascript"></script>
    </body>
</html>
