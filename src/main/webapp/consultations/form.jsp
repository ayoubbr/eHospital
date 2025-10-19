<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Consultation Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/minimal.css">
</head>
<body>

<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #842029; padding: 10px; border-radius: 5px;">
            ${errorMessage}
    </div>
</c:if>
<h2>Consultation Form</h2>
<form action="consultations" method="post">
    <input type="hidden" name="id" value="${consultation.id}"/>
    <label>Date:</label><input type="datetime-local" name="date" value="${consultation.date}"/><br>
    <label>Report:</label><input type="text" name="report" value="${consultation.report}"/><br>
    <label for="status">Status:</label>
    <select id="status" name="status">
        <option disabled>Choose a status</option>
        <d:forEach var="s" items="${statuses}">
            <option value="${s}">
                    ${s}
            </option>
        </d:forEach>
    </select>
    <br>
    <label for="doctors">Doctor:</label>
    <select id="doctors" name="doctor_id">
        <option disabled>Choose a doctor</option>
        <d:forEach var="d" items="${doctors}">
            <option value="${d.id}">
                    ${d.firstName} ${d.lastName}
            </option>
        </d:forEach>
    </select>
    <br>
    <label for="patient">Patient:</label>
    <select id="patient" name="patient_id">
        <option disabled>Choose a patient</option>
        <d:forEach var="p" items="${patients}">
            <option value="${p.id}">
                    ${p.firstName} ${p.lastName}
            </option>
        </d:forEach>
    </select>
    <br>
    <label for="room">Room:</label>
    <select id="room" name="room_id">
        <option disabled>Choose a room</option>
        <d:forEach var="r" items="${rooms}">
            <option value="${r.id}">
                    ${r.name} : capacity (${r.capacity})
            </option>
        </d:forEach>
    </select>
    <br>
    <button type="submit">Save</button>
</form>
<a href="consultations">Back</a>
</body>
</html>

