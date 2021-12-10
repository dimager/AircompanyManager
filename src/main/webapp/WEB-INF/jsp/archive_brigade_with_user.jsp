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
    <title>${requestScope.brigadeUserDTO.brigadeName} </title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one" >
    <c:import url="header.jsp"/>

    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <section class="mt-4">
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
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.brigadeUserDTO.userDTOs}" var="user">
                                    <tr>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td><fmt:message bundle="${lang}" key="rolename.${user.role.roleId}"/></td>
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