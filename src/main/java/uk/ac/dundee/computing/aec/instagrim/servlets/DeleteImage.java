/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import java.util.Date;
import java.text.DateFormat;
import java.util.TimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author iainmorton
 */
@WebServlet(name = "DeleteImage", urlPatterns = {"/Images/DeleteImage"})
public class DeleteImage extends HttpServlet {
    private Cluster cluster;

    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            String uuid=request.getParameter("picid");
            String user=request.getParameter("picuser");
            String date=request.getParameter("picadded");
            java.util.UUID convuuid;
            convuuid = java.util.UUID.fromString(uuid);
            /*DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH);
            TimeZone zone = TimeZone.getTimeZone("+0000");
            format.setTimeZone(zone);
            
            Date dateconv = format.parse(date);*/
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH);
            LocalDate dateconv = LocalDate.parse(date, formatter);
            java.util.Date check = java.sql.Date.valueOf(dateconv);
            PicModel tm = new PicModel();
            tm.setCluster(cluster);
            tm.deletePic(convuuid,user,check);
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        
        
        
    }
   
}
