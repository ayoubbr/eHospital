<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Consultations</title>
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

<h2>Consultations</h2>

<!-- replaced inline error style with error-container class -->
<c:if test="${not empty errorMessage}">
    <div class="error-container">
            ${errorMessage}
    </div>
</c:if>

<a href="consultations?action=new">Add Consultation</a>

<c:if test="${not empty consultations}">
    <table>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Report</th>
            <th>Status</th>
            <th>Doctor</th>
            <th>Patient</th>
            <th>Room</th>
            <th>Actions</th>
        </tr>
        <jsp:useBean id="consultations" scope="request" type="java.util.List"/>
        <c:forEach var="c" items="${consultations}">
            <tr>
                <td>${c.id}</td>
                <td>${c.formattedDate}</td>
                <td>${c.report}</td>
                <td>${c.status}</td>
                <td>${c.doctor.firstName} ${c.doctor.lastName}</td>
                <td>${c.patient.firstName} ${c.patient.lastName}</td>
                <td>${empty c.room ? '-' : c.room.name}</td>
                <td>
                    <a href="consultations?action=edit&id=${c.id}">Edit</a> |
                    <a href="consultations?action=delete&id=${c.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
