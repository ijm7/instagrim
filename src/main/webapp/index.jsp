<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <br>
            <h1>InstaGrim ! </h1>
            <h2>Your world in Black and White</h2>
        </header>

        
        <nav>
            <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
            
            <ul class="navbar">
                <li><a href="/Instagrim/Upload">Upload</a></li>
                    
                <li><a href="/Instagrim/Profile">Your Profile</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
                <li><a href="/Instagrim/Logout">Logout</a></li>
               
                    
                 
                
                
                
                
            </ul>
                    <%}
                            }else{
                                %>
                    
                    <ul class="navbar">
            
                <li><a href="/Instagrim/Register">Register</a></li>
                <li><a href="/Instagrim/Login">Login</a></li>
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
                    <%}
                    %>
            </ul>
        </nav>
    <homebody>
        <div class="centerer">
           
        <img style="width:100%;height:auto;"src="landscape.jpg" alt="landscape">
        <br>
        <br>
        </div>
                    
                    
                    <%
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                    <h3>Welcome, <%=lg.getUsername()%>!</h3>
                                
                    <p id="para1" style="text-align:center; ">Click <a href="/Instagrim/Images/<%=lg.getUsername()%>">here</a> to go to your images</p>
                    <%}
                            }else{
                                %>
                                <h3>What is instagrim?</h3>
                    
                    <p id="para1" style="text-align:center;">Instagrim allows you to store pictures with image filters applied to them!</p>
                    <%
                           
                    }%>
    </homebody>
                    
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
                
            </ul>
        </footer>
    </body>
</html>
