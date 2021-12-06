<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<footer class="footer-basic">
    <div class="social"><a href="#">
        <i class="icon ion-social-instagram"></i></a><a href="#">
        <i class="icon ion-social-snapchat"></i></a><a href="#">
        <i class="icon ion-social-twitter"></i></a><a href="#">
        <i class="icon ion-social-facebook"></i></a></div>
    <ul class="list-inline">
            <li class="list-inline-item">
                <a class="nav-link"
                        <c:if test="${fn:endsWith(pageContext.request.queryString,'controller' )}"> style="color:#007bff" </c:if>
                   href="/">
                    <fmt:message bundle="${lang}" key="headermenu.main"/>
                </a>
            </li>
            <c:if test="${sessionScope.loggedinUser.role <= Role_MANAGER}">
                <li class="list-inline-item">
                    <a class="nav-link"
                            <c:if test="${fn:contains(pageContext.request.queryString,'SHOW_FLIGHT_PAGE')}"> style="color:#007bff" </c:if>
                       href="/controller?command=SHOW_FLIGHT_PAGE">
                        <fmt:message bundle="${lang}" key="headermenu.flights"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                <li class="list-inline-item">
                    <a class="nav-link"
                            <c:if test="${fn:contains(pageContext.request.queryString,'SHOW_AIRCRAFT_PAGE')}"> style="color:#007bff" </c:if>
                       href="/controller?command=SHOW_AIRCRAFT_PAGE">
                        <fmt:message bundle="${lang}" key="headermenu.aircrafts"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                <li class="list-inline-item">
                    <a class="nav-link"
                            <c:if test="${fn:contains(pageContext.request.queryString,'SHOW_BRIGADE_PAGE')}"> style="color:#007bff" </c:if>
                       href="/controller?command=SHOW_BRIGADE_PAGE">
                        <fmt:message bundle="${lang}" key="headermenu.brigades"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.loggedinUser.role <= Role_MANAGER}">
                <li class="list-inline-item">
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
                <li class="list-inline-item">
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
                <li class="list-inline-item">
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
                <li class="list-inline-item">
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
    <p class="signuptip"><fmt:message bundle="${lang}" key="label.companyname"/> 2021</p>
</footer>
