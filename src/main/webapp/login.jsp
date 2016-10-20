<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
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
       
        <logg>
            <h3>Login</h3>
            <!--
            <form method="POST"  action="Login">
                <ul>
                    <li id="list1">User Name <input type="text" name="username"></li>
                    <li id="list1">Password <input type="password" name="password"></li>
                </ul>
                
                <
                <br/>
                <input type="submit" value="Login"> 
            </form>
            -->
            <form method="POST"  action="Login">
               
                <p class="loggtext">User Name</p> <input type="text" name="username">
                <p class="loggtext">Password</p> <input type="password" name="password">
                
                
                
                <br/>
                <br/>
                <%
                        
                        ErrorCatch err  = (ErrorCatch) session.getAttribute("ErrorCatch");
                        if (err != null) {
                            boolean error = err.getLoginError();
                            if (err.getLoginError()) {
                    %>
                    <p class="loggtext" style="color:red;">Error: Username or password has been incorrectly entered</p>
                        <%}}else{%>
                        <br/><%}%>
                <br/>
                <br/>
                 <br/>
                <br/>
                <input type="submit" value="Login"> 
            </form>

        </logg>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
