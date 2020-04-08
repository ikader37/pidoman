   
<%@page import="java.util.Date"%>
<%-- 
        Document   : index
        Created on : 20 juil. 2018, 15:27:48
        Author     : julie
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Idomen</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <script>

            $(document).ready(function() {
                $("a").click(function(e) {
                    e.preventDefault();

                    $("#someFrame").attr("src", $(this).attr("href"));
                })
            });

        </script>


        <style>

            .cadre{height:660px;}
            .header{height: 150px; background-image:url(images/Cp.PNG);background-repeat:no-repeat }
            .image{height:15px;margin-left: 20px;margin-top: 20px; }
            .cadlog{height:30px;margin-top: 10px;}
            .vide{height: 20px;background-color: red}
            .footer{height: 30px;background-color: darkgray;}
            .contenu{height: 600px; }
             .menu{height: 40px;background-color: #F57C00;}
            .milieu{height: 550px;background-color: white;}
            .zaff{background-color: white;height: 600px;overflow:scroll;border-left: darkgrey 0.5px solid}
            .zMenu{background-color: white;height: 210px;text-align: center;}
            /* Fond du gadget de la barre de recherche */
            .recherche_p {
                background-color: #eeeeee;   /* Couleur de fond */
                border-style: solid;   /* Style de la bordure  */
                border-width: 1px;   /* Epaisseur de la bordure  */
                border-color: #dddddd;   /* Couleur de la bordure  */
                padding: 10px 10px 10px 10px;   /* Espace entre les bords et le contenu : haut droite bas gauche  */
            }

            /* Champ de saisie */
            #searchthis #search {
                background-color: #ffffff;   /* Couleur de fond */
                border-style: solid;   /* Style de la bordure  */
                border-width: 1px;   /* Epaisseur de la bordure  */
                border-color: #dddddd;   /* Couleur de la bordure  */
                padding: 5px 10px 5px 10px;   /* Espace entre les bords et le contenu : haut droite bas gauche  */
                width: 98.5%;   /* Permet d'ajuster la largeur du champ de saisie à 100% */
                box-sizing: border-box;   /* Important */
                font-family: Lato;   /* Police du texte */
                font-size: 12px;   /* Taille de la police du texte */
                font-weight: normal;   /* Graisse du texte : normal = normal ; bold = gras */
                letter-spacing: 1px;   /* Espacement des caractères */
            }

            /* Bouton valider */
            #searchthis #search-btn {
                background-color: #E8B960;   /* Couleur de fond */
                border-style: solid;   /* Style de la bordure  */
                border-width: 1px;   /* Epaisseur de la bordure  */
                border-color: #E8B960;   /* Couleur de la bordure  */
                padding: 5px 10px 5px 10px;   /* Espace entre les bords et le contenu : haut droite bas gauche  */
                width: 98.5%;   /* Permet d'ajuster la largeur du champ de saisie à 100% */
                box-sizing: border-box;   /* Important */
                font-family: PT sans;   /* Police du texte */
                font-size: 13px;   /* Taille de la police du texte */
                font-weight: normal;   /* Graisse du texte : normal = normal ; bold = gras */
                letter-spacing: 2px;   /* Espacement des caractères */
                margin: 10px 0 0 0;   /* Espace autour du bouton : haut droite bas gauche  */
                text-transform: uppercase;   /* Transforme le texte en majuscules */
                color: #ffffff;   /* Couleur du texte */
            }

            /* Bouton valider quand survolé par la souris */
            #searchthis #search-btn:hover {
                background-color: #ffffff;   /* Couleur de fond */
                color: #E8B960;   /* Couleur du texte */
                cursor: pointer;   /* Apparence du curseur comme pour un lien */
            }

        </style>
    </head>

    <body>
        <!--%= session.getAttribute( "id" ) % -->
        <%
            if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
        %>       

       Connecté<br/>
        <a href="index.jsp">Connectez-vous</a>
        <%} else {
        %>

        <div class="header col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="row">
                <div class="image col-lg-2" ><img  src="images/ll.jpg" class="img-circle"></div>
                <div class="col-lg-6">
                    <div class="row">
                        <div class="col-lg-3"></div>
                        <div class="zrech col-lg-9 col-md-6 col-sm-6 col-xs-6">
                            <div class="row">
                                <div class="brech col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    </br> 
                                    <form action="/search" id="searchthis" method="get">
                                        <input id="search" name="q" type="text" placeholder="Rechercher" />
                                        <input id="search-btn" type="submit" value="Rechercher" />
                                    </form>
                                </div>               
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cadlog col-lg-3">
                    <div class="bouton" style="margin-left: 120px;">
                        Welcome <%=session.getAttribute("login")%><% }%>

                        <a type="button" href="index.jsp" onclick="window.open(this.href);
                                return false;">Deconnexion</a>
                    </div>


                </div>

            </div>         
        </div> 
        <div class="contenu col-lg-12">
            <div role="tabpanel" class="menu col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                <ul class="nav nav-tabs" role="tablist">

                    <li  class="liencol-lg-4 col-md-4 col-sm-4 col-xs-4 "></li>
                    <li role="presentation" id="ddlsource" class="lien active col-lg-2 col-md-2 col-sm-2 col-xs-2 "><a href="ReadPage.jsp"  target="someFrame" aria-controls="home" role="tab" data-toggle="tab" style="color:white;">Lire</a></li>
                    <li role="presentation" id="ddlsource" class="lien col-lg-2 col-md-2 col-sm-2 col-xs-2"><a href="UpdatePage.jsp" target="someFrame" aria-controls="partenaire" role="tab" data-toggle="tab" style="color:white;">Modifier</a></li>
                    <li role="presentation" id="ddlsource" class="lien col-lg-2 col-md-2 col-sm-2 col-xs-2"><a href="PageCreate.jsp" target="someFrame"  aria-controls="service" role="tab" data-toggle="tab" style="color:white;">Creer</a></li>
                </ul>

                <div role="tabpanel"class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
                <div role="tabpanel" class="tab-pane fade col-lg-2 col-md-2 col-sm-2 col-xs-2" id="Partenaire">Lire</div>
                <div role="tabpanel" class="tab-pane fade col-lg-2 col-md-2 col-sm-2 col-xs-2" id="Service">Modifier</div>
                <div role="troleabpanel" class="tab-pane fade col-lg-2 col-md-2 col-sm-2 col-xs-2" id="stat">Creer</div>

            </div>

        </div>



        <div class="milieu" style="margin-top: -600px;"> 
            <div class="row" style="margin-top:40px;">

                <div class=" zMenu text-center col-lg-2"><center><h4>Menu</h4></center> <br/>

                    
                    <!--<%@include  file="Menu.jsp" %>-->
                    
                    <div class="row" style="text-align:left;height: 25px;border:1px solid black;"><div class="col-lg-12 "><a href="contenu.jsp" target="someFrame">Accueil</a></div></div> 
                    <div class="row" style="text-align:left;height: 25px;margin-top: 5px;border:1px solid black;"> <div class="col-lg-12"><a href="PageSpeciale.jsp" target="someFrame">Page Speciales</a></div></div> 
                    <div class="row" style="text-align:left;height: 50px;margin-top: 5px;border:1px solid black;"><div class="col-lg-12"><a href="PageCreate.jsp" target="someFrame">Creer/Partager une connaissance</a></div></div> 
                    <div class="row" style="text-align:left;height: 25px;margin-top: 5px;border:1px solid black;"><div class="col-lg-12" ><a href="stat.jsp" target="someFrame">Statistiques</a></div></div> 
                    <div class="row" style="text-align:left;height: 25px;margin-top: 5px;border:1px solid black;"><div class="col-lg-12"><a href="recherchesemantique.jsp" target="someFrame">Recherche Semantique</a></div></div> 
                    
                </div>


                </br> 
                <div  class="zaff col-lg-10">
                    <iframe name="someFrame" id="someFrame" width="100%" height="100%; " >
                    </iframe>
                </div>
            </div>
        </div>




    </body>

</html>

