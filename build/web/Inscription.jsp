<%-- 
    Document   : Connexion
    Created on : 19 août 2019, 10:41:44
    Author     : ilboudo
--%>
<%@ page trimDirectiveWhitespaces="true" %>

<%@include file="Headers/Headers.jsp" %>
<div class="row">
    <%@include file="Menus/MenuMembres.jsp" %>
  <div class="col-lg-6 col-sm-6 col-xl-6 col-md-6 col-xs-6">
      <div class="row">
          <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12">
              <center>
                  <h1>INSCRIPTION</h1>
              </center>
          </div>
      </div>
      <form class="login-form" method="post" onsubmit="verifierPass()" action="inscription" style="margin-top: 0">
       
            
            <c:if test="erreur ==true">
                <div class="alert alert-danger">
                    <span><i class="icon icon-bell-l"></i>Erreur</span>
                </div>
            </c:if>
        
        <div class="login-wrap">
          <p class="login-img"><i class="icon icon_lock_alt"></i></p>
          
          <div class="input-group">
            <span class="input-group-addon"><i class="icon icon_profile"></i></span>
            <input type="text" class="form-control" placeholder="Nom" name="nom" value="<c:out value="${user.nom}"/>" required="true" autofocus>
          </div>
          <div class="input-group">
            <span class="input-group-addon"><i class="icon icon_profile"></i></span>
            <input type="text" class="form-control" placeholder="Prenom(s)" name="prenom" required="true" value="<c:out value="${user.prenom}"/>" autofocus>
          </div>
          
          <div class="input-group">
            <span class="input-group-addon"><i class="icon icon_profile"></i></span>
            <input type="email" class="form-control" placeholder="Email" name="email" required="true" value="<c:out value="${user.email}"/>" autofocus>
          </div>
          <div class="input-group">
            <span class="input-group-addon"><i class="icon icon icon_key_alt"></i></span>
            <input type="password" class="form-control" placeholder="Mot de passe" id="p1" required="true" value="<c:out value="${user.mot_de_passe}"/>" name="motpass">
          </div>
          <div class="input-group">
            <span class="input-group-addon"><i class="icon icon_key_alt"></i></span>
            <input type="password" class="form-control" placeholder="Confirmer mot de passe" id="p2" required="true" name="motpass2" autofocus>
          </div>
          
          <button class="btn btn-primary btn-lg btn-block" type="submit">S'inscrire</button>
        </div>
      </form>
    <div class="text-right">
      <div class="credits">
        
        </div>
    </div>
  </div>
           <%@include  file="right/right.jsp" %>
    <%@include file="Footers/Footer.jsp" %>
</div>

<script type="text/javascript">
    
    function verifierPass(){
        var p1=document.getElementById("p1");
        var p2=document.getElementById("p2");
        if(p1.value!=p2.value){
            alert("Les mots de passe ne sont pas identiques.Veuillez reessayer");
            return false;
        }else{
            return true;
        }
    }
    
</script>

</body>

</html>
