<%-- 
    Document   : CreatePages
    Created on : 19 août 2019, 20:07:18
    Author     : ilboudo
--%>



<%-- 
    Document   : AccueilMembre
    Created on : 19 août 2019, 11:39:07
    Author     : ilboudo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="shortcut icon" href="img/favicon.png">

        <title>Create page</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>


    </head>

    <body>

        <%@include file="../Headers/Headers.jsp" %>
        <div class="row" style="overflow-y: scroll">
            <%@include file="../Menus/MenuMembres.jsp" %>
            <div class="col-lg-6 col-xl-12 col-md-6 col-sm-6 col-xl-6">
                <%@include  file="../Menus/MenuMembreFin.jsp" %>
                <div class="row">
                    <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12 ">


                        <form class="form-horizontal col-lg-offset-1" name="formcreate" novalidate method="post" action="">
                            <div class="row">
                                <input type="hidden" id="idPage" value="${page.id}">
                                <c:if test="${erreur=='false'}">
                                    <div class=" alert alert-success">
                                        <p>Page enregistree avec success!!</p>
                                    </div>
                                </c:if>
                                <c:if test="${erreur=='true'}">
                                    <div class=" alert alert-danger">
                                        <p>Erreur lors de l'enregistrement de la page. Veuillez reeessayer SVP. ${err}</p>
                                    </div>
                                </c:if>
                            </div>
                            <div class="row">
                                <section class="panel col-lg-12">
                                    <header class="panel-heading">
                                        Titre de la page
                                    </header>
                                    <div class="panel-body">

                                        <input  type="text" value="${pageEdit.titre}" required="true" name="titre" class="form-control round-input"/>
                                    </div>
                                </section>

                            </div>
                            <div class="row">
                                <section class="panel col-lg-12">
                                    <header class="panel-heading">
                                        Categorie(s)
                                    </header>
                                    <div class="panel-body">
                                        <select name="categories" required="true"   multiple="multiple" class="form-control col-lg-4 selectpicker" size="3" id = "categories">

                                            <c:forEach items="${pageEdit.pageHasCategorieList}" var="paC">
                                                <option selected="true" id="${paC.idCategorie.id}" value="${paC.idCategorie.id}">
                                                    ${paC.idCategorie.nom}
                                                </option>
                                            </c:forEach>

                                            <c:forEach items="${categories}" var="categorie" >
                                                <option   id="${categorie.getId()}" value="${categorie.getId()}">
                                                    ${categorie.getNom()}
                                                </option>
                                            </c:forEach>

                                        </select>

                                        <div class = "col-lg-6">
                                            <p id = "selected-categories">

                                            </p>
                                        </div>
                                    </div>
                                </section>
                            </div>
                            <div class="row">
                                <div class="col-lg-12 col-xl-12 col-md-12">
                                    <section class="panel">
                                        <header class="panel-heading">
                                            Mot(s) clé(s) 
                                        </header>
                                        <div class="panel-body" style="height: 100px">
                                            <input name="cle" value="${pageEdit.keyWords}" required="true" id="tagsinput" class="tagsinput" />
                                        </div>
                                    </section>
                                </div>

                            </div>

                            <div class="row">
                                <section class="panel col-lg-12">
                                    <header class="panel-heading">
                                        Fichier(s)
                                    </header>
                                    <div class="panel-body">
                                        <input name="fichier" type="file"placeholder="Choisir un fichier" />
                                    </div>
                                </section>
                            </div>

                            <div class="row">
                                <section class="panel col-lg-12">
                                    <header class="panel-heading">
                                        Contenu de la page
                                    </header>
                                    <div class="panel-body">
                                        <div class="form">

                                            <div class="form-group">
                                                <label class="control-label col-sm-2">Contenu</label>
                                                <div class="col-sm-10">
                                                    <textarea class="form-control ckeditor"  required="true" name="contenu" rows="6"> ${pageEdit.contenu}</textarea>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </section>
                            </div>
                            <div class="row">
                                <button class="btn btn-primary col-lg-5 " type="submit">Enregistrer</button>
                                <button class="btn btn-default col-lg-6 col-lg-offset-1" type="reset">Annuler</button>
                            </div>
                        </form>

                    </div>
                </div>

            </div>
            <%@include  file="../right/right.jsp" %>
            <%@include file="../Footers/Footer.jsp" %>
        </div>
        <script>
            //knob
            $(function () {
                $(".knob").knob({
                    'draw': function () {
                        $(this.i).val(this.cv + '%')
                    }
                })
            });

            //carousel
            $(document).ready(function () {
                $("#owl-slider").owlCarousel({
                    navigation: true,
                    slideSpeed: 300,
                    paginationSpeed: 400,
                    singleItem: true

                });
            });

            //custom select box

            $(function () {
                $('select.styled').customSelect();
            });

            /* ---------- Map ---------- */
            $(function () {
                $('#map').vectorMap({
                    map: 'world_mill_en',
                    series: {
                        regions: [{
                                values: gdpData,
                                scale: ['#000', '#000'],
                                normalizeFunction: 'polynomial'
                            }]
                    },
                    backgroundColor: '#eef3f7',
                    onLabelShow: function (e, el, code) {
                        el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');
                    }
                });
            });


        </script>

        <script>
            $(function () {
                $('#categories').change(function (e) {
                    var tab = $(this).val() || [];
                    var finalTab = [];
                    for (var i = 0, l = tab.length; i < l; i++) {
                        finalTab.push($('#' + tab[i]).html());
                    }
                    $('#selected-categories').html(finalTab.join(' , '));
                });
            });
        </script>
    </body>

</html>

