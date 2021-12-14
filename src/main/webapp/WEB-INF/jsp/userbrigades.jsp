<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.userbrigades" var="userpagename"/>
<fmt:message bundle="${lang}" key="pagename.mybrigades" var="pagename"/>
<fmt:message bundle="${lang}" key="header.mybrigades" var="tablename"/>
<fmt:message bundle="${lang}" key="label.brigadeUsersList" var="labelBrigadeUsersList"/>
<fmt:message bundle="${lang}" key="label.userDoesntHaveBrigades" var="dontHaveBrigades"/>
<fmt:message bundle="${lang}" key="label.doesntHaveBrigades" var="dontHaveBrigades"/>
<fmt:message bundle="${lang}" key="label.userDoesntHaveBrigades" var="userDontHaveBrigades"/>
<html>
<head>
    <c:choose>
        <c:when test="${not empty param.show_user_id}">
            <title> ${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} ${userpagename}</title>
        </c:when>
        <c:otherwise>
            <title>${pagename}</title>
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
        <div class="row-cols-1">
            <div class="col">
                <div class="login-one-userform">
                    <div class="form-group">
                        <div class="tab-content">
                        <c:choose>
                            <c:when test="${not empty param.show_user_id}">
                                <h3>${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} ${userpagename}</h3>
                                <c:if test="${empty requestScope.userBrigades}">
                                    <h5>${userDontHaveBrigades}</h5>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <h3>${tablename}</h3>
                                <c:if test="${empty requestScope.userBrigades}">
                                    <h5>${dontHaveBrigades}</h5>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <div class="list-group" id="myList" role="tablist">
                            <c:forEach items="${requestScope.userBrigades}" var="brigade">
                                <a class="list-group-item list-group-item-action" data-toggle="list" href="#brigade${brigade.brigadeId}" role="tab">${brigade.brigadeName}</a>
                            </c:forEach>
                        </div>
                        </div>
                    </div>
                </div>        </div>

        </div>
            <c:if test="${not empty requestScope.userBrigades}">
                <div class="row-cols-1" >
                <div class="login-one-userform" <c:if test="${empty requestScope.userBrigades}">hidden</c:if>>
                        <div class="form-group">
                            <div class="tab-content">
                                <label><h3>${labelBrigadeUsersList}</h3></label>
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
            </c:if>
    </div>
<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
