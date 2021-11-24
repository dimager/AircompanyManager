<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>SignUp</title>
    <c:if test="${sessionScope.userIsRegistered}">
        <meta http-equiv="refresh" content="5;url=/controller?command=LOGIN"/>
    </c:if>
    <c:import url="meta.jsp"/>
</head>
<body>
<c:import url="header.jsp"/>
<section class="register-photo">
    <div class="form-container">
        <div class="image-holder"></div>
        <c:if test="${empty sessionScope.loginState or not sessionScope.loginState}">
            <c:if test="${requestScope.userIsRegistered}">
                <div class="form-group">Succesfully reg. rediract yo login page 5 sec</div>
            </c:if>
            <c:if test="${not requestScope.userIsRegistered}">
                <form method="post" action="/controller">
                    <input type="hidden" name="command" value="SIGNUP">
                    <h2 class="text-center"><fmt:message key="label.createAccount"/></h2>
                    <div class="form-group">
                        <input class="form-control" type="text" name="username"
                               <c:if test="${not empty requestScope.username}">value="${requestScope.username}"</c:if>
                               minlength="3" maxlength="50" placeholder=<fmt:message key="placeholder.username"/>>
                    </div>
                    <c:if test="${requestScope.usernameInUse}">
                        <div class="form-group"><fmt:message key="error.message.usernameIsNotFree"/></div>
                    </c:if>
                    <div class="form-group">
                        <input class="form-control" type="text" name="firstname"
                               <c:if test="${not empty requestScope.firstname}">value="${requestScope.firstname}"</c:if>
                               minlength="3" maxlength="50" placeholder=<fmt:message key="placeholder.firstname"/>>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="text" name="lastname"
                               <c:if test="${not empty requestScope.lastname}">value="${requestScope.lastname}"</c:if>
                               minlength="3" maxlength="50" placeholder=<fmt:message key="placeholder.lastname"/>>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="email" name="email"
                               <c:if test="${not empty requestScope.email}">value="${requestScope.email}"</c:if>
                               maxlength="320" placeholder="Email">
                    </div>
                    <c:if test="${requestScope.emailInUse}">
                        <div class="form-group">Email allready in use, change please</div>
                    </c:if>
                    <div class="form-group">
                        <input class="form-control" type="password" name="password" placeholder="Password">
                    </div>
                    <div class="form-group"><input class="form-control" type="password" name="password-repeat"
                                                   placeholder="Password (repeat)"></div>
                    <c:if test="${requestScope.passwordState}">
                        <div class="form-group">check password</div>
                    </c:if>
                    <div class="form-group">
                        <button class="btn btn-primary btn-block" type="submit">Sign Up</button>
                        <button type="button" class="btn btn-primary">Primary</button>
                    </div>
                    <a class="already" href="/controller?command=SHOW_LOGIN_PAGE">You already have an account? Login
                        here.</a>
                </form>
            </c:if>
        </c:if>
        <c:if test="${sessionScope.loginState}">
            <h1>allready login</h1>
        </c:if>
    </div>
</section>


<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.21/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.21/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.flash.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.print.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/DataTable---Fully-BSS-Editable.js"></script>
</body>
</html>
