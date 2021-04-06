<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<body>
<form method="POST" action=
<c:choose>
    <c:when test="${coach.id != null}">${coach.id}</c:when>
        <c:otherwise>new</c:otherwise>
</c:choose>>
    <label for="firstName">Imię:</label><br>
    <input type="text" id="firstName" name="firstName" value="${coach.firstName}"><br>
    <label for="lastName">Nazwisko:</label><br>
    <input type="text" id="lastName" name="lastName" value="${coach.lastName}"><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email" value="${coach.email}"><br>
    <label for="phoneNumber">Numer telefonu:</label><br>
    <input type="text" id="phoneNumber" name="phoneNumber" value="${coach.phoneNumber}"><br>
    <input type="submit" value="Zapisz"/>
</form>
</body>
</html>
