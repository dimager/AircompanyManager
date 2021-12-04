<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="myTag" uri="/WEB-INF/tld/customTag.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<nav class="navbar navbar-light navbar-expand-md navigation-clean-button" style="margin-bottom: 30px">
    <div class="container">
        <a class="navbar-brand" href="#">
            <fmt:message bundle="${lang}" key="label.companyname"/>
        </a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link"
                            <c:if test="${fn:endsWith(pageContext.request.queryString,'controller' )}"> style="color:#007bff" </c:if>
                       href="/">
                        <fmt:message bundle="${lang}" key="headermenu.main"/>
                    </a>
                </li>
                <c:if test="${sessionScope.loggedinUser.role <= Role_MANAGER}">
                    <li class="nav-item">
                        <a class="nav-link"
                                <c:if test="${fn:contains(pageContext.request.queryString,'SHOW_FLIGHT_PAGE')}"> style="color:#007bff" </c:if>
                           href="/controller?command=SHOW_FLIGHT_PAGE">
                            <fmt:message bundle="${lang}" key="headermenu.flights"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                    <li class="nav-item">
                        <a class="nav-link"
                                <c:if test="${fn:contains(pageContext.request.queryString,'SHOW_AIRCRAFT_PAGE')}"> style="color:#007bff" </c:if>
                           href="/controller?command=SHOW_AIRCRAFT_PAGE">
                            <fmt:message bundle="${lang}" key="headermenu.aircrafts"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                    <li class="nav-item">
                        <a class="nav-link"
                                <c:if test="${fn:contains(pageContext.request.queryString,'SHOW_BRIGADE_PAGE')}"> style="color:#007bff" </c:if>
                           href="/controller?command=SHOW_BRIGADE_PAGE">
                            <fmt:message bundle="${lang}" key="headermenu.brigades"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.loggedinUser.role <= Role_MANAGER}">
                    <li class="nav-item">
                        <a class="nav-link"
                                <c:if
                                        test="${fn:contains(pageContext.request.queryString,'SHOW_ALL_USERS')}">
                                    style="color:#007bff"
                                </c:if>
                           href="/controller?command=SHOW_ALL_USERS">
                            <fmt:message bundle="${lang}" key="headermenu.allusers"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                    <li class="nav-item">
                        <a class="nav-link"
                                <c:if
                                        test="${fn:contains(pageContext.request.queryString,'SHOW_AIRPORT_PAGE')}">
                                    style="color:#007bff"
                                </c:if>
                           href="/controller?command=SHOW_AIRPORT_PAGE">
                            <fmt:message bundle="${lang}" key="headermenu.allAirports"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.loggedinUser.role > Role_MANAGER and sessionScope.loggedinUser.role < Role_GUEST}">
                    <li class="nav-item">
                        <a class="nav-link"
                                <c:if
                                        test="${fn:contains(pageContext.request.queryString,'SHOW_USER_FLIGHTS_PAGE')}">
                                    style="color:#007bff"
                                </c:if>
                           href="/controller?command=SHOW_USER_FLIGHTS_PAGE">
                            <fmt:message bundle="${lang}" key="headermenu.myflights"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.loggedinUser.role > Role_MANAGER and sessionScope.loggedinUser.role < Role_GUEST}">
                    <li class="nav-item">
                        <a class="nav-link"
                                <c:if
                                        test="${fn:contains(pageContext.request.queryString,'SHOW_USER_BRIGADES_PAGE')}">
                                    style="color:#007bff"
                                </c:if>
                           href="/controller?command=SHOW_USER_BRIGADES_PAGE">
                            <fmt:message bundle="${lang}" key="headermenu.mybrigades"/>
                        </a>
                    </li>
                </c:if>
            </ul>
            <span class="navbar-text actions">
            <c:url value="/controller" var="login">
                <c:param name="command" value="SHOW_LOGIN_PAGE"/>
            </c:url>
            <c:url value="/controller" var="logout">
                <c:param name="command" value="LOGOUT"/>
            </c:url>
            <c:url value="/controller" var="signup">
                <c:param name="command" value="SHOW_SIGNUP_PAGE"/>
            </c:url>
            <c:url value="/controller" var="myacc">
                <c:param name="command" value="SHOW_USER_PAGE"/>
            </c:url>
            <c:url value="/controller" var="setEnLocale">
                <c:param name="command" value="SET_LOCALE"/>
                <c:param name="returnPage" value="${param.command}"/>
                <c:param name="sessionLocale" value="en"/>
            </c:url>
            <c:url value="/controller" var="setRuLocale">
                <c:param name="command" value="SET_LOCALE"/>
                <c:param name="returnPage" value="${param.command}"/>
                <c:param name="sessionLocale" value="ru"/>
            </c:url>
            <a class="login" href="${setEnLocale}">
            <img src="${pageContext.request.contextPath}/assets/img/en.png" height="32px" width="32px">
            </a>
            <a class="login" href="${setRuLocale}">
            <img src="${pageContext.request.contextPath}/assets/img/ru.png" height="32px" width="32px">
            </a>
            <c:choose>
                <c:when test="${sessionScope.loginState}">
                    <c:choose>
                        <c:when test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                            <img src="${pageContext.request.contextPath}/assets/img/admin_small.png" height="50px" width="50px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                        <img src="${pageContext.request.contextPath}/assets/img/manager_small.png" height="50px"
                             width="50px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_PILOT}">
                            <img src="${pageContext.request.contextPath}/assets/img/pilot_small.png" height="50px" width="50px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_RADIO_ENGINEER}">
                        <img src="${pageContext.request.contextPath}/assets/img/engineer_small.png" height="50px"
                             width="50px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_FLIGHT_ATTENDANT}">
                        <img src="${pageContext.request.contextPath}/assets/img/hostess_small.png" height="50px"
                             width="50px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_NAVIGATOR}">
                        <img src="${pageContext.request.contextPath}/assets/img/radar_small.png" height="50px"
                             width="50px">
                        </c:when>
                        <c:when test="${sessionScope.loggedinUser.role == Role_GUEST}">
                        <img src="${pageContext.request.contextPath}/assets/img/guest_small.png" height="50px"
                             width="50px">
                        </c:when>
                    </c:choose>
                    <a class="login" href="${myacc}">
                     <myTag:Hello userDTO="${sessionScope.loggedinUser}"/>
                  </a>
                    <a class="login" href="${logout}">
                     <fmt:message bundle="${lang}" key="headermenu.logout"/>
                  </a>
                </c:when>
                <c:otherwise>
                  <a class="login" href="${login}">
                     <fmt:message bundle="${lang}" key="headermenu.login"/>
                  </a>
                    <a class="login" href="${signup}">
                     <fmt:message bundle="${lang}" key="headermenu.signup"/>
                  </a>
                </c:otherwise>
            </c:choose>
         </span>
        </div>
    </div>
</nav>