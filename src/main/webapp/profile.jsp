<%-- 
    Document   : profile
    Created on : 17-Oct-2016, 13:46:44
    Author     : iainmorton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        
        <title>Instagrim</title>
    </head>
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
                <li><a href="/Instagrim">Home</a></li>
                <li><a href="/Instagrim/Upload">Upload</a></li>
                    
                
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
                    <%}
                    %>
                
                
                
            </ul>
                    
                    
                    
        </nav>
                    <br>
                    <br>
                    <br>
    <div class="pictureformat">
        <table style="width:35;"><tr>
                    
                <th colspan="2">Your Details</th>
                    
                    
                    </tr>
                    <tr><td><b>Username</b></td><td><p><%=lg.getUsername()%></p></td></tr>
                    <tr><td><b>First Name</b></td><td><p><%=lg.getUserFirstName()%></p></td></tr>
                    <tr><td><b>Last Name</b></td><td><p><%=lg.getUserLastName()%></p></td></tr>
                    <tr><td><b>Email</b></td><td><p><%=lg.getUserEmail()%></p></td></tr>
                    <tr><td><b>Image Count</b></td><td><p><%=lg.getImageCount()%></p></td></tr>
                    </table>
        
        
    </div>
    
    <body>
        
    </body>
</html>
