<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<footer class="footer-basic">
    <div class="social"><a href="https://github.com/dimager/AircompanyManager">
        <i class="icon ion-social-github"></i></a><a href="">
        <i class="icon ion-social-snapchat"></i></a><a href="#">
        <i class="icon ion-social-twitter"></i></a><a href="#">
        <i class="icon ion-social-facebook"></i></a></div>
    <p class="signuptip"><fmt:message bundle="${lang}" key="label.companyname"/> 2021</p>
</footer>
