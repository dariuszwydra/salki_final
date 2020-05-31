<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">username</th>
        <th scope="col">password</th>
        <th scope="col">usertype</th>
        <th scope="col">action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.usertype}</td>
            <c:choose>
                <c:when test="${sessionScope.usertype == 3}">
                    <td>
                        <a href="/deleteUser/${user.userID}/"><button class="btn btn-danger">Delete user</button></a>
                        <a href="/editUser/${user.userID}/"><button class="btn btn-danger">Edit user</button></a>
                    </td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>