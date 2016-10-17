<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

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
        
        <p class="loggtext">User Name</p><input type="text" name="username">
        <p class="loggtext">Password</p><input type="password" name="password">
        <p class="loggtext">First Name</p><input type="text" name="first_name">
        <p class="loggtext">Last Name</p><input type="text" name="last_name">
        <p class="loggtext">Email Address</p><input type="email" name="email">
        
        
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <input type="submit" value="Register"> 
        </form>
    </logg>
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
