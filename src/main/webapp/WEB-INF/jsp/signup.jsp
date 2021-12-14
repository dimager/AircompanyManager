<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.signup" var="pagename"/>
<fmt:message bundle="${lang}" key="header.signupto" var="signupto"/>
<fmt:message bundle="${lang}" key="labelloginpage.username" var="labelUsername"/>
<fmt:message bundle="${lang}" key="placeholder.username" var="phUsername"/>
<fmt:message bundle="${lang}" key="labelloginpage.firstname" var="labelFirstName"/>
<fmt:message bundle="${lang}" key="labelloginpage.lastname" var="labelLastName"/>
<fmt:message bundle="${lang}" key="labelloginpage.email" var="labelEmail"/>
<fmt:message bundle="${lang}" key="labelloginpage.password" var="labelPassword"/>
<fmt:message bundle="${lang}" key="promt.password" var="passwordHint"/>
<fmt:message bundle="${lang}" key="promt.user" var="userHint"/>
<fmt:message bundle="${lang}" key="labelloginpage.repeatpassword" var="labelPasswordRepeat"/>
<fmt:message bundle="${lang}" key="placeholder.passwordrepeat" var="phPasswordRepeat"/>
<fmt:message  bundle="${lang}" key="placeholder.lastname" var="phLastName"/>
<fmt:message bundle="${lang}" key="placeholder.password" var="phPassword"/>
<fmt:message bundle="${lang}" key="placeholder.firstname" var="phFirstname"/>
<fmt:message bundle="${lang}" key="placeholder.email" var="phEmail"/>
<html>
<head>
    <title>
        ${pagename}
    </title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>

    <form class="login-one-userform" method="post" action="/controller?command=SIGNUP">
        <div class="img-loggin">
            <img src="${pageContext.request.contextPath}/assets/img/singup.jpg" width="500" height="200">
        </div>
        <div class="col">
            <div class="form-group">
                <div class="login-one-ico"><i class="fa fa-sign-in" id="lockico"></i></div>
                <div><h3 id="heading">${signupto} <fmt:message bundle="${lang}" key="label.companyname"/></h3></div>
                <div>
                    <input type="hidden" name="command" value="SIGNUP">
                    <label class="inputlabel">${labelUsername}</label>
                    <input class="form-control" minlength="1" maxlength="50" type="text" name="username" pattern="[A-Za-z0-9]+" placeholder="${phUsername}" value="${requestScope.userDTO.username}">
                    <p class="signuptip">${userHint}</p>
                    <label class="inputlabel">${labelFirstName}</label>
                    <input class="form-control" minlength="1" maxlength="50" type="text" name="firstname" pattern="[A-Za-z0-9]+" placeholder="${phFirstname}" value="${requestScope.userDTO.firstName}">
                    <p class="signuptip">${userHint}</p>
                    <label class="inputlabel">${labelLastName}</label>
                    <input class="form-control" minlength="1" maxlength="50" type="text" name="lastname" pattern="[A-Za-z0-9]+" placeholder="${phLastName}"  value="${requestScope.userDTO.lastName}">
                    <p class="signuptip">${userHint}</p>
                    <label class="inputlabel">${labelEmail}</label>
                    <input class="form-control" minlength="1" maxlength="320" type="email" name="email" pattern="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" placeholder="${phEmail}" value="${requestScope.userDTO.email}">
                    <label class="inputlabel">${labelPassword}</label>
                    <input class="form-control" minlength="8" maxlength="50" type="password" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" placeholder="${phPassword}">
                    <p class="signuptip">${passwordHint}</p>
                    <label class="inputlabel">${labelPasswordRepeat}</label>
                    <input class="form-control" minlength="8" maxlength="50" type="password" name="password-repeat" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"
                           placeholder="${phPasswordRepeat}">
                </div>
                <div style="margin-top: 10px">
                    <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        <fmt:message bundle="${lang}" key="buttonname.signup"/>
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>