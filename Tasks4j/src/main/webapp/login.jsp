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
    <script>
        function validate() {
            var usr = $('#usr').val();
            var psw = $('#psw').val();

            if (psw == '' || usr == '') {
                alert("Заполните все поля!");
                return false;
            }
            return true;
        }

    </script>

    <title>Login</title>
</head>
<body style="background-color: #1A1E28">
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
                <form action="/login" method="post">
                    <div class="form-group">
                        <label>Логин</label>
                        <input type="text" class="form-control" name="username" id="usr"/>
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <input type="password" class="form-control" name="password" id="psw"/>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Войти</button>
                </form>
                <p/>
                <a href="<%=request.getContextPath()%>/reg" style="color:steelblue">Регистрация</a>
            </div>
        </div>
    </div>

</div>

</body>
</html>
