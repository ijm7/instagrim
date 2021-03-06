/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;
import uk.ac.dundee.computing.aec.instagrim.stores.ErrorCatch;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    Cluster cluster=null;
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
       RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rd.forward(request, response);
        
    }



    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String first_name=request.getParameter("first_name");
        String last_name=request.getParameter("last_name");
        User us=new User();
        us.setCluster(cluster);
        boolean space=us.IsAvailable(username, email);
        HttpSession session=request.getSession();
        System.out.println("Session in servlet "+session);
        if (space){
            us.RegisterUser(username, password, email,first_name,last_name);
            LoggedIn lg= new LoggedIn();
            System.out.println("yes");
            lg.setLogedin();
            lg.setUsername(username);
            lg.setUserFirstName(first_name);
            lg.setUserLastName(last_name);
            lg.setUserEmail(email);
            lg.setImageCount(us.getImageAmount(username));
            session.setAttribute("LoggedIn", lg);
            System.out.println("Session in servlet "+session);
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
	    rd.forward(request,response);
	response.sendRedirect("/Instagrim");
        }
        else{
            System.out.println("no");
            ErrorCatch err = new ErrorCatch();
            err.setLoginError(true);
            session.setAttribute("ErrorCatch", err);
            response.sendRedirect("/Instagrim/register.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
