<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/15/2017
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="list" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Contacts</title>
</head>
<body>
    <a href="/phoneBook?op=sendtoaddpage">ADD</a>
    <table>
        <tr>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:if test="${empty list}"><br>
            No contacts found.
        </c:if>
        <c:forEach var="info" items="${list}">
        <tr>
        <td>${info.name}</td>
                <td>${info.phoneNumber}</td>
            <td><a href="/phoneBook?op=edit&Id=${info.id}">Edit</a></td>
            <td><a href="/phoneBook?op=delete&Id=${info.id}">Delete</a></td>
        </tr>
        </c:forEach>


    </table>
<br><br>

</body>
</html>
