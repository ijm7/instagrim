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
import java.util.UUID;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
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
            HttpSession session=request.getSession();
            String uuid=request.getParameter("picid");
            String user=request.getParameter("picuser");
            java.util.UUID convuuid;
            convuuid = UUID.fromString(uuid);
            PicModel tm = new PicModel();
            tm.setCluster(cluster);
            tm.deletePic(convuuid,user);
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            lg.minusImageCount();
            lg.setDelete(true);
            //RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            //rd.forward(request,response);
        response.sendRedirect("/Instagrim");
        
        
        
        
    }
   
}
