<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <script type="text/javascript"></script>
    <script>
        function change(id, status) {
            window.location.replace("http://localhost:8082/driver?id=" + id + "&status=" + status)
        }
    </script>
    <title>Autoshop</title>
<body style="align-content: center;">
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="<%=request.getContextPath()+"/ads"%>">Обьявления</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()+"/driver"%>">Мои обьявления <span
                            class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()+"/create"%>">Создать<span
                            class="sr-only">(current)</span></a>
                </li>
            </ul>
            <a href="<%=request.getContextPath()%>/logout" class="btn btn-outline-success my-2 my-sm-0" type="submit">
                <c:out value="${sessionScope.driver.login}"/></a>
        </div>
    </nav>
</div>
<h2>Мои обьявления</h2>
<table class="table table-sm table-dark">
    <thead>
    <tr>
        <th scope="col">Image</th>
        <th scope="col">Car</th>
        <th scope="col">Model</th>
        <th scope="col">Brand</th>
        <th scope="col">Price</th>
        <th scope="col" style="width: 20%"><input type="button" value="Status" class="btn btn-dark"
                                                  onclick="aOrd()"/></th>
    </tr>
    </thead>
    <tbody id="bodyt">
    <c:forEach items="${sessionScope.driver.car}" var="car">
        <tr>
            <td width="13%"><img src="<c:url value='/download?id=${car.id}'/>" height="100px" width="100px">
                <a href="<%=request.getContextPath()%>/upimg?idCar=<c:out value="${car.id}"/>" style="font-size: 0.8rem">Загрузить фото</a></td>
            <td><c:out value="${car.name}"/></td>
            <td><c:out value="${car.model.model}"/></td>
            <td><c:out value="${car.model.brand.brand}"/></td>
            <td><c:out value="${car.price}"/></td>
            <td>
                <c:choose>
                    <c:when test="${car.status == 1}">
                        <button type="button" class="btn btn-outline-success"
                                onclick="change(<c:out value="${car.id}"/>,<c:out value="${car.status}"/>)">
                            Продается
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-outline-danger"
                                onclick="change(<c:out value="${car.id}"/>,<c:out value="${car.status}"/>)">
                            Продана
                        </button>
                    </c:otherwise>
                </c:choose>
            </td>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
