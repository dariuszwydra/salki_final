<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">name</th>
        <th scope="col">sh_desc</th>
        <th scope="col">price</th>
        <th scope="col">action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="object" items="${objectList}">
        <tr>
            <td>${object.name}</td>
            <td>${object.sh_desc}</td>
            <td>${object.price_day}</td>
            <td><a href="/details/${object.objectID}/"><button class="btn btn-primary">Show object</button></a></td>
            <c:choose>
                <c:when test="${sessionScope.usertype == 3}">
                    <td>
                        <a href="/delete/${object.objectID}/"><button class="btn btn-danger">Delete object</button></a>
                        <a href="/edit/${object.objectID}/"><button class="btn btn-danger">Edit object</button></a>
                    </td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>