<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/14/2017
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"   prefix = "c" %>
<html>
  <head>
    <title>Phone Book</title>
    <h1>Welcome to the Phone Book</h1><br>
  </head>
  <body>
  <form action="/user" method="post">
    <h2>Login</h2><br>
    User Name:<br>
    <input type = "text" name="usrname"><br>
    Password:
    <input type = "password" name="usrpassword"><br>
    ${message}
    <input type="submit" value="Login">
    <input type="text" name="logorreg" value="login" hidden>

  </form>
  <form action="/user" method="post">
    <h2>Register</h2><br>
    User Name:<br>
    <input type = "text" name="rusrname"><br>
    Password:
    <input  id='userpass' type = "password" name="rusrpassword" required><br>
    Confirm Password:
    <input onkeyup="checkPassword()" id=confpass type ="password" name = "conformpassword" required>
    <put id="message" ></put><br>
    <input id='submit' style='visibility: hidden;' type="submit" value="Register">
    <input type="text" name="logorreg" value="register" hidden>
  </form>
  <script>
    function checkPassword(){

        if(document.getElementById('userpass').value==document.getElementById('confpass').value){
            document.getElementById('message').innerHTML="Password matched";
            document.getElementById('submit').style.visibility='visible';
        }
        else{
            document.getElementById('message').innerHTML="Password doesn't match";
        }

    }
  </script>
  </body>
</html>
