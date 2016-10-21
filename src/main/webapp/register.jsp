<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
      
       
    </head>
    <body>
        <header>
            <br>
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul class="navbar">
                <li><a href="/Instagrim">Home</a></li>
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
       
        <!--<article>
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>
                </ul>
                <br/>
                <input type="submit" value="Register"> 
            </form>

        </article>-->
    <logg>
        <form method="POST"  action="Register">
            <br>
        
        <p class="loggtext">User Name</p><input type="text" name="username" required>
        <p class="loggtext">Password</p><input type="password" name="password" required>
        <p class="loggtext">First Name</p><input type="text" name="first_name" required>
        <p class="loggtext">Last Name</p><input type="text" name="last_name" required>
        <p class="loggtext">Email Address</p><input type="email" name="email" required>
        
        
                <br/>
                <br/>
                
                
               <%
                        
                        ErrorCatch err  = (ErrorCatch) session.getAttribute("ErrorCatch");
                        if (err != null) {
                            boolean error = err.getLoginError();
                            if (err.getLoginError()) {
                                err.setLoginError(false);
                    %>
                    <p class="loggtext" style="color:red;">Error: This username may have already been taken, or your email address has been registered already</p>
                        <%}}else{%>
                        <br/><%}%>
                <br/>
                <input type="submit" value="Register"> 
        </form>
        <br>
       
    </logg>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
