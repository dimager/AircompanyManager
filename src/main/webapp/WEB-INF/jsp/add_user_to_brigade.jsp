<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mager
  Date: 22.11.2021
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="meta.jsp"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="pagename.addusertobrigade"/> </title>
</head>
<body>

<c:import url="header.jsp"/>

<div id="login-one" class="login-one" style="padding-top: 20px;">
    <section class="mt-4">
        <c:import url="exception.jsp"/>
            <c:import url="command_result_state.jsp"/>

            <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0"><fmt:message key="tablename.addusertobrigade"/> ${requestScope.brigadeDTO.brigadeName}</p>
                    </div>
                    <div class="card-body">

                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th><fmt:message key="tablecolumnlabel.firstname"/> </th>
                                    <th><fmt:message key="tablecolumnlabel.lastname"/></th>
                                    <th><fmt:message key="tablecolumnlabel.role"/></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.userDTOList}" var="user">
                                    <tr>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td><fmt:message key="rolename.${user.role.roleId}"/></td>
                                        <td>
                                            <c:url value="/controller" var="addUserToBrigade">
                                                <c:param name="command" value="ADD_USER_TO_BRIGADE_PAGE"/>
                                                <c:param name="brigade_id" value="${requestScope.brigadeDTO.brigadeId}"/>
                                                <c:param name="user_id" value="${user.userId}"/>
                                            </c:url>

                                            <a href="${addUserToBrigade}">
                                                <i class="icon ion-person-add" style=" margin-left: 10px;"></i>
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
