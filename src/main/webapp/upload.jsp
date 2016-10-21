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
                <li><a href="/Instagrim">Home</a></li>
                <li><a href="/Instagrim/Profile">Your Profile</a></li>
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
                
            </ul>
        </nav>
 
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                <p class="loggtext">File to upload: <input type="file" name="upfile"></p><br/>

                
            <div class="filterformat">    
                    <table style="width:20%;"><tr>
                    
                    <th style="width:20%;">✓</th>
                    <th>Filter</th>
                    
                    </tr>
                    <tr><td><input type="radio" name="filter" value="blackandwhite"></td><td><p>Black and White</p></td></tr>
                    <tr><td><input type="radio" name="filter" value="brighter"></td><td><p>Bright</p></td></tr>
                    <tr><td><input type="radio" name="filter" value="darker"></td><td><p>Dark</p></td></tr>
                    </table>
            </div>
                    <br>
                    <br>
                    
                <div class="centerer">    
                <input class="imasubmit" type="submit" value="Press">
                </div>
            </form>
            <%
                        
                        UploadSuccess upload  = (UploadSuccess) session.getAttribute("UploadSuccess");
                        if (upload != null) {
                            
                            if (upload.getUploadSuccess()) {
                                upload.setUploadSuccess(false);
                    %>
                    <p class="loggtext" style="color:green;">Upload successful!</p>
                    <%}}%>
            <br>
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
