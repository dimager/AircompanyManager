<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="../assets/css/Login-screen.css">
    <link rel="stylesheet" href="../assets/css/styles.css">
</head>

<body>
<div id="login-one" class="login-one">
    <form class="login-one-form" method="post">
        <div class="col">
            <div class="login-one-ico"><i class="fa fa-unlock-alt" id="lockico"></i></div>
            <div class="form-group">
                <div>
                    <h3 id="heading">Log in:</h3>
                </div>
                <input class="form-control" type="text" id="input-1" placeholder="Username">
                <input class="form-control" type="password" id="input-2" placeholder="Password">
                <button class="btn btn-primary" id="button" style="background-color:#007ac9;" type="submit">Log in</button>
            </div>
        </div>
    </form>
</div>
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
