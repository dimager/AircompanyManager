<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <title><fmt:message bundle="${lang}" key="page.title.brigadepage"/></title>
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
                        <p class="lead text-info m-0"><fmt:message bundle="${lang}" key="tablename.brigade"/></p>
                    </div>
                    <div class="card-body">
                        <div style="padding-top: 10px;padding-bottom: 10px;text-align: right;">
                            <form method="get" action="/controller">
                                <input type="hidden" name="command" value="SHOW_ADD_BRIGADE_PAGE">
                                <button class="btn btn-primary shadow" type="submit">Add brigade</button>
                            </form>
                        </div>
                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.brigadename"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.users"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.pilots"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.engineer"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.navigator"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.flightattendant"/></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.brigadesWithUser}" var="brigade">
                                    <tr>
                                        <td>${brigade.brigadeName}</td>
                                        <td>${brigade.userDTOs.size()}</td>
                                        <td>
                                            <c:forEach items="${brigade.userDTOs}" var="user">
                                                <c:if test="${user.role == Role_PILOT}">
                                                    +
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${brigade.userDTOs}" var="user">
                                                <c:if test="${user.role == Role_FLIGHT_ATTENDANT}">
                                                    +
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td >
                                            <c:forEach items="${brigade.userDTOs}" var="user">
                                                <c:if test="${user.role == Role_RADIO_ENGINEER}">

                                                    +
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td >
                                            <c:forEach items="${brigade.userDTOs}" var="user">
                                                <c:if test="${user.role == Role_NAVIGATOR}">
                                                    +
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:url value="/controller" var="editURL">
                                                <c:param name="command" value="SHOW_EDIT_BRIGADE_PAGE"/>
                                                <c:param name="edit_brigade_id" value="${brigade.brigadeId}"/>
                                            </c:url>
                                            <c:url value="/controller" var="deleteURL">
                                                <c:param name="command" value="DELETE_BRIGADE"/>
                                                <c:param name="delete_brigade_id" value="${brigade.brigadeId}"/>
                                            </c:url>
                                            <c:url value="/controller" var="showBrigadeURL">
                                                <c:param name="command" value="SHOW_BRIGADE_WITH_USERS_PAGE"/>
                                                <c:param name="brigade_id" value="${brigade.brigadeId}"/>
                                            </c:url>
                                            <a href="${editURL}">
                                                <i class="icon ion-edit" style=" margin-left: 10px;"></i>
                                            </a>
                                            <a href="${deleteURL}">
                                                <i class="icon ion-android-delete" style="margin-left: 10px;"></i>
                                            </a>
                                            <a href="${showBrigadeURL}">
                                                <i class="icon ion-ios-people" style="margin-left: 10px;"></i>
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