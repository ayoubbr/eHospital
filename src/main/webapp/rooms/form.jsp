<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 14/10/2025
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Room Form</title></head>
<body>
<form action="rooms" method="post">
    <input type="hidden" name="id" value="${room.id}"/>
    <label>Name:</label><input type="text" name="name" value="${room.name}"/><br>
    <label>Capacity:</label><input type="number" name="capacity" value="${room.capacity}"/><br>
    <button type="submit">Save</button>
</form>
<a href="rooms">Back</a>
</body>
</html>
