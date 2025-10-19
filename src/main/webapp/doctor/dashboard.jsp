<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><c:if test="${empty sessionScope.user}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP - DOCTOR</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>
<div class="admin-container">
    <h1>Doctor Dashboard</h1>
    <div style="text-align: right;">
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="error-message">
                ${errorMessage}
        </div>
    </c:if>

    <nav class="navigation">
        <a href="${pageContext.request.contextPath}/rooms" class="nav-link">Rooms</a>
        <a href="${pageContext.request.contextPath}/departments" class="nav-link">Departments</a>
        <a href="${pageContext.request.contextPath}/consultations" class="nav-link">Consultations</a>
    </nav>
</div>
</body>
</html>
