<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/index.jsp"/>
</c:if>
<html>
<head>
    <title>Departments</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body>
<!-- added admin dashboard link and improved structure -->
<div style="text-align: right; margin-bottom: 20px;">
    <c:choose>
        <c:when test="${sessionScope.user.role == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/admin/dashboard.jsp">Admin Dashboard</a>
        </c:when>
        <c:when test="${sessionScope.user.role == 'DOCTOR'}">
            <a href="${pageContext.request.contextPath}/doctor/dashboard.jsp">Doctor Dashboard</a>
        </c:when>
        <c:when test="${sessionScope.user.role == 'PATIENT'}">
            <a href="${pageContext.request.contextPath}/patient/dashboard.jsp">Patient Dashboard</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
        </c:otherwise>
    </c:choose>
</div>

<h2>Departments</h2>

<!-- replaced inline error style with error-container class -->
<c:if test="${not empty errorMessage}">
    <div class="error-container">
            ${errorMessage}
    </div>
</c:if>

<a href="departments?action=new">Add Department</a>

<c:if test="${not empty departments}">
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <jsp:useBean id="departments" scope="request" type="java.util.List"/>
        <c:forEach var="r" items="${departments}">
            <tr>
                <td>${r.id}</td>
                <td>${r.name}</td>
                <td>
                    <a href="departments?action=edit&id=${r.id}">Edit</a> |
                    <a href="departments?action=delete&id=${r.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
