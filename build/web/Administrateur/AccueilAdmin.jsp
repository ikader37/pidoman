<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int nombreuser = Utilities.Utiles.uJpa.getUtilisateurCount();
    int nombrecommentaire = Utilities.Utiles.commentJp.getCommentaireCount();

    int nombrepage = Utilities.Utiles.pJpa.getPageCount();
    int nombrecategorie = Utilities.Utiles.commentJp.getCommentaireCount();
    List<Categorie> catList = Utilities.Utiles.catJpa.findCategorieEntities();
%>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="shortcut icon" href="img/favicon.png">

        <title>Perso</title>

        <!-- Bootstrap CSS -->
        <link href="Admin/css/bootstrap.min.css" rel="stylesheet">
        <!-- bootstrap theme -->
        <link href="Admin/css/bootstrap-theme.css" rel="stylesheet">
        <!--external css-->
        <!-- font icon -->
        <link href="Admin/css/elegant-icons-style.css" rel="stylesheet" />
        <link href="Admin/css/font-awesome.min.css" rel="stylesheet" />
        <!-- full calendar css-->
        <link href="Admin/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
        <link href="Admin/assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" />
        <!-- easy pie chart-->
        <link href="Admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen" />
        <!-- owl carousel -->
        <link rel="stylesheet" href="Admin/css/owl.carousel.css" type="text/css">
        <link href="Admin/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
        <!-- Custom styles -->
        <link rel="stylesheet" href="Admin/css/fullcalendar.css">
        <link href="Admin/css/widgets.css" rel="stylesheet">
        <link href="Admin/css/style.css" rel="stylesheet">
        <link href="Admin/css/style-responsive.css" rel="stylesheet" />
        <link href="Admin/css/xcharts.min.css" rel=" stylesheet">
        <link href="Admin/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
        <script type="text/javascript" src="Admin/js/jquery.js"></script>
        <script type="text/javascript" src="Admin/js/jquery-1.8.3.min.js"></script>
            



        <script type="text/javascript">
            $(document).ready(function () {
                $.get("categorieAdmin",function(data){
                    $("#listCat").html(data);
                }
                });
                var libcat=$("#idlibcat").val();
                var desccat=$("#iddesccat").val();
                
                
                $("#idformcat").submit(function(){
                    alert("Test ajax");
                    $.post("categorieAdmin",{libcat:libcat,desccat:desccat},function (data){
                        $("#listCat").html(data);
                    });
                    return false;
                });
            });


        </script>

    </head>

    <body>
        <%@include file="../Headers/Headers.jsp" %>

        <!--header end-->

        <!--sidebar start-->
        <%@include file="../Menus/MenuAdmin.jsp" %>




        <div class="tab-content pull-left" id="myTabContent">
            <div id="stack1" class="tab-pane fade active in">
                <div class="row">
                    <div class="col-lg-3 col-md-3 col-xl-3 col-xs-3">
                        <div class="info-box blue-bg">
                            <i class="fa fa-users"></i>
                            <div class="count"><%= nombreuser%></div>
                            <div class="title">Utilisateurs</div>
                        </div>
                    </div>



                    <div class="col-lg-3 col-md-3 col-xl-3 col-xs-3">
                        <div class="info-box red-bg">
                            <i class="fa fa-edit"></i>
                            <div class="count"><%= nombrepage%></div>
                            <div class="title">Pages</div>
                        </div>
                    </div>


                    <div class="col-lg-3 col-md-3 col-xl-3 col-xs-3">
                        <div class="info-box yellow-bg">
                            <i class="fa fa-comments"></i>
                            <div class="count"><%= nombrecommentaire%></div>
                            <div class="title">Commentaires</div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-xl-3 col-xs-3">
                        <div class="info-box orange-bg">
                            <i class="fa fa-folder-open"></i>
                            <div class="count"><%= nombrecategorie%></div>
                            <div class="title">Categories</div>
                        </div>
                    </div>


                </div>
            </div>

            <!--Dans ce div nous ferons un formulaire pour ajout listing edition suppression des categories-->
            <div id="idcat" class="tab-pane fade">
                <div class="row">
                    <div class="col-lg-9 col-md-9 col-sm-9 col-xl-9 col-xl-9">

                    </div>

                    <div class="col-lg-3 col-md-3 col-sm-3 col-xl-3 col-xs-3">
                        <button class="btn btn-primary fa fa-plus" data-toggle="modal" data-target="#idModAdd">Nouveau</button>

                        <div class="modal fade" id="idModAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" aria-hidden="true" data-dismiss="modal">×</button>
                                        <h4 class="modal-title"><center><h1>Ajouter une categorie</h1></center></h4>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form" name="form" id="idformcat">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Libellé:</label>
                                                <div class="col-sm-8">
                                                    <input type="text" id="idlibcat" name="idlibcat" required="true" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Description</label>
                                                <div class="col-sm-8">
                                                    <textarea  class="form-control" id="iddesccat" name="iddesccat" rows="7"></textarea>
                                                </div>
                                            </div>


                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-8">
                                                    <button type="submit" class="btn btn-primary"  id="idsubcat"><i class="fa fa-check fa-lg"></i> Enregistrer</button>
                                                    <button type="reset" class="btn btn-inverse"><i class="fa fa-times fa-lg"></i> Annuler</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-inverse" type="button" data-dismiss="modal"><i class="icon icon-times icon-lg"></i> Fermer</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12 col-xs-12" id="listCat">
                        <table class="table table-striped table-advance table-hover">
                            <caption>
                                Liste des categories
                            </caption>
                            <thead>

                            </thead>
                            <tfoot>

                            </tfoot>
                            <tbody>

                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>

        <!-- javascripts -->
        <script src="Admin/js/jquery.js"></script>
        <script src="Admin/js/jquery-ui-1.10.4.min.js"></script>
        <script src="Admin/js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="Admin/js/jquery-ui-1.9.2.custom.min.js"></script>
        <!-- bootstrap -->
        <script src="Admin/js/bootstrap.min.js"></script>
        <!-- nice scroll -->
        <script src="Admin/js/jquery.scrollTo.min.js"></script>
        <script src="Admin/js/jquery.nicescroll.js" type="text/javascript"></script>
        <!-- charts scripts -->
        <script src="Admin/assets/jquery-knob/js/jquery.knob.js"></script>
        <script src="Admin/js/jquery.sparkline.js" type="text/javascript"></script>
        <script src="Admin/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
        <script src="Admin/js/owl.carousel.js"></script>
        <!-- jQuery full calendar -->
        <<script src="Admin/js/fullcalendar.min.js"></script>
        <!-- Full Google Calendar - Calendar -->
        <script src="Admin/assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
        <!--script for this page only-->
        <script src="Admin/js/calendar-custom.js"></script>
        <script src="Admin/js/jquery.rateit.min.js"></script>
        <!-- custom select -->
        <script src="Admin/js/jquery.customSelect.min.js"></script>
        <script src="Admin/assets/chart-master/Chart.js"></script>

        <!--custome script for all page-->
        <script src="Admin/js/scripts.js"></script>
        <!-- custom script for this page-->
        <script src="Admin/js/sparkline-chart.js"></script>
        <script src="Admin/js/easy-pie-chart.js"></script>
        <script src="Admin/js/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="Admin/js/jquery-jvectormap-world-mill-en.js"></script>
        <script src="Admin/js/xcharts.min.js"></script>
        <script src="Admin/js/jquery.autosize.min.js"></script>
        <script src="Admin/js/jquery.placeholder.min.js"></script>
        <script src="Admin/js/gdp-data.js"></script>
        <script src="Admin/js/morris.min.js"></script>
        <script src="Admin/js/sparklines.js"></script>
        <script src="Admin/js/charts.js"></script>
        <script src="Admin/js/jquery.slimscroll.min.js"></script>
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

    </body>

</html>
