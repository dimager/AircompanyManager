<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Main</title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <div class="login-one-userform">
                    <div class="form-group">
                        <ul class="list-group">
                            <c:forEach items="${requestScope.userBrigades}" var="brigade">
                                <li class="list-group-item">${brigade.brigadeName}</li>
                            </c:forEach>
                        </ul>
                    </div>
    </div>

</div>
<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
