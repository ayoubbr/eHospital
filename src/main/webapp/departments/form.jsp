<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Department Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>

<form action="departments" method="post">
    <input type="hidden" name="id" value="${department.id}"/>
    <label>Name:</label><input type="text" name="name" value="${department.name}"/><br>
    <button type="submit">Save</button>
</form>
<a href="departments">Back</a>
</body>
</html>

