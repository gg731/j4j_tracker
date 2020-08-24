<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21.08.2020
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Autoshop</title>
</head>
<body>
<div class="container pt-3" style="width: 30%; text-align: center">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Авторизация
            </div>
            <% if (request.getAttribute("authError") != null) { %>
            <label style="color: darkred"><%=request.getAttribute("authError")%>
            </label>
            <% } %>
            <div clas="card-body">
                <form action="<%=request.getContextPath()%>/login" method="post">
                    <div class="form-group">
                        <label>Login</label>
                        <input type="text" class="form-control" required name="login"/>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" required name="password"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </form>
                <p/>
                <a href="<%=request.getContextPath()%>/reg" style="color:steelblue">Регистрация</a>
            </div>

        </div>
    </div>

</div>

</body>
</html>
