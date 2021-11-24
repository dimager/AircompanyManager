<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value=""/>
<html>
<head>
    <title><fmt:message key="page.title.brigadepage"/></title>
    <c:import url="meta.jsp"/>
</head>
<body>
<c:import url="header.jsp"/>
<div id="login-one" class="login-one" style="padding-top: 20px;">
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <section class="mt-4">
        <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0"><fmt:message key="tablename.brigade"/></p>
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
                                    <th><fmt:message key="tablecolumnlabel.brigadename"/></th>
                                    <th><fmt:message key="tablecolumnlabel.users"/></th>
                                    <th><fmt:message key="tablecolumnlabel.pilots"/></th>
                                    <th><fmt:message key="tablecolumnlabel.engineer"/></th>
                                    <th><fmt:message key="tablecolumnlabel.flightattendant"/></th>
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
                                                <c:if test="${user.role=='PILOT'}">
                                                    +
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${brigade.userDTOs}" var="user">
                                                <c:if test="${user.role=='ENGINEER'}">
                                                    +
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td style="">
                                            <c:forEach items="${brigade.userDTOs}" var="user">
                                                <c:if test="${user.role=='FLIGHT_ATTENDANT'}">
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
                                            <a href="${editURL}">
                                                <i class="icon ion-edit" style=" margin-left: 10px;"></i>
                                            </a>
                                            <a href="${deleteURL}">
                                                <i class="icon ion-android-delete" style="margin-left: 10px;"></i>
                                            </a>
                                            <a href="controller?command=SHOW_BRIGADE_WITH_USERS_PAGE&brigade_id=${brigade.brigadeId}">
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