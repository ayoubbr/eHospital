<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 14/10/2025
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>Rooms</title></head>
<body>
<h2>Rooms</h2>
<a href="rooms?action=new">Add Room</a>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Capacity</th><th>Actions</th></tr>
    <jsp:useBean id="rooms" scope="request" type="java.util.List"/>
    <c:forEach var="r" items="${rooms}">
        <tr>
            <td>${r.id}</td>
            <td>${r.name}</td>
            <td>${r.capacity}</td>
            <td>
                <a href="rooms?action=edit&id=${r.id}">Edit</a> |
                <a href="rooms?action=delete&id=${r.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>


