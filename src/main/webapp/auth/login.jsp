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
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>

<h2>Login</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email">Email :</label>
    <input id="email" name="email" type="email" value="${param.email}"/><br>

    <label for="password">Mot de passe :</label>
    <input id="password" name="password" type="password"/><br>

    <button type="submit">Se connecter</button>
</form>

<a href="${pageContext.request.contextPath}/auth/register.jsp">S'inscrire</a>
</body>
</html>
