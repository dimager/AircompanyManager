<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Hello World!111122233344445556667777</h2>
<c:set var="myName" value="Alex"/>
<h1>${myName}</h1>

<c:forEach var = "i" begin = "1" end = "15">
         Item <c:out value = "${i}"/><p>
</c:forEach>
</body>
</html>
