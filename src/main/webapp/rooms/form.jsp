<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Room Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body>

<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>

<form action="rooms" method="post">
    <input type="hidden" name="id" value="${room.id}"/>
    <label>Name:</label><input type="text" name="name" value="${room.name}"/><br>
    <label>Capacity:</label><input type="number" name="capacity" value="${room.capacity}"/><br>
    <button type="submit">Save</button>
</form>
<a href="rooms">Back</a>
</body>
</html>
