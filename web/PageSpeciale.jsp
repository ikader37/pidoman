<%-- 
    Document   : PageSpeciale
    Created on : 18 sept. 2018, 11:46:36
    Author     : Julie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="bootstrap.min.css">
        <script type="text/javascript" src="jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="bootstrap.js"></script>
        <script type="text/javascript" src="bootstrap-tab.js"></script>
        <script type="text/javascript" src="bootstrap-modal.js"></script>
    </head>
    <body>

        <div class="row">
            <div class="col-lg-10 offset-1" style="border-bottom:solid whitesmoke 2px;margin-top: 10px;font-size: 25px;"><b>Pages Speciales</b></div>
            <div class="col-lg-10 offset-1" style="border-bottom: solid whitesmoke 2px;margin-top: 10px;font-size: 20px;height: 100px;"><b>Rapport de maintenance</b><br>
                <div class="row"> 
                    <div class="col-lg-5">
                        <li style="font-size: 15px;"><a href="unusedCat.jsp">Categories inutilisées</a></li>
                        <li style="font-size: 15px;"><a href="neededCat.jsp">Categories les plus demandées</a></li>
                        <li style="font-size: 15px;"><a href="CatSansCat.jsp"> Categories sans sous categorie</a></li>

                    </div>
                    <div class="col-lg-2"></div>
                    <div class="col-lg-5">
                        <li style="font-size: 15px;"><a href="PageSansCat.jsp">Pages sans categorie</a></li>
                        <li style="font-size: 15px;"><a href="MotsClePlusDemandes.jsp">mots clés les plus demandés</a></li>
                    </div>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-lg-10 offset-1" style="border-bottom: solid whitesmoke 2px;margin-top: 10px;font-size: 20px;height: 90px;"><b>Liste des pages</b><br>
                <div class="row"> 
                    <div class="col-lg-5">
                        <li style="font-size: 15px;"><a href="AllCategorie.jsp">Liste des categories</a></li>
                        <li style="font-size: 15px;"><a href="AllPage.jsp">Toutes les pages</a></li>

                    </div>
                    <div class="col-lg-2"></div>
                    <div class="col-lg-5">
                        <li style="font-size: 15px;"><a href="search.jsp">Toutes les pages commencant par</a></li>
                        <li style="font-size: 15px;"><a href="AllMotCles.jsp">Liste des mots clés</a></li>

                    </div>
                </div>
            </div>

        </div>
        <div class="row">

            <div class="col-lg-10 offset-1" style="border-bottom: solid whitesmoke 2px;margin-top: 10px;font-size: 20px;height: 50px"><b>S'identifier/Sinscrire</b><br>
                <div class="row"> 
                    <div class="col-lg-5">
                        <li style="font-size: 15px;"><a href="#">Connexion</a></li>
                        <center></center>
                    </div>
                    <div class="col-lg-2"></div>
                    <div class="col-lg-5">
                        <li style="font-size: 15px;"><a href="#">Creer un compte</a></li>
                        <center></center>
                    </div>
                </div>
            </div>

        </div>

        <div class="row">

            <div class="col-lg-10 offset-1" style="border-bottom: solid whitesmoke 2px;margin-top: 10px;font-size: 20px;height: 75px"><b>Données semantiques</b><br>
                <div class="row"> 
                    <div class="col-lg-5">
                        <li style="font-size: 15px;"><a href="#">Export des pages en RDF</a></li>
                        <li style="font-size: 15px;"><a href="#">Parcourir le wiki</a></li>

                    </div>
                    <div class="col-lg-2"></div>
                    <div class="col-lg-5">
                        <li style="font-size: 15px;"><a href="#">Recherche semantique (mots clés,categorie)</a></li>

                        <center></center>
                    </div>
                </div>
            </div>

        </div>

    </body>
</html>
