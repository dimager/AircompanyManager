<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value=""/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <c:if test="${sessionScope.loginState}">
        <meta http-equiv="refresh" content="5;url=/controller?"/>
    </c:if>
    <title>1Untitled</title>
</head>

<body>
<h2>

</h2>
<section class="login-clean">
    <c:choose>
        <c:when test="${empty sessionScope.loginState or not sessionScope.loginState}">
            <form method="post" action="/controller">
                <input type="hidden" name="command" value="LOGIN">
                <h2 class="sr-only">Login Form</h2>
                <div class="illustration"><i class="icon ion-ios-navigate"></i></div>
                <div class="form-group"><input class="form-control" type="text" name="username" placeholder="username">
                </div>
                <div class="form-group"><input class="form-control" type="password" name="password"
                                               placeholder="Password"></div>
                <div class="form-group">
                    <button class="btn btn-primary btn-block" type="submit">Log In</button>
                </div>
                <a class="forgot" href="/controller?command=SHOW_SIGNUP_PAGE">Sign Up</a>
            </form>
        </c:when>
        <c:when test="${sessionScope.loginState}">
            <h1>allready logging, redirect 5 seconds</h1>
        </c:when>
    </c:choose>

</section>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.6.0/umd/popper.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.21/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.21/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.flash.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.print.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
