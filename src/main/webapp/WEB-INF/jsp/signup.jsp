<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}" />
 <fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <title>
        <fmt:message bundle="${lang}" key="pagename.signup"/>
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
            <img src="${pageContext.request.contextPath}/assets/img/singup.jpg" width="500" height="200" >
        </div>
        <div class="col">
            <div class="form-group">
                <div class="login-one-ico"><i class="fa fa-sign-in" id="lockico"></i></div>
                <div><h3 id="heading"> <fmt:message bundle="${lang}" key="header.signupto"/> <fmt:message bundle="${lang}" key="label.companyname"/></h3></div>
                <div>
                    <input type="hidden" name="command" value="SIGNUP">
                    <label class="inputlabel"><fmt:message bundle="${lang}" key="labelloginpage.username"/> </label>
                    <input class="form-control"  minlength="1" maxlength="50" type="text" name="username" pattern="[A-Za-z0-9]+" placeholder="<fmt:message bundle="${lang}" key="placeholder.username"/>">
                    <label class="inputlabel"><fmt:message bundle="${lang}" key="labelloginpage.firstname"/> </label>
                    <input class="form-control" minlength="1" maxlength="50" type="text" name="firstname" placeholder="<fmt:message bundle="${lang}" key="placeholder.firstname"/>">
                    <label class="inputlabel"><fmt:message bundle="${lang}" key="labelloginpage.lastname"/> </label>
                    <input class="form-control" minlength="1" maxlength="50" type="text" name="lastname" placeholder="<fmt:message  bundle="${lang}" key="placeholder.lastname"/>">
                    <label class="inputlabel"><fmt:message bundle="${lang}" key="labelloginpage.email"/> </label>
                    <input class="form-control" minlength="1" maxlength="320" type="email" name="email" pattern="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$" placeholder="<fmt:message bundle="${lang}" key="placeholder.email"/>">
                    <label class="inputlabel"><fmt:message bundle="${lang}" key="labelloginpage.password"/></label>
                    <input class="form-control" minlength="8" maxlength="50" type="password" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" placeholder="<fmt:message bundle="${lang}" key="placeholder.password"/>">
                    <p class="signuptip"><fmt:message bundle="${lang}" key="promt.password"/></p>
                    <label class="inputlabel"><fmt:message bundle="${lang}" key="labelloginpage.repeatpassword"/></label>
                    <input class="form-control" minlength="8" maxlength="50" type="password" name="password-repeat" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" placeholder="<fmt:message bundle="${lang}" key="placeholder.passwordrepeat"/>">
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