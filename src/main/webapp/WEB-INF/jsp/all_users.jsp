<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.firstname" var="firstname"/>
<fmt:message bundle="${lang}" key="tablename.allusers" var="allUsers"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.lastname" var="lastname"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.email" var="email"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.role" var="rolecol"/>
<fmt:message bundle="${lang}" key="buttonname.changerole" var="changerole"/>
<fmt:message bundle="${lang}" key="buttonname.close" var="close"/>
<fmt:message bundle="${lang}" key="pagename.allusers" var="pagename"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.username" var="username"/>
<html>
<head>
    <title>${pagename}</title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <section class="mt-4">
        <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0">${allUsers}</p>
                    </div>
                    <div class="card-body">

                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th>${firstname}</th>
                                    <th>${lastname}</th>
                                    <th>${email}</th>
                                    <th>${rolecol}</th>
                                    <th>${username}</th>
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
                                        <td>${user.username}</td>
                                        <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                                            <td class="icons">
                                                <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#exampleModalLong${user.userId}">
                                                        ${changerole}
                                                </button>
                                            </td>
                                        </c:if>
                                        <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                                            <td style="text-align: center">
                                                <c:url value="/controller" var="showUserFlights">
                                                    <c:param name="command" value="SHOW_USER_FLIGHTS_PAGE_FOR_MANAGER"/>
                                                    <c:param name="show_user_id" value="${user.userId}"/>
                                                </c:url>
                                                <c:url value="/controller" var="showUserFlightsHistory">
                                                    <c:param name="command" value="SHOW_USER_FLIGHTS_HISTORY_PAGE"/>
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

                                                <a href="${showUserFlightsHistory}">
                                                    <i class="icon ion-clipboard" style=" margin-left: 10px;"></i>
                                                </a>
                                            </td>
                                        </c:if>
                                    </tr>
                                    <div class="modal fade" id="exampleModalLong${user.userId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle${user.userId}" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLongTitle${user.userId}">
                                                            ${changerole}
                                                    </h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="list-group">

                                                        <c:forEach var="role" items="${roles}">
                                                            <c:if test="${user.role.roleId != role.roleId}">
                                                                <c:url value="/controller" var="changeRole">
                                                                    <c:param name="command" value="CHANGE_ROLE"/>
                                                                    <c:param name="new_role" value="${role.roleId}"/>
                                                                    <c:param name="user_id" value="${user.userId}"/>
                                                                </c:url>
                                                                <a href="${changeRole}" class="list-group-item list-group-item-action">
                                                                    <fmt:message bundle="${lang}" key="rolename.${role.roleId}"/>
                                                                </a>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
