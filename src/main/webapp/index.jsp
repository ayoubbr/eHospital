<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
<div class="home-container">
    <h1><%= "Hello World!" %></h1>

    <!-- Cute Animated Owl -->
    <div class="animal-container">
        <svg class="owl" viewBox="0 0 200 200" xmlns="http://www.w3.org/2000/svg">
            <!-- Owl body -->
            <ellipse cx="100" cy="110" rx="50" ry="60" fill="none" stroke="#000000" stroke-width="2"/>

            <!-- Owl head -->
            <circle cx="100" cy="60" r="45" fill="none" stroke="#000000" stroke-width="2"/>

            <!-- Left ear -->
            <path d="M 70 25 Q 60 10 65 5" fill="none" stroke="#000000" stroke-width="2" stroke-linecap="round"/>

            <!-- Right ear -->
            <path d="M 130 25 Q 140 10 135 5" fill="none" stroke="#000000" stroke-width="2" stroke-linecap="round"/>

            <!-- Left eye -->
            <circle class="owl-eye" cx="80" cy="45" r="8" fill="none" stroke="#000000" stroke-width="2"/>
            <circle cx="80" cy="45" r="4" fill="#000000"/>

            <!-- Right eye -->
            <circle class="owl-eye" cx="120" cy="45" r="8" fill="none" stroke="#000000" stroke-width="2"/>
            <circle cx="120" cy="45" r="4" fill="#000000"/>

            <!-- Beak -->
            <path d="M 100 60 L 95 70 L 105 70 Z" fill="#000000"/>

            <!-- Left wing -->
            <path d="M 60 100 Q 40 110 45 140" fill="none" stroke="#000000" stroke-width="2" stroke-linecap="round"/>

            <!-- Right wing -->
            <path d="M 140 100 Q 160 110 155 140" fill="none" stroke="#000000" stroke-width="2" stroke-linecap="round"/>

            <!-- Feet -->
            <line x1="90" y1="170" x2="90" y2="185" stroke="#000000" stroke-width="2"/>
            <line x1="110" y1="170" x2="110" y2="185" stroke="#000000" stroke-width="2"/>
            <path d="M 85 185 L 95 185" stroke="#000000" stroke-width="2" stroke-linecap="round"/>
            <path d="M 105 185 L 115 185" stroke="#000000" stroke-width="2" stroke-linecap="round"/>
        </svg>
    </div>

    <div>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                    ${errorMessage}
            </div>
        </c:if>
    </div>

    <div class="navigation">
        <a href="login" class="nav-link">Login</a>
        <a href="register" class="nav-link">Register</a>
    </div>
</div>
</body>
</html>
