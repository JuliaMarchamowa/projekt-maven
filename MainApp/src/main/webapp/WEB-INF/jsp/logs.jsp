<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<body>
<table>
    <tr>
        <th>IP</th>
        <th>Akcja</th>
        <th>Link do trenera</th>
        <th>Data</th>
    </tr>
    <c:forEach var="event" items="${events}">
        <tr>
            <td>${event.userIp}</td>
            <td>${event.actionType}</td>
            <td>${event.link}</td>
            <td>${event.timestamp}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>