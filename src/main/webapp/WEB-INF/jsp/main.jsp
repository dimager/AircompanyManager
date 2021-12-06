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

<div class="container">
    <div class="row">
        <div class="col-sm">
            <img src="${pageContext.request.contextPath}/assets/img/img.png">
        </div>
        <div class="col-sm">
          <h3>  Welcome to <fmt:message bundle="${lang}" key="label.companyname"/></h3>
            <span>
                <h5>Service for airline personnel management</h5>
            </span>
        </div>

    </div>
</div>

<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
