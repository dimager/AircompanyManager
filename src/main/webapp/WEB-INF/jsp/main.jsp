<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Main</title>
<c:import url="meta.jsp"/>
</head>
<body>
<c:import url="header.jsp"/>


<c:forEach var = "i" begin = "1" end = "5">
Item asdasdfasdf <c:out value = "${i}"/><p>
    </c:forEach>
        <c:out value="${sessionScope.userDTO.toString()}"/>
        <c:out value="${sessionScope.loginState}"/>
        <c:out value="${pageContext.request.contextPath}"/>
<c:out value="${sessionScope.loginState}"/>

<c:import url="footer.jsp"/>
</body>
</html>
