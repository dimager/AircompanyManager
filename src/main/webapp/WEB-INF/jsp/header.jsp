<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<nav class="navbar navbar-light navbar-expand-md navigation-clean-button">
    <div class="container"><a class="navbar-brand" href="#"><fmt:message key="label.companyname"/></a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item"><a class="nav-link" <c:if
                        test="${fn:endsWith(pageContext.request.queryString,'controller' )}">
                    style="color:#007bff"</c:if> href="/controller">
                    <fmt:message key="headermenu.main"/></a></li>
                <li class="nav-item">
                    <a class="nav-link"
                            <c:if test="${fn:contains(pageContext.request.queryString,' SHOW_FLIGHT')}">
                                style="color:#007bff"</c:if>
                       href="/controller?command=SHOW_FLIGHT"><fmt:message key="headermenu.flights"/>
                    </a></li>
                <li class="nav-item">
                    <a class="nav-link"
                            <c:if test="${fn:contains(pageContext.request.queryString,'SHOW_AIRCRAFT_PAGE')}">
                                style="color:#007bff"</c:if>
                       href="/controller?command=SHOW_AIRCRAFT_PAGE"><fmt:message key="headermenu.aircrafts"/>
                    </a></li>

                <li class="nav-item"><a class="nav-link" <c:if
                        test="${fn:contains(pageContext.request.queryString,'SHOW_BRIGADE_PAGE')}">
                    style="color:#007bff"
                </c:if> href="/controller?command=SHOW_BRIGADE_PAGE"><fmt:message key="headermenu.brigades"/>
                </a></li>
                <li class="nav-item"><a class="nav-link" <c:if
                        test="${fn:contains(pageContext.request.queryString,'SHOW_ALL_USERS')}">
                    style="color:#007bff"
                </c:if> href="/controller?command=SHOW_ALL_USERS"><fmt:message key="headermenu.allusers"/>
                </a></li>
            </ul>
            <span class="navbar-text actions"> <a class="login" href="#">Log In</a><a class="login" href="#">Sign up</a></span>
        </div>
    </div>
</nav>
