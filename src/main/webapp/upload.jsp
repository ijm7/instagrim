<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
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
                <%
                    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                %>
                <li><a href="/Instagrim/Upload">Upload</a></li>
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
            </ul>
        </nav>
 
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                <p class="loggtext">File to upload: <input type="file" name="upfile"></p><br/>

                <br/>
                <div class="centerer">
                <input type="radio" name="filter" value="blackandwhite"> Black and White<br>
                <input type="radio" name="filter" value="block">Block<br>    
                    
                <input class="imasubmit" type="submit" value="Press">
                
            </form>
            <br>
            <form action="">
                <div class="centerer">
                
                </div>
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
