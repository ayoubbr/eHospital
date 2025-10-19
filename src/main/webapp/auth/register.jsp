<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
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

<h2>Register</h2>
<form action="${pageContext.request.contextPath}/register" method="post">
    <label for="email">Email :</label>
    <input id="email" name="email" type="email" value="${param.email}"/><br>

    <label for="firstName">Prénom :</label>
    <input id="firstName" name="firstName" type="text" value="${param.firstName}"/><br>

    <label for="lastName">Nom :</label>
    <input id="lastName" name="lastName" type="text" value="${param.lastName}"/><br>

    <label for="password">Mot de passe :</label>
    <input id="password" name="password" type="password"/><br>

    <label for="confirm">Confirmer :</label>
    <input id="confirm" name="confirm" type="password"/><br>

    <button type="submit">S'inscrire</button>
</form>

<a href="${pageContext.request.contextPath}/auth/login.jsp">Déjà inscrit ? Se connecter</a>
</body>
</html>
