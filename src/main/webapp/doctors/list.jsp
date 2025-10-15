<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Doctors</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body>
<h2>Doctors</h2>
<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>

<a href="doctors?action=new">Add Doctor</a>
<a href="${pageContext.request.contextPath}/index.jsp">Home</a>
<table>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Speciality</th>
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
</body>
</html>
