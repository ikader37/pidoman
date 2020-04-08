/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.julie.pidomen.util;

import bf.julie.pidomen.dao.PageHasCategorieDAO;
import bf.julie.pidomen.exec.RoleJpaController;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author hp
 */
public class Util {
    
    static EntityManagerFactory em=Persistence.createEntityManagerFactory("plateformeMeningitPU");
    public static  RoleJpaController rjpa=new RoleJpaController(em);
    
//    public static String generateCategoriesPieChart(String dir) throws IOException {
//        final int NB_CATEGORIES_TO_SHOW = 10;
//        
//        PageHasCategorieDAO dao = new PageHasCategorieDAO();
//        DefaultPieDataset dataset = new DefaultPieDataset();
//        List<Object[]> result = dao.getTenMostUsedCategories();
//        boolean hasOtherCategories = result.size() == NB_CATEGORIES_TO_SHOW;
//        String excludedIds = "";
//        int i = 0;
//        
//        for(Object[] o: result) {
//            Long occ = (Long)o[1];
//            if(occ == 0)
//            {
//                hasOtherCategories = false;
//                break;
//            }
//            
//            if(i != 0)
//            {
//                excludedIds += "," + o[2];
//            }
//            
//            else {
//                excludedIds += o[2];
//            }
//            dataset.setValue((String)o[0] , occ);
//            i++;
//        }
//        
//        if(hasOtherCategories) {
//            Double occ = (double)(long)dao.getOtherCategoriesCount(excludedIds);
//            dataset.setValue("Autres" , occ);
//            System.out.println(dao.getOtherCategoriesCount(excludedIds));
//        }
//        System.out.println(String.format("(%s)" , excludedIds));
//        JFreeChart chart = ChartFactory.createPieChart(
//                "Catégories les plus utilisées", // chart title
//                dataset, // data
//                true, // include legend
//                true,
//                false);
//        int width = 900;
//        int height = 700;
//        File pieChart = new File(dir + "/piechart.png");
//            ChartUtilities.saveChartAsJPEG(pieChart, chart, width, height);
//       
//        return "piechart.png";
//    }
}
