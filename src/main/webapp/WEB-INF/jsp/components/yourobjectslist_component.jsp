<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Your objects</h2>
<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">name</th>
        <th scope="col">sh_desc</th>
        <th scope="col">price</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="object" items="${objectList}">
        <tr>
            <td>${object.name}</td>
            <td>${object.sh_desc}</td>
            <td>${object.price_day}</td>
            <td>
                <a href="/delete/${object.objectID}/"><button class="btn btn-danger">Delete object</button></a>
                <a href="/edit/${object.objectID}/"><button class="btn btn-danger">Edit object</button></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>