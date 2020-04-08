<%-- 
    Document   : CreatePages
    Created on : 19 août 2019, 20:07:18
    Author     : ilboudo
--%>


<%@include file="../Headers/Headers.jsp" %>
<div class="row">
    <%@include file="../Menus/MenuMembres.jsp" %>
    <div class="col-lg-6 col-xl-6 col-md-6 col-sm-6 col-xl-6">
        <%@include  file="../Menus/MenuMembreFin.jsp" %>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xl-12 col-xs-12">
                <h1>
                    <center>Ajouter une page</center>
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-xl-12 col-sm-12 col-xs-12">

                <form class="form-horizontal" name="formcreate" accept-charset="utf-8" enctype="multipart/form-data" novalidate method="POST" action="CreatePages" >
                    <div class="row">
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
                                <input  type="text" value="${page.getTitre()}" required="true" name="titre" class="form-control round-input"/>
                            </div>
                        </section>

                    </div>
                    <div class="row">
                        <section class="panel col-lg-12">
                            <header class="panel-heading">
                                Categorie(s)
                            </header>
                            <div class="panel-body">
                                <select name="categories"  multiple="multiple"   id = "categories">


                                    <c:forEach items="${categories}" var="categorie" >
                                        <option  id="${categorie.getId()}" value="${categorie.getId()}">
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
                                    <input name="cle" value="${page.getKeyWords()}" required="true" id="tagsinput" class="tagsinput" value="Exemple 1, exemlpe 2" />
                                </div>
                            </section>
                        </div>

                    </div>

                    <div class="row">
                        <section class="panel col-lg-12">
                            <header class="panel-heading">
                                Document
                            </header>
                            <div class="panel-body">
                                <input name="doc" type="file"placeholder="Choisir un fichier" />
                            </div>
                        </section>
                    </div>

                    <div class="row">
                        <section class="panel col-lg-12">
                            <header class="panel-heading">
                                Image 1
                            </header>
                            <div class="panel-body">
                                <input name="img1" accept="image/*" type="file"placeholder="Choisir un fichier" />
                            </div>
                        </section>
                    </div>
                    <div class="row">
                        <section class="panel col-lg-12">
                            <header class="panel-heading">
                                Image 2
                            </header>
                            <div class="panel-body">
                                <input name="img2" accept="image/*" type="file"placeholder="Choisir un fichier" />
                            </div>
                        </section>
                    </div>
                    <div class="row">
                        <section class="panel col-lg-12">
                            <header class="panel-heading">
                                Image 3:
                            </header>
                            <div class="panel-body">
                                <input name="img3" accept="image/*" type="file"placeholder="Choisir un fichier" />
                            </div>
                        </section>
                    </div>

                    <div class="row">
                        <section class="panel col-lg-12">
                            <header class="panel-heading">
                                Imnage 4
                            </header>
                            <div class="panel-body">
                                <input name="img4" accept="image/*" type="file"placeholder="Choisir un fichier" />
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
                                            <textarea class="form-control ckeditor" value="${page.getContenu()}" required="true" name="c" rows="6"></textarea>
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
    
</div>
    <%@include file="../Footers/Footer.jsp" %>


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

