<%-- 
    Document   : PagesSpecialsContenu
    Created on : 26 août 2019, 00:40:31
    Author     : ilboudo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pages speciales</title>
    </head>
    <body>
       <section id="container" class="">
            <%@include file="../Headers/Headers.jsp" %>

          <!--header end-->

          <!--sidebar start-->
          <%@include file="../Menus/MenuMembres.jsp" %>
          <!--sidebar end-->

          <!--main content start-->
          <section id="main-content">
              <div class="row">
                  <div class="col-lg-12 col-md-12 col-xl-12">
                      <h1>PAGES SPECIALES</h1>
                  </div>
              </div>
              <section class="wrapper" id="contents">
                  <div class="row">
                      <div class="col-lg-12 col-xl-12 col-md-12">
                           <h2>Rapports de maintenaces</h2>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-lg-6 col-xl-6 col-sm-6 col-md-6">
                          <ul>
                               <li>
                                   <a href="categoriesUnUsed">Catégories inutilisées</a>
                               </li>
                               <li>
                                   <a>Pages courtes</a>
                               </li>
                               <li>
                                   <a>Pages les moins modifiées</a>
                               </li>
                               
                           </ul>
                      </div>
                       <div class="col-lg-6 col-xl-6 col-sm-6 col-md-6">
                           <ul>
                               <li>
                                   <a>Pages les plus demandées</a>
                               </li>
                               <li>
                                   <a>Pages sans catégories</a>
                               </li>
                           </ul>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-lg-12 col-xl-12 col-md-12">
                           <h2>Liste de pages/h2>
                      </div>
                      
                  </div>
                  <div class="row">
                      <div class="col-lg-6 col-xs-6 col-md-6">
                          <ul>
                              <li>
                                  <a href="#">Toutes les pages</a>
                              </li>
                              <li>
                                  <a href="#">Rechercher</a>
                              </li>
                              <li>
                                  <a href="#">Toutes les pages commencant par ...</a>
                              </li>
                              <li>
                                  <a href="ListCategoriesSimple">Liste des catégories</a>
                              </li>
                              <li>
                                  <a href="#">Modifications récentes</a>
                              </li>
                              <li>
                                  <a href="#">Nouvelles pages</a>
                              </li>
                          </ul> 
                      </div>
                  </div>
                  
                  
              </section>
          </section>
       </section>
    </body>
</html>
