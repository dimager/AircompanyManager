<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.main" var="pagename"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>${pagename}</title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <div class="container">
        <div class="row mainpage">
            <div class="col-sm helloimage">
                <img src="${pageContext.request.contextPath}/assets/img/img.png" width="500" height="625">
            </div>
            <div class="col-sm">
                <h3>Welcome to <fmt:message bundle="${lang}" key="label.companyname"/></h3>
                <span>
                <h5>Service for airline personnel management</h5>
            </span>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
    <c:import url="scripts.jsp"/>
</div>
</body>
</html>
