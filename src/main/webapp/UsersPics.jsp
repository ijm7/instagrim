<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <header>
        <br>
        <h1>InstaGrim ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        <%
                        String url=request.getAttribute("javax.servlet.forward.request_uri").toString();
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>                   
        <nav>
            <%if (lg != null) {
                            String UserName = lg.getUsername();%>
            <ul class="navbar">
                <li><a href="/Instagrim">Home</a></li>
                <li><a href="/Instagrim/Upload">Upload</a></li>
                <li><a href="/Instagrim/Profile">Your Profile</a></li>
                <%if (!url.equals("/Instagrim/Images/majed")) {
                    %>
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
                <li><a href="/Instagrim/Logout">Logout</a></li>
                </ul>
                <%}else{%> 
                <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/Instagrim/Logout">Logout</a></li>
                </ul>
                <%}}else{%>
                <ul class="navbar">
                <li><a href="/Instagrim">Home</a></li>
                <li><a href="/Instagrim/Register">Register</a></li>
                <li><a href="/Instagrim/Login">Login</a></li><%}%>
        </nav>
        <div class="pictureformat">
                            <%if (!url.equals("/Instagrim/Images/majed")) {
                    %>
            <h1>Your Pics</h1>
            <%}else{%>
            <h1>Sample Pics</h1>
            <%}%>   
        <%
            int counter=0;
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p class="loggtext">No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();
              counter++;

        %>
        <table>
        <tr>          
        <th>Image</th>
        <th>Picture Information</th>
        <th>Options</th>
        </tr>
        <tr>
            <td style="width:20%;"><a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a></td>
            <td><p class="loggtext">Date and Time of upload: <%=p.getTextDate()%></p></td>
            <td style="width:25%;"><form method="GET" enctype="multipart/form-data" action="DeleteImage">
                    <input type="hidden" name="picid" value="<%=p.getSUUID().toString()%>">
                    <input type="hidden" name="picuser" value="<%=p.getUser()%>">
                    <input type="submit" value="Delete"></form>
                    <br></td></tr></table><%
            }
            }
        %>
        </div>
        <footer>
        </footer>
    </body>
</html>
