/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.dao;

import bf.julie.pidomen.entites.MotCle;
import bf.julie.pidomen.entites.Page;
import bf.julie.pidomen.entites.PageHasMotCle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class PageDAO extends DAOAbstrait<Page, Long> {

    @Override
    public Class<Page> getClassType() {
        return Page.class;
    }

    public List<Page> getByTitre(String titre) {
        return em.createQuery("from Page p where p.titre LIKE '%" + titre + "%'", Page.class)
                .getResultList();
    }

    public List<Page> getAllPag() {
        return em.createQuery("from Page p", Page.class).getResultList();
    }

    public List<Page> getPageWithoutCat() {
        return em.createQuery("from Page c where c.id not in (select p.page.id from PageHasCategorie p)", Page.class).getResultList();
    }

    public List<Page> getByTitle(String titre) {
        return em.createQuery("from Page p where p.titre LIKE '" + titre + "%'", Page.class)
                .getResultList();
    }

    public List<MotCle> getMotCle(Long id) {
        return em.createQuery("select p.motCle from PageHasMotCle p where p.page.id=:id", MotCle.class)
                .setParameter("id", id).getResultList();
    }

    public String getMotsCleString(Long id) {
        StringBuilder builder = new StringBuilder();
        List<MotCle> motsCle = em.createQuery("select p.motCle from PageHasMotCle p where p.page.id=:id", MotCle.class)
                .setParameter("id", id).getResultList();
        int l = motsCle.size();
        if (l != 0) {
            builder.append("" + motsCle.get(0).getLibelle());
            for (int i = 1; i < l; i++) {
                builder.append("," + motsCle.get(i).getLibelle());
            }
        }
        return builder.toString();
    }

    public void mettreAJour(Page page, String motsCle) {
        supprimerMotsCle(page);
        mettreAJour(page);
        MotsClesDAO motCleDao = new MotsClesDAO();
        PageHasMotsClesDAO hasMotCleDao = new PageHasMotsClesDAO();
        if (motsCle != null && !motsCle.isEmpty() && motsCle.contains(",")) {
            String[] tokens = motsCle.split(",");
            for (String m : tokens) {
                MotCle motCle = new MotCle(m);
                motCleDao.creer(motCle);
                PageHasMotCle pHasMotCle = new PageHasMotCle(motCle, page);
                hasMotCleDao.creer(pHasMotCle);
            }
        }
    }

    public int supprimerMotsCle(Page p) {
        transaction.begin();
        int res = em.createQuery("delete from PageHasMotCle p where p.page.id=:id").setParameter("id", p.getId())
                .executeUpdate();
        transaction.commit();
        return res;
    }
    
    
    public List<Page> pageRecent(){
        
        String sql="select id from page order by date_creation desc limit 10";
        
        List<Integer> idP=(List<Integer>)em.createNativeQuery(sql).getResultList();
        
        List<Page> p=new ArrayList<>();
        for(int i=0;i<idP.size();i++){
            Page pa=em.find(Page.class, idP.get(i));
            System.out.println("PA :"+pa.getTitre());
            p.add(pa);
        }
        return p;
    }
    
    
    public List<Page> resultSearch(String subject){
        String sql="select distinct(p.id) from page p,categorie c,page_has_categorie pa where pa.id_categorie=c.id and p.id=pa.id_page and (p.titre like '%"+subject+"%' or c.nom like '%"+subject+"%' or p.contenu like '%"+subject+"%' or p.keyword like '%"+subject+"%')";
        List<Integer> idp=(List<Integer>)em.createNativeQuery(sql).getResultList();
        List<Page> pa=new ArrayList<>();
        
        for(int i=0;i<idp.size();i++){
            Page p=em.find(Page.class, idp.get(i));
            pa.add(p);
        }
        
        
        return pa;
    }
}
