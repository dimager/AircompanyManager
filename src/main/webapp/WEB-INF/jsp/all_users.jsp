<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <title><fmt:message bundle="${lang}" key="pagename.allusers"/></title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <section class="mt-4">
        <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0"><fmt:message bundle="${lang}" key="tablename.allusers"/></p>
                    </div>
                    <div class="card-body">

                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.firstname"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.lastname"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.email"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.role"/></th>
                                    <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                                        <th></th>
                                    </c:if>
                                    <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                                        <th></th>
                                    </c:if>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.userDTOList}" var="user">
                                    <tr>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td><a href="mailto:${user.email}">${user.email}</a></td>
                                        <td><fmt:message bundle="${lang}" key="rolename.${user.role.roleId}"/></td>
                                        <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                                            <td class="icons" >
                                                <div class="dropdown">
                                                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        change role
                                                    </button>
                                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                        <c:forEach var="role" items="${roles}">
                                                            <c:if test="${user.role.roleId != role.roleId}">
                                                                <c:url value="/controller" var="changeRole">
                                                                    <c:param name="command" value="CHANGE_ROLE"/>
                                                                    <c:param name="new_role" value="${role.roleId}"/>
                                                                    <c:param name="user_id" value="${user.userId}"/>
                                                                </c:url>
                                                                <a class="dropdown-item" href="${changeRole}"><fmt:message bundle="${lang}" key="rolename.${role.roleId}"/></a>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <c:url value="/controller" var="editRole">
                                                    <c:param name="command" value="EDIT_USER_ROLE"/>
                                                    <c:param name="user_id" value="${user.userId}"/>
                                                </c:url>
                                            </td>
                                        </c:if>
                                        <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                                            <td class="icons">
                                                <c:url value="/controller" var="showUserFlights">
                                                    <c:param name="command" value="SHOW_USER_FLIGHTS_PAGE_FOR_MANAGER"/>
                                                    <c:param name="show_user_id" value="${user.userId}"/>
                                                </c:url>
                                                <c:url value="/controller" var="showUserBrigades">
                                                    <c:param name="command" value="SHOW_USER_BRIGADES_PAGE_FOR_MANAGER"/>
                                                    <c:param name="show_user_id" value="${user.userId}"/>
                                                </c:url>
                                                <a href="${showUserFlights}">
                                                    <i class="icon ion-paper-airplane" style=" margin-left: 10px;"></i>
                                                </a>
                                                <a href="${showUserBrigades}">
                                                    <i class="icon ion-ios-people" style=" margin-left: 10px;"></i>
                                                </a>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:import url="footer.jsp"/>
    <c:import url="scripts.jsp"/>
</body>
</html>
