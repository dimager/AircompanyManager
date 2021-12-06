<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <c:choose>
        <c:when test="${not empty param.show_user_id}">
            <title> ${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} <fmt:message bundle="${lang}" key="pagename.userbrigades"/></title>
        </c:when>
        <c:otherwise>
            <title><fmt:message bundle="${lang}" key="pagename.mybrigades"/></title>
        </c:otherwise>
    </c:choose>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <div class="login-one-userform">
                    <div class="form-group">
                        <c:choose>
                            <c:when test="${not empty param.show_user_id}">
                                <h3>${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} <fmt:message bundle="${lang}" key="pagename.userbrigades"/></h3>
                            </c:when>
                            <c:otherwise>
                                <h3><fmt:message bundle="${lang}" key="header.mybrigades"/></h3>
                            </c:otherwise>
                        </c:choose>
                        <div class="list-group" id="myList" role="tablist">
                            <c:forEach items="${requestScope.userBrigades}" var="brigade">
                                <a class="list-group-item list-group-item-action" data-toggle="list" href="#brigade${brigade.brigadeId}" role="tab">${brigade.brigadeName}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm">
                <div class="login-one-userform">
                    <div class="form-group">
                        <div class="tab-content">
                            <c:forEach items="${requestScope.userBrigades}" var="brigade">
                                <div class="tab-pane" id="brigade${brigade.brigadeId}" role="tabpanel">
                                    <c:forEach items="${brigade.userDTOs}" var="user">
                                        <div class="form-group2">
                                                ${user.firstName} ${user.lastName} (<fmt:message bundle="${lang}" key="rolename.${user.role.roleId}"/>)
                                        </div>
                                    </c:forEach>
                                    <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                                    <c:url value="/controller" var="showBrigadeWithUsers">
                                        <c:param name="command" value="SHOW_BRIGADE_WITH_USERS_PAGE"/>
                                        <c:param name="brigade_id" value="${brigade.brigadeId}"/>
                                    </c:url>
                                    <a href="${showBrigadeWithUsers}">
                                        <i class="icon ion-edit" style="margin-top:20px; font-size: 30px;"></i>
                                    </a>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
