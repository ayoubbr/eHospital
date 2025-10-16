<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<div><c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>
</div>
<br/>
<a href="rooms">Rooms</a>
<a href="departments">Departments</a>
<a href="doctors">Doctors</a>
<a href="consultations">Consultations</a>
</body>
</html>