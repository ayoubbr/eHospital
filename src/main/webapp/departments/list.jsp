<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Departments</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body>
<h2>Departments</h2>
<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>

<a href="departments?action=new">Add Department</a>
<a href="${pageContext.request.contextPath}/index.jsp">Home</a>
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
</body>
</html>


