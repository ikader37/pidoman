<%-- 
    Document   : header
    Created on : 10 sept. 2018, 12:12:07
    Author     : Julie
--%>
            <div class="header col-lg-12 col-md-12 col-sm-12 col-xs-12">
                 <div class="row">
                     <div class="image col-lg-2 img-rounded">logo</div>
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
                              <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#connection">Se connecter</button>
                              <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#registrar"> S'inscrire</button>
                         <div class="modal fade" id="connection" tabindex="-1" role="dialog" aria-labelledby="ConnectionPage" aria-hidden="true">
                                    <div class="modal-dialog">
                                       <div class="modal-content">
                                           <div class="modal-header">
                                                 <h4 class="modal-title" id="ConnectionPage"><center>Connectez-vous</center></h4>
                                           </div>
                                       <div class="modal-body">
                                          <div class="row">
                                              <div class="col-lg-offset-3 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                                  <form role="form" method="post" action="loggin.jsp">
                                                    <div class="form-group">
                                                        <label for="mail">Email adress</label>
                                                        <input type="email" name="email" class="form-control" id="mail" placholder="Enter email">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="pwd">Password</label>
                                                        <input type="password"  name="motpass" class="form-control" id="pwd" placholder="Password">
                                                    </div>
                                                    <div class="checkbox">
                                                      <label>
                                                        <input type="checkbox"> Se rappeler de moi
                                                      </label>
                                                    </div>
                                                    <button type="submit" class="btn btn-default">Valider</button>
                                                 </form>
                                             </div>
                                           </div>
                                      </div>
                                     <div class="modal-footer">
                                     <button type="button" class="btn btn-primary" data-dismiss="modal">Fermer</button>
                                     <button type="button" class="btn btn-primary" data-dismiss="modal">Annuler</button>
                                     </div>
                               </div>
                         </div>
                     </div>
                              <div class="modal fade" id="registrar" tabindex="-1" role="dialog" aria-labelledby="RegistrarPage" aria-hidden="true">
                                  <div class="modal-dialog">
                                      <div class="modal-content">
                                           <div class="modal-header">
                                                <h4 class="modal-title" id="registrarPage"><center>Inscrivez vous</center></h4>
                                           </div>
                                           <div class="modal-body">
   		                            <form method="post" action="registration.jsp"> 
          	                                <input type="text" class="form-control" name="nom" placeholder="Nom" ><br/>
                                                <input type="text" class="form-control" name="prenom" placeholder="Prenom" aria-describedby="basic-addon2"><br/>
                                                <input type="text" class="form-control" name="email" placeholder="Email" aria-describedby="basic-addon2"><br/>
                                                <input type="password" class="form-control" name="motpass" placeholder="Mot de passe" aria-describedby="basic-addon2"><br/>
                                                <input type="text" class="form-control" name="login" placeholder="login" aria-describedby="basic-addon2"><br/>
                                                <button class="btn btn-lg btn-outline-danger"><i class="fa fa-save"></i> S'inscrire</button> 
                                                <button type="button" class="btn btn-lg btn-outline-default" data-dismiss="modal"><i class="fa fa-close"></i> Annuler</button>
          	
                                             </form>   
                                          </div>
                                         <div class="modal-footer">
                                        <center>...</center>
        </div>
                                      </div>
                                  </div>
                              </div>
                         </div>
                              
                         
                    </div>
                    
                 </div>         
             </div> 
             <div class="contenu col-lg-12">
                <div class="row">
                            <div role="tabpanel" class="menu col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                <ul class="nav nav-tabs" role="tablist">
                                     <li role="presentation" id="ddlsource" class="lien col-lg-2 col-md-2 col-sm-2 col-xs-2"><a href="index.jsp" aria-controls="stat" role="tab" data-toggle="tab">Acceuil</a></li>
                                     <li  class="liencol-lg-4 col-md-4 col-sm-4 col-xs-4 "></li>
                                     <li role="presentation" id="ddlsource" class="lien active col-lg-2 col-md-2 col-sm-2 col-xs-2 "><a href="index.jsp"  aria-controls="home" role="tab" data-toggle="tab">Lire</a></li>
                                     <li role="presentation" id="ddlsource" class="lien col-lg-2 col-md-2 col-sm-2 col-xs-2"><a href="UpdatePage.jsp" aria-controls="partenaire" role="tab" data-toggle="tab">Modifier</a></li>
                                     <li role="presentation" id="ddlsource" class="lien col-lg-2 col-md-2 col-sm-2 col-xs-2"><a href="PageCreate.jsp"  aria-controls="service" role="tab" data-toggle="tab">Creer</a></li>
                                    
                                     
                                    
                                </ul>
                                    <div class="tab-content">4<div role="troleabpanel" class="tab-pane fade col-lg-2 col-md-2 col-sm-2 col-xs-2" id="data">Acceuil</div>
                                    <div role="tabpanel"class="col-lg-4 col-md-4 col-sm-4 col-xs-4"></div>
                                    <div role="tabpanel" class="tab-pane fade col-lg-2 col-md-2 col-sm-2 col-xs-2" id="Partenaire">Lire</div>
                                    <div role="tabpanel" class="tab-pane fade col-lg-2 col-md-2 col-sm-2 col-xs-2" id="Service">Modifier</div>
                                    <div role="troleabpanel" class="tab-pane fade col-lg-2 col-md-2 col-sm-2 col-xs-2" id="stat">Creer</div>
                                    <div role="troleabpanel" class="tab-pane fade col-lg-2 col-md-2 col-sm-2 col-xs-2" id="data">Afficher</div>
                                </div>
                      
                            </div>
                         </div>        
                            
              </div>
                     
                
