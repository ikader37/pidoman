
import bf.julie.pidomen.dao.PageHasMotsClesDAO;
import bf.julie.pidomen.entites.MotCle;
import bf.julie.pidomen.entites.Page;
import bf.julie.pidomen.entites.PageHasMotCle;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hp
 */
public class Main {

    public static void main(String[] args) throws IOException  {
//        PageHasMotsClesDAO pageHasMotCleDAO = new PageHasMotsClesDAO();
//        List<PageHasMotCle> all = pageHasMotCleDAO.getAll();
//        Set<MotCle> motCles = all.stream().map(item -> item.getMotCle()).collect(Collectors.toSet());
//        Set<Page> pages = all.stream().map(item -> item.getPage()).collect(Collectors.toSet());
//        Map<String , List<Page>> motsCleMap = new HashMap<>();
//        PrintWriter fichier = new PrintWriter(new BufferedWriter(new FileWriter("resultats.txt")));
//        String ligne = "";
//        for(Page p: pages) {
//            ligne +=  "\t" + p.getId();
//        }
//        fichier.println(ligne);
//        motCles.forEach(motCle -> {
//            String ligneTag = motCle.getLibelle()+"";
//            for(Page p: pages) {
//                ligneTag += 
//                all.stream().anyMatch(item -> item.getMotCle().getId() == motCle.getId() && item.getPage().getId() == p.getId())
//                        ? "\t1" : "\t0";
//            }
//            fichier.println(ligneTag);
//            System.out.println(ligneTag);
//        });
//        fichier.flush();
    }
}
