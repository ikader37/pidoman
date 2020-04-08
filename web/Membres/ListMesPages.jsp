<%@include file="../Headers/Headers.jsp" %>
<div class="row" style="overflow-y: scroll">
    <%@include file="../Menus/MenuMembres.jsp" %>
    <div class="col-lg-6 col-xl-6 col-md-6 col-sm-6 col-xl-6">
      <%@include  file="../Menus/MenuMembreFin.jsp" %>
        <div class="row">
            <div class="col-lg-12 col-xl-12 col-md-12 col-md-12 col-xs-12">

                <div class="row">
                    <div class="col-lg-12 col-xl-12 col-md-12">

                        <h1><center>Mes pages</center></h1>

                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-xl-12 col-md-12">
                        <c:if test="${erreur=='true'}">
                            <div class="row">
                                <div class="col-lg-12 col-xs-12 col-md-12">
                                    <span class="alert alert-success">
                                        Opération réussit avec success!!!!
                                    </span>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>


                <div class="row">

                    <div class="col-lg-12 col-md-12 col-xl-12">
                        <c:if test="${utilisateur.getPageList().size()>0}">
                            <ol>
                                <div class="panel-group m-bot20" id="accordion">
                                    <c:forEach items="${utilisateur.getPageList()}" var="page" >
                                        <li>
                                            <form method="get" action="editPage" >
                                                <input type="hidden" name="page" value="${page.id}">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">
                                                            <a id="${page.id}" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne${page.id}">
                                                                ${page.titre}
                                                            </a>
                                                        </h4>
                                                    </div>
                                                    <div id="collapseOne${page.id}" class="panel-collapse collapse in">
                                                        <div class="panel-body">
                                                            Categorie(s):<c:forEach items="${page.pageHasCategorieList}" var="cat">

                                                                ${cat.idCategorie.nom}

                                                            </c:forEach>
                                                            <br>
                                                            Mot(s) clé(s):${page.keyWords}                                         
                                                            <br>
                                                            Contenu: ${page.contenu}
                                                            <br>

                                                            <button class="btn btn-primary" type="submit">
                                                                <i class="icon_book_alt"></i>
                                                                Editer  
                                                            </button>

                                                            <a href="#myModal-1" data-toggle="modal" class="btn  btn-danger" data-id="${page.id}" data-role="del"><i class="icon_trash_alt"></i>
                                                                Supprimer
                                                            </a>



                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </li>
                                        <br>
                                    </c:forEach>
                                </div>
                            </ol>
                        </c:if>
                    </div>
                </div>

                <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal-1" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                <h4 class="modal-title">Suppression de page</h4>
                            </div>
                            <div class="modal-body">
                                <p><i class="icon-bell-l"></i>   Etes vous sur de vouloir supprimer la page?</p>
                                <form action="myPages" method="post">
                                    <input name="idsup" id="id" type="hidden">
                                    <br>
                                    <p id="title"></p>
                                    <button type="submit" class="btn btn-danger">OUI
                                    </button>
                                    <button type="reset" class="btn btn-close">
                                        Annuler
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <%@include  file="../right/right.jsp" %>
    
</div>
<%@include file="../Footers/Footer.jsp" %>
<script type="text/javascript">
    $(document).on('click', 'a[data-role=del]', function () {
        //Obtenir le id de page a supprimer
        console.log("BIEN ");

        var idp = $(this).data('id');
        console.log("ID :" + idp);

        //Maintenant recuperons le titre de la page 
        var titre = $("#" + idp).text();
        $("#id").val(idp);
        $("#title").val(titre);
        console.log("TITRE :" + titre);
    });

    $(document).on('click', 'a[data-role=editer]', function () {

    });

</script>



</body>
</html>