<%-- 
    Document   : CreerPage
    Created on : 14 sept. 2018, 10:03:54
    Author     : Julie
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="stl" uri="http://ns.inria.fr/sewese/semtags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date" %>
<%@ page import="bf.julie.pidomen.entites.*" %>
<%@ page import="bf.julie.pidomen.dao.*" %>
<%

    PageDAO pageDAO = new PageDAO();
    MotsClesDAO motCleDao = new MotsClesDAO();
    CategorieDAO categorieDAO = new CategorieDAO();
    PageHasMotsClesDAO hasMotCleDao = new PageHasMotsClesDAO();
    PageHasCategorieDAO pageHasCategorieDAO = new PageHasCategorieDAO();
    Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
    String[] categories = request.getParameterValues("categories");
    String titre = request.getParameter("titre");
    String contenu = request.getParameter("contenu");
    String motsCle = request.getParameter("motcle");
    Page nouvellePage = new Page(titre, contenu, utilisateur);
    //nouvellePage.setMotCle(motsCle);
    //nouvellePage.setIdUtilisateur(utilisateur);
    
    pageDAO.creer(nouvellePage);

    if (categories != null) {
        for (String c : categories) {
            Categorie categorie = categorieDAO.getById(Long.valueOf(c));
            PageHasCategorie hasCategorie = new PageHasCategorie();
            hasCategorie.setIdCategorie(categorie);
            hasCategorie.setIdPage(nouvellePage);
            pageHasCategorieDAO.creer(hasCategorie);
        }
    }
    String[] tabMotsCle = motsCle.split("[,|;]");

    /*for (String m : tabMotsCle) {
        MotCle motCle = new MotCle(m);
        motCleDao.creer(motCle);
        PageHasMotCle pHasMotCle = new PageHasMotCle(motCle, nouvellePage);
        hasMotCleDao.creer(pHasMotCle);
    }*/
    
    out.println("Page enregistee avec succès");
%>


