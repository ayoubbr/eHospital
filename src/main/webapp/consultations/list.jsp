<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Consultations</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body>
<h2>Doctors</h2>
<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>

<a href="consultations?action=new">Add Consultation</a>
<a href="${pageContext.request.contextPath}/index.jsp">Home</a>
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
            <td> ${empty c.room ? '-' : c.room.name}</td>
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
