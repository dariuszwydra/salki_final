<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/main.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="/">Rooms</a>
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="/show-all">Discover</a>
        </li>
    </ul>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <c:choose>
                <c:when test="${sessionScope.logged == true}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Logged as ${sessionScope.username}
                        </a>
                        <c:choose>
                            <c:when test="${sessionScope.usertype == 1}">
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="/user-settings">Account settings</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/logout-confirm">Logout</a>
                                </div>
                            </c:when>
                            <c:when test="${sessionScope.usertype == 2}">
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="/user-settings">Account settings</a>
                                    <a class="dropdown-item" href="/add-object">Add new object</a>
                                    <a class="dropdown-item" href="/show-your-objects">Show your objects</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/logout-confirm">Logout</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="/user-settings">Account settings</a>
                                    <a class="dropdown-item" href="/admin-users">Manage users</a>
                                    <a class="dropdown-item" href="/admin-objects">Manage objects</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/logout-confirm">Logout</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/register">Register</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>