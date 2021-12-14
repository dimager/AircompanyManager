<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<footer class="footer-basic">
    <div class="social">
        <a href="https://github.com/dimager/AircompanyManager" target="_blank">
        <i class="icon ion-social-github"></i></a>
        <a href="https://www.linkedin.com/in/dmitry-mager-071a4357/" target="_blank">
            <i class="icon ion-social-linkedin"></i>
        </a><a href="https://www.facebook.com/magerdima" target="_blank">
            <i class="ion-social-facebook"></i></a>
        </div>
    <p class="signuptip"><fmt:message bundle="${lang}" key="label.companyname"/> 2021</p>
</footer>
