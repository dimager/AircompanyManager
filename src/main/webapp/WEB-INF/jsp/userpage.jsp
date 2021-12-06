<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <title>
        <fmt:message bundle="${lang}" key="pagename.userpage"/>
    </title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <c:if test="${sessionScope.loginState}">
        <div class="login-one-userform">

            <div class="col">
                <div class="form-group">
                    <c:choose>
                        <c:when test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                            <img src="${pageContext.request.contextPath}/assets/img/admin.png" height="150px" width="150px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                            <img src="${pageContext.request.contextPath}/assets/img/manager.png" height="150px"
                                 width="150px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_PILOT}">
                            <img src="${pageContext.request.contextPath}/assets/img/pilot.png" height="150px" width="150px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_RADIO_ENGINEER}">
                            <img src="${pageContext.request.contextPath}/assets/img/engineer.png" height="150px"
                                 width="150px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_FLIGHT_ATTENDANT}">
                            <img src="${pageContext.request.contextPath}/assets/img/hostess.png" height="150px"
                                 width="150px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_NAVIGATOR}">
                            <img src="${pageContext.request.contextPath}/assets/img/radar.png" height="150px"
                                 width="150px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_GUEST}">
                            <img src="${pageContext.request.contextPath}/assets/img/guest.png" height="150px"
                                 width="150px">
                        </c:when>
                    </c:choose>
                    <div>
                        <label style="font-size: 40px"> ${sessionScope.loggedinUser.firstName} ${sessionScope.loggedinUser.lastName}</label>
                    </div>
                    <div>
                        <label style="font-size: 25px">
                            <fmt:message bundle="${lang}" key="rolename.${sessionScope.loggedinUser.role.roleId}"/>
                        </label>
                    </div>
                    <div>
                        <label class="inputlabel">
                            <fmt:message bundle="${lang}" key="labeluserpage.username"/> ${sessionScope.loggedinUser.username}
                        </label>
                    </div>
                    <div>
                        <label class="inputlabel">
                            <fmt:message bundle="${lang}" key="labeluserpage.email"/> ${sessionScope.loggedinUser.email}
                        </label>
                    </div>
                    <div style="margin-top: 10px">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                            <fmt:message bundle="${lang}" key="buttonname.changepassword"/>
                        </button>
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">
                                            <fmt:message bundle="${lang}" key="buttonname.changepassword"/>
                                        </h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form action="/controller" method="post">
                                        <div class="modal-body">
                                            <input type="hidden" name="command" value="CHANGE_PASSWORD">
                                            <div class="form-group">
                                                <input class="form-control" minlength="1" maxlength="50"
                                                                           type="password" name="oldPassword"
                                                                           placeholder="<fmt:message bundle="${lang}" key="label.oldpassword"/>">
                                            </div>
                                            <div class="form-group">
                                                <input class="form-control" minlength="1" maxlength="50" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"
                                                                           type="password" name="newPassword"
                                                                           placeholder="<fmt:message bundle="${lang}" key="label.newpassword"/>">
                                                <p class="signuptip"><fmt:message bundle="${lang}" key="promt.password"/></p>
                                            </div>
                                            <div class="form-group"><input class="form-control" minlength="1" maxlength="50" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"
                                                                           type="password" name="newPasswordRepeat"
                                                                           placeholder="<fmt:message bundle="${lang}" key="label.repnewpassword"/>">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                <fmt:message bundle="${lang}" key="buttonname.cancel"/></button>
                                            <button type="submit" class="btn btn-primary"><fmt:message bundle="${lang}" key="buttonname.savepassword"/></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
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