/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.servlets;

import bf.julie.pidomen.dao.*;
import bf.julie.pidomen.entites.*;
import bf.julie.pidomen.exec.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.primefaces.shaded.commons.io.IOUtils;

/**
 *
 * @author ilboudo
 */
public class CreatePagesServlet extends HttpServlet {
    public EntityManagerFactory emf ;//= null;
    public static final int TAILLE_TAMPON=10240;
    public static final String CHEMIN_FINAL="src/";//Ou seront stocker les fichiers au final
    boolean erreur;
    String message="";
    private final String UPLOAD_DIRECTORY = "uploadfiles";
    
     // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
    
    //emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
    public String vue="/Membres/CreatePages.jsp";
    UtilisateurJpaController userJpa;
    
    public List<Categorie> listCat=new ArrayList<>();
    
    
    PageJpaController pageExec;//=new PageJpaController();
    PageHasCategorieJpaController phCExec;
    CategorieJpaController catExec;
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recupperons les attributs envoyes
        
        System.out.println("DEBUT!!!!!");
        
        HttpSession session=req.getSession();
        emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
        String titre=req.getParameter("titre");
        String mo_cle=req.getParameter("cle");
        String [] cats=req.getParameterValues("categories");
        
       // System.out.println(req.getParameterValues("categories")+"  c:"+titre);
        String contenu=req.getParameter("c");
        System.out.println("CONTENU :"+contenu);
         System.out.println("FICHIERV :::"+req.getParameter("doc"));
        //Retrouvons les categories selectionnees
        catExec=new CategorieJpaController(emf);
        userJpa=new UtilisateurJpaController(emf);
        phCExec=new PageHasCategorieJpaController(emf);
        
        listCat=catExec.findCategorieEntities();
        req.setAttribute("categories", listCat);
        
      // gets absolute path of the web application
        String appPath = req.getServletContext().getRealPath("");
        System.out.println("APP PATH:"+appPath);
        
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;//+ File.separator
        String savePath2=appPath  + UPLOAD_DIRECTORY;
        
        System.out.println("Save path:"+savePath);
         
