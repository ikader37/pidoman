/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import bf.julie.pidomen.dao.PageDAO;
import bf.julie.pidomen.dao.PageHasCategorieDAO;
import bf.julie.pidomen.entites.*;
import bf.julie.pidomen.exec.CategorieJpaController;
import bf.julie.pidomen.exec.PageHasCategorieJpaController;
import bf.julie.pidomen.exec.PageJpaController;
import bf.julie.pidomen.exec.UtilisateurJpaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 *
 * @author ilboudo
 */
@WebServlet(name="editPage",urlPatterns = "/editPage")
public class EditPagesServlet extends HttpServlet{
    
    public String vue="/Membres/EditPages.jsp";

    PageDAO pagDAO=new PageDAO();
    
    
     public EntityManagerFactory emf ;//= null;
    
    boolean erreur;
    String message="";
    
    
    
    UtilisateurJpaController userJpa;
    
    public List<Categorie> listCat=new ArrayList<>();
    
    
    PageJpaController pageExec;//=new PageJpaController();
    PageHasCategorieJpaController phCExec;
    CategorieJpaController catExec;
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
       
       
       String idP=req.getParameter("idPage");
       HttpSession session=req.getSession();
       
       
       Long id=Long.parseLong(idP);
       String contenu=req.getParameter("contenu");
       String titre=req.getParameter("titre");
       String [] cat=req.getParameterValues("categories");
       
       String motCle=req.getParameter("cle");
        if(session.getAttribute("login")==null){//Verifier sil ya variable session
            
            this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        }else{
            Page page=pagDAO.getById(id);
            try {
                List<Categorie> listc=new ArrayList<>();
                for(int i=0;i<cat.length;i++){
                    listc.add(catExec.findCategorie(Long.valueOf(cat[i])));
                    
                    
                }
                Utilisateur user=new Utilisateur();
                user=userJpa.findUtilisateur(Long.valueOf(session.getAttribute("id").toString()));
                
                page.setTitre(titre);
                page.setContenu(contenu);
                page.setModifier_par(user);
                //page.setModifier_par(user);
                page.setKeyWords(motCle);
                //page.setDateCreation(new Date());
                page.setDate_modification(new Date());
                PageDAO pageDAO = new PageDAO();
                pageDAO.mettreAJour(page);
                
                System.out.print("1");
                //pageExec.create(page);
                 System.out.print("1");
                 PageHasCategorieDAO phCDAO=new PageHasCategorieDAO();
                 
                 for(int i=0;i<page.getPageHasCategorieList().size();i++){
                  
                     for(int j=0;j<listc.size();j++){
                         
                         if(!page.getPageHasCategorieList().get(i).getIdCategorie().equals(listc.get(j))){
                             
                             PageHasCategorie phC=new PageHasCategorie();
                            phC.setIdPage(page);
                            phC.setIdCategorie(listc.get(i));
                           phCDAO.creer(phC);
                         }
                     }
                 }
                 
//                for(int i=0;i<listc.size();i++){
//                    
//                    PageHasCategorie phC=new PageHasCategorie();
//                    phC.setIdPage(page);
//                    phC.setIdCategorie(listc.get(i));
//                   phCDAO.creer(phC);
//                   // phCExec.create(phC);
//                }
//                
                
               req.setAttribute("erreur", "false");
               
            } catch (Exception e) {
                
                req.setAttribute("erreur","true");
                req.setAttribute("page", page);
                req.setAttribute("err", e.getLocalizedMessage()+" / "+e.getMessage());
                System.out.println(e.getLocalizedMessage());
            }
            vue="/Membres/CreatePages.jsp";
            this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        }
       
       
       
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
       Page pa=(Page)req.getAttribute("page");
       
       String idp=req.getParameter("page");
       
       Long idpage=Long.parseLong(idp);
        HttpSession session=req.getSession();
        emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
        catExec=new CategorieJpaController(emf);
        listCat=catExec.findCategorieEntities();
        req.setAttribute("categories", listCat);
        
       //Retrouvons la page en question
       
       Page page=pagDAO.getById(idpage);
       
       //Enlevons les categorie de la pages a l'ensemble des categorie
       
       for(int i=0;i<page.getPageHasCategorieList().size();i++){
           
           listCat.remove(page.getPageHasCategorieList().get(i).getIdCategorie());
       }
       
       
       req.setAttribute("pageEdit", page);
       req.setAttribute("categories", listCat);
       
       this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
    }
    
}
