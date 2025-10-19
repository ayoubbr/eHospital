<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Doctors</title>
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

<h2>Doctors</h2>

<!-- replaced inline error style with error-container class -->
<c:if test="${not empty errorMessage}">
    <div class="error-container">
            ${errorMessage}
    </div>
</c:if>

<a href="doctors?action=new">Add Doctor</a>

<c:if test="${not empty doctors}">
    <table>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Specialty</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        <jsp:useBean id="doctors" scope="request" type="java.util.List"/>
        <c:forEach var="d" items="${doctors}">
            <tr>
                <td>${d.id}</td>
                <td>${d.firstName}</td>
                <td>${d.lastName}</td>
                <td>${d.email}</td>
                <td>${d.specialty}</td>
                <td>${d.department.name}</td>
                <td>
                    <a href="doctors?action=edit&id=${d.id}">Edit</a> |
                    <a href="doctors?action=delete&id=${d.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
