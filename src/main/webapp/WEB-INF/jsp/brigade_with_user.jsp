<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="page.title.brigadepage" var="pagename"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.firstname" var="colFirstName"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.lastname" var="colLastName"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.role" var="colRole"/>
<fmt:message bundle="${lang}"  key="tablename.addusertobrigade" var="tableNameAddUser"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../assets/css/styles.css"/>
    <title>${pagename}</title>
    <c:import url="meta.jsp"/>
</head>
<body>
<c:import url="header.jsp"/>
<div id="login-one" class="login-one" style="padding-top: 20px; padding-bottom: 40px; height: auto">
    <section class="mt-4">
        <c:import url="exception.jsp"/>
        <c:import url="command_result_state.jsp"/>
        <c:import url="errors.jsp"/>
        <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0">${requestScope.brigadeUserDTO.brigadeName}</p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th>${colFirstName}</th>
                                    <th>${colLastName}</th>
                                    <th>${colRole}</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.brigadeUserDTO.userDTOs}" var="user">
                                    <tr>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td><fmt:message bundle="${lang}" key="rolename.${user.role.roleId}"/></td>
                                        <td class="icons">
                                            <c:url value="/controller" var="deleteUserFromBrigadeURL">
                                                <c:param name="command" value="DELETE_USER_FROM_BRIGADE"/>
                                                <c:param name="brigade_id"
                                                         value="${requestScope.brigadeUserDTO.brigadeId}"/>
                                                <c:param name="user_id" value="${user.userId}"/>
                                            </c:url>

                                            <a href="${deleteUserFromBrigadeURL}">
                                                <i class="icon ion-android-delete" style="margin-left: 10px;"></i>
                                            </a>

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
    <section class="mt-4">
        <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0"> ${tableNameAddUser} ${requestScope.brigadeDTO.brigadeName}</p>
                    </div>
                    <div class="card-body">

                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th>${colFirstName}</th>
                                    <th>${colLastName}</th>
                                    <th>${colRole}</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.userDTOList}" var="user">
                                    <tr>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td><fmt:message bundle="${lang}" key="rolename.${user.role.roleId}"/></td>
                                        <td class="icons" >
                                            <c:url value="/controller" var="addUserToBrigade">
                                                <c:param name="command" value="ADD_USER_TO_BRIGADE_PAGE"/>
                                                <c:param name="brigade_id" value="${requestScope.brigadeDTO.brigadeId}"/>
                                                <c:param name="user_id" value="${user.userId}"/>
                                            </c:url>

                                            <a href="${addUserToBrigade}">
                                                <i class="icon ion-person-add" style=" margin-left: 10px;"></i>
                                            </a>

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
</div>

<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>