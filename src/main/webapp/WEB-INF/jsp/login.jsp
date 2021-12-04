<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <c:if test="${sessionScope.loginState}">
        <meta http-equiv="refresh" content="3;url=/"/>
    </c:if>
    <title>
        <fmt:message bundle="${lang}" key="pagename.loginpage"/>
    </title>
    <jsp:include page="/WEB-INF/jsp/meta.jsp"/>

</head>

<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="errors.jsp"/>
    <c:import url="exception.jsp"/>
    <c:choose>
        <c:when test="${sessionScope.loginState}">
            <c:import url="command_result_state.jsp"/>
            <h3>Redirect in 3 seconds...</h3>
        </c:when>
        <c:otherwise>
            <form class="login-one-userform" method="post" action="/controller?command=LOGIN">
                <div class="col">
                    <div class="form-group">
                        <div class="login-one-ico"><i class="fa fa-sign-in" id="lockico"></i></div>
                        <div><h3 id="heading">  <fmt:message bundle="${lang}" key="label.loginto"/> <fmt:message bundle="${lang}" key="label.companyname"/></h3></div>
                        <div>
                            <label style="font-size: 20px">
                                <fmt:message bundle="${lang}" key="labelloginpage.username"/>
                            </label>
                            <input class="form-control" minlength="1" maxlength="50" name="username"  pattern="[A-Za-z0-9]+" placeholder="<fmt:message bundle="${lang}" key="label.username"/>">
                            <label style="font-size: 20px">
                                <fmt:message bundle="${lang}" key="labelloginpage.password"/>
                            </label>
                            <input class="form-control" minlength="8" maxlength="50" type="password" name="password" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
                                   placeholder="<fmt:message bundle="${lang}" key="label.password"/>">
                        </div>
                        <div style="    margin-top: 10px">
                            <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                                <fmt:message bundle="${lang}" key="buttonname.login"/>
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