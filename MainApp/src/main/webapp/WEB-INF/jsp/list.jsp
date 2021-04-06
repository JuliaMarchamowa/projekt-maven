<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<body>
<table>
    <tr>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>E-mail</th>
        <th>Numer telefonu</th>
    </tr>
    <c:forEach var="coach" items="${coaches}">
        <tr>
            <td>${coach.firstName}</td>
            <td>${coach.lastName}</td>
            <td>${coach.email}</td>
            <td>${coach.phoneNumber}</td>
            <td>
                <form method="POST" action="coaches/${coach.id}/delete">
                    <input type="submit" value="Usuń">
                </form>
            </td>
            <td><a href="/coaches/${coach.id}">Edytuj</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/coaches/new">Dodaj</a>
</body>
</html>
