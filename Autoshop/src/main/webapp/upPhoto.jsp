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
<h2>Загрузить фотографию в обьявления</h2>
<label>Машина: <c:out value="${car.name}"/></label>
<label>Модель: <c:out value="${car.model.model}"/></label>
<form action=
      <c:out value="/upimg?photoId=${car.id}"/> method="post" enctype="multipart/form-data">
    <label>Загрузите фотографию машины</label>
    <div class="checkbox">
        <input type="file" name="file" required>
    </div>
    <button type="submit" class="btn btn-success">Upload</button>
</form>

</body>
</html>