<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Doctor Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body>

<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>
<h2>Doctor Form</h2>
<form action="doctors" method="post">
    <input type="hidden" name="id" value="${doctor.id}"/>
    <label>First Name:</label><input type="text" name="firstName" value="${doctor.firstName}"/><br>
    <label>Last Name:</label><input type="text" name="lastName" value="${doctor.lastName}"/><br>
    <label>Email:</label><input type="email" name="email" value="${doctor.email}"/><br>
    <label>Password:</label><input type="password" name="password" value="${doctor.password}"/><br>
    <label>Specialty:</label><input type="text" name="specialty" value="${doctor.specialty}"/><br>
    <label for="department">Department:</label>
    <select id="department" name="department_id">
        <option disabled>Choose a department</option>
        <d:forEach var="dep" items="${departments}">
            <option value="${dep.id}">
                    ${dep.name}
            </option>
        </d:forEach>
    </select>
    <br>
    <button type="submit">Save</button>
</form>
<a href="doctors">Back</a>
</body>
</html>
