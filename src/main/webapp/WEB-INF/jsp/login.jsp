<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.loginpage" var="pagename"/>
<fmt:message bundle="${lang}" key="label.loginto" var="labellogin"/>
<fmt:message bundle="${lang}" key="label.companyname" var="companyName"/>
<fmt:message bundle="${lang}" key="labelloginpage.username" var="labelUsername"/>
<fmt:message bundle="${lang}" key="labelloginpage.password" var="labelPassword"/>
<fmt:message bundle="${lang}" key="buttonname.login" var="btnLogin"/>


<html>
<head>
    <c:if test="${sessionScope.loginState}">
        <meta http-equiv="refresh" content="3;url=/"/>
    </c:if>
    <title>${pagename}</title>
    <jsp:include page="/WEB-INF/jsp/meta.jsp"/>

</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="errors.jsp"/>
    <c:choose>
        <c:when test="${sessionScope.loginState}">
            <c:import url="command_result_state.jsp"/>
            <h3>Redirect in 3 seconds...</h3>
        </c:when>
        <c:otherwise>
            <form class="login-one-userform" method="post" action="/controller?command=LOGIN">
                <div class="img-loggin">
                    <img src="${pageContext.request.contextPath}/assets/img/login.jpg" width="500" height="200">
                </div>
                <div class="col">
                    <div class="form-group">
                        <div class="login-one-ico"><i class="fa fa-sign-in" id="lockico"></i></div>
                        <div><h3 id="heading"> ${labellogin} ${companyName}</h3></div>
                        <div>
                            <label class="inputlabel">${labelUsername}</label>
                            <input class="form-control" minlength="1" maxlength="50" name="username" pattern="[A-Za-z0-9]+" placeholder="<fmt:message bundle="${lang}" key="label.username"/>">
                            <label class="inputlabel"> ${labelPassword}</label>
                            <input class="form-control" minlength="8" maxlength="50" type="password" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" placeholder="${labelPassword}">
                        </div>
                        <div class="btn1">
                            <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                                    ${btnLogin}
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</div>

<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>