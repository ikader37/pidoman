<%-- 
    Document   : Connexion
    Created on : 19 août 2019, 10:41:44
    Author     : ilboudo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stl" uri="http://ns.inria.fr/sewese/semtags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%@include file="Headers/Headers.jsp" %>
<div class="row">
    <%@include file="Menus/MenuMembres.jsp" %>
  <div class="col-lg-6 col-sm-6 col-xl-6 col-md-6 col-xs-6">
      <div class="row">
          <div class="col-lg-12 col-xl-12 col-md-12 col-sm-12">
              <center>
                  <h1>CONNEXION</h1>
              </center>
          </div>
      </div>
      <form class="login-form" method="post" action="Connexion" style="margin-top: 0">
       
            
            <c:if test="erreur ==true">
                <div class="alert alert-danger">
                    <span><i class="icon icon-bell-l"></i>Erreur</span>
                </div>
            </c:if>
        
      <div class="login-wrap">
        <p class="login-img"><i class="icon icon_lock_alt"></i></p>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon icon_profile"></i></span>
          <input type="text" class="form-control" placeholder="Email" name="username" value="<c:out value="${user.email}"/>" autofocus>
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="icon icon icon_key_alt"></i></span>
          <input type="password" class="form-control" placeholder="Mot de passe" value="<c:out value="${user.mot_de_passe}"/>" name="password">
        </div>
        <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
                <span class="pull-right"> <a href="#"> Forgot Password?</a></span>
            </label>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Se connecter</button>
        <a class="btn btn-info btn-lg btn-block" href="inscription">S'inscrire</a>
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
</body>

</html>
