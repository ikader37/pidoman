<%-- 
    Document   : traite
    Created on : 20 oct. 2018, 15:47:45
    Author     : hp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="stl" uri="http://ns.inria.fr/sewese/semtags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import="java.util.Date" %>
<%
  try {
        String id = request.getParameter("idcat");
        String idp = request.getParameter("idpage");
        Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidomen_db?" + "user=root&password=");  
     
     Statement st= con.createStatement(); 
     ResultSet rs; 
     int a= st.executeUpdate("INSERT into page_has_categorie(idpage,id_cat) VALUES ('"+idp+"','"+id+"')");
    
           if(a>0){out.println("yes");
       
        } else {
            out.println("invalid");
        }
        
        }
     catch (Exception e) {
        e.printStackTrace();

    }
%>
