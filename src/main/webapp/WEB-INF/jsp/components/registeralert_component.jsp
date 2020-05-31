<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${success=='true'}">
        <div class="alert alert-success p-3" role="alert">
            Registered successfully! Now you can log-in and enjoy Rooms!
        </div>
    </c:when>
    <c:when test="${success=='false'}">
        <div class="alert alert-danger p-3" role="alert">
            Something went wrong. Try again later.
        </div>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>