        // creates the save directory if it does not exists
        
        
        
        
        vue="/Connexion.jsp";
        if(session.getAttribute("login")==null){//Verifier sil ya variable session
            
            this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        }else{
            Page page=new Page();
            try {
                List<Categorie> listc=new ArrayList<>();
               // System.out.println("CAT:"+cats.length+" STRING :"+cats.toString());
                
                for(int i=0;i<cats.length;i++){
                    listc.add(catExec.findCategorie(Long.valueOf(cats[i])));
                  
                }


                Utilisateur user=new Utilisateur();
                user=userJpa.findUtilisateur(Long.valueOf(session.getAttribute("id").toString()));
                
                page.setTitre(titre);
                page.setContenu(contenu);
                page.setModifier_par(user);
                page.setIdUtilisateur(user);
                page.setKeyWords(mo_cle);
                page.setDateCreation(new Date());
                
               
                File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        
        Part partDoc=req.getPart("doc");
        if(partDoc!=null){
            String fileName = extractFileName(partDoc);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            System.out.println("File:"+fileName);
            partDoc.write(savePath + File.separator + fileName);
            page.setDoc(UPLOAD_DIRECTORY+"/"+  fileName);
        }
        
        Part partImg_1=req.getPart("img1");
        
        if(partImg_1!=null){
            String fileName = extractFileName(partImg_1);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            System.out.println("File:"+fileName);
            
            partImg_1.write(savePath + File.separator + fileName);
            page.setImg1(UPLOAD_DIRECTORY+"/"+  fileName);
        }
        
        
        Part partImg_2=req.getPart("img2");
        
        
        if(partImg_2!=null){
            String fileName = extractFileName(partImg_2);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            System.out.println("File:"+fileName);
            partImg_2.write(savePath + File.separator + fileName);
            page.setImg2(UPLOAD_DIRECTORY+"/"+  fileName);
        }
        
        Part partImg_3=req.getPart("img3");
        
        if(partImg_3!=null){
            String fileName = extractFileName(partImg_3);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            System.out.println("File:"+fileName);
            partImg_3.write(savePath + File.separator + fileName);
            page.setImg3(UPLOAD_DIRECTORY+"/"+  fileName);
        }
        
        Part partImg_4=req.getPart("img4");
        
        
         if(partImg_4!=null){
            String fileName = extractFileName(partImg_4);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            System.out.println("File:"+fileName);
            partImg_4.write(savePath + File.separator + fileName);
            page.setImg4(UPLOAD_DIRECTORY+"/"+  fileName);
        }
                
                
                
                
                //Recuperons les fichiers
                //Part partDoc=req.getPart("doc");
                
               // String docNom=getNomFichier(partDoc);
//                
//                //Si on a bien un fichier

//                 if (docNom != null && !docNom.isEmpty()) {
//                    String nomChamp = partDoc.getName();
//                    // Corrige un bug du fonctionnement d'Internet Explorer
//                     docNom = docNom.substring(docNom.lastIndexOf('/') + 1)
//                            .substring(docNom.lastIndexOf('\\') + 1);
//
//                    // On écrit définitivement le fichier sur le disque
//                    //ecrireFichier(partDoc, docNom, CHEMIN_FINAL);
//
//                   // req.setAttribute(nomChamp, docNom);
//                }

               
                InputStream doc=req.getPart("doc").getInputStream();
                System.out.println("DOCCCC :"+doc.toString());
                //page.setDoc(new byte[doc.available()]);
                
                //page.setDoc(IOUtils.toByteArray(doc));
                
                
//               InputStream img1=req.getPart("img1").getInputStream();
//                page.setImg1(IOUtils.toByteArray(img1));
//                
//                InputStream img2=req.getPart("img2").getInputStream();
//                page.setImg2(IOUtils.toByteArray(img2));
//                InputStream img3=req.getPart("img3").getInputStream();
//                page.setImg3(IOUtils.toByteArray(img3));
//                InputStream img4=req.getPart("img4").getInputStream();
//                page.setImg4(IOUtils.toByteArray(img4));
                
                page.setDate_modification(new Date());
                PageDAO pageDAO = new PageDAO();
                pageDAO.creer(page);
                
//                System.out.print("1");
//                //pageExec.create(page);
//                 System.out.print("1");
                 PageHasCategorieDAO phCDAO=new PageHasCategorieDAO();
                 
                for(int i=0;i<listc.size();i++){
                    
                    PageHasCategorie phC=new PageHasCategorie();
                    phC.setIdPage(page);
                    phC.setIdCategorie(listc.get(i));
                   phCDAO.creer(phC);
                   // phCExec.create(phC);
                }
                
                
               req.setAttribute("erreur", "false");
               
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("erreur","true");
                req.setAttribute("page", page);
                req.setAttribute("err", e.getLocalizedMessage()+" / "+e.toString());
                System.out.println(e.getLocalizedMessage());
            }
            vue="/Membres/CreatePages.jsp";
            this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        }
        
        
       // super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        emf=Persistence.createEntityManagerFactory("PlateformeMeningitPU");
        catExec=new CategorieJpaController(emf);
        listCat=catExec.findCategorieEntities();
        System.out.println("TAILLE CATEGORIE :"+listCat.size());
        req.setAttribute("categories", listCat);
        vue="/Connexion.jsp";
        if(session.getAttribute("login")==null){//Verifier sil ya variable session
            
            this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        }else{
            vue="/Membres/CreatePages.jsp";
            this.getServletContext().getRequestDispatcher(vue).forward( req, resp );
        }
        
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (Exception ignore) {
                System.out.println("SORTIE CATCH:"+ignore.getLocalizedMessage());
            }
            try {
                entree.close();
            } catch (IOException ignore) {
                 System.out.println("ENTREE CATCH:"+ignore.getLocalizedMessage());
            }
        }
    }
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }
    
    
    
    
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    
}
