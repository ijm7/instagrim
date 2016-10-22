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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;

/**
 *
 * @author iainmorton
 */
@WebServlet(name = "DeleteImage", urlPatterns = {"/Images/DeleteImage"})
public class DeleteImage extends HttpServlet {
    private Cluster cluster;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String uuid=request.getParameter("picid");
         String user=request.getParameter("picuser");
         String date=request.getParameter("picadded");
         PicModel tm = new PicModel();
         tm.setCluster(cluster);
         tm.deletePic(uuid,user,date);
         RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
	 rd.forward(request,response);
        
        
    }
   
}
