<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/15/2017
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>ADD</title>
</head>
<body>
<form action="/phoneBook" method="post">
    Name:<br>
    <input type="text" name="name" value="${phone.name}" required><br>
    Phone Number:<br>
    <input type="number" name="phnnumber" value="${phone.phoneNumber}" required><br>
    <input type="submit" value="Add">
    <input type="number" name = "id" value="${phone.id}" hidden>
    <input type="text" name="editOrAdd" value="${editOrAdd}" hidden>

</form>
</body>
</html>
