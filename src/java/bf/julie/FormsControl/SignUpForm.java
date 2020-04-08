/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.FormsControl;

import bf.julie.pidomen.dao.UtilisateurDAO;
import bf.julie.pidomen.entites.Utilisateur;
import java.util.HashMap;
import java.util.Map;
import bf.julie.pidomen.dao.RoleDAO;
import bf.julie.pidomen.entites.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ilboudo
 */
public final class SignUpForm {
    
    
    
    public  final String nomC="nom";
    public final String prenomC="prenom";
    public final String emailC="email";
    public final String loginC="login";
    public final String mot_de_passeC="motpass";
    public final String mot_de_passe_2C="motpass2";
    UtilisateurDAO udao=new UtilisateurDAO();
    RoleDAO rdao=new RoleDAO();
    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }
    
    public Utilisateur inscrireUtilisateur(HttpServletRequest request){
        
        UtilisateurDAO dao = new UtilisateurDAO();
        String nom =request.getParameter(nomC); 
        String prenom=request.getParameter(prenomC); 
        String login=request.getParameter(loginC); 
        String motpass=request.getParameter(mot_de_passeC); 
        String motpass2=request.getParameter(mot_de_passe_2C);
        String email=request.getParameter(emailC); 
        Utilisateur u = new Utilisateur(nom, prenom, login, motpass, email); 
        
        Utilisateur ut=dao.getUtilisateurByEmail(email);
        if(ut!=null){
            
            resultat="email";
            setErreur(emailC,"Cet email est déjà utilisé!");
            
        }else{
          
          System.out.println("ERREUR");
          if(verifierPassword(request)){
              
              Role role=Utilities.Utiles.rjpa.findRole(new Integer(2));
              u.setIdRole(role);
               dao.creer(u);
               resultat="bien";
               //HttpSession session=new HttpSession();
               
              // session.setMaxInactiveInterval(0);
                 setErreur("tous","Enregistrement réussit");
          }else{
              resultat="erreur";
              setErreur(mot_de_passeC,"Les deux mots de passe doivent etre identiques");
          }
         
        }
        request.setAttribute("utilisateur", u);
        return u;
    }
    
    
    private static String getValeurChampNom( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
    
    public boolean verifierPassword(HttpServletRequest request){
        String p1=request.getParameter(mot_de_passeC);
        String pass2=request.getParameter(mot_de_passe_2C);
        if(p1.equals(pass2)){
            return true;
        }else{
            return false;
        }
    }
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
    
    
    public Utilisateur connection(HttpServletRequest request){
        
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        
        Utilisateur ut=udao.getUtilisateur(username, password);
        //ut.setIdrole(null);
        
        if(ut==null){
            
            setErreur("login","Erreur de connection");
            
            
        }else{
           
            //ut.setIdrole(rdao.getRole(ut.get));
        }
        return ut;
        
        
    }
    
}
