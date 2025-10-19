<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
    <%@ page contentType="text/html;charset=UTF-8" %>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px;
    border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>

<h2>Login</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email">Email :</label>
    <input id="email" name="email" type="email" required><br>

    <label for="password">Password :</label>
    <input id="password" name="password" type="password" required><br>

    <button type="submit">Login</button>
</form>

<a href="${pageContext.request.contextPath}/auth/register.jsp">Don't have an account? Register</a>
</body>
</html>
