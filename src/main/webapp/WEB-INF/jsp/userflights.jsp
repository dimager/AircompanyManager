<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <c:choose>
        <c:when test="${not empty param.show_user_id}">
            <title> ${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} <fmt:message bundle="${lang}" key="pagename.userflights"/></title>
        </c:when>
        <c:otherwise>
            <title><fmt:message bundle="${lang}" key="pagename.myflights"/></title>
        </c:otherwise>
    </c:choose>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <div class="login-one-userform2">
                    <c:choose>
                        <c:when test="${not empty param.show_user_id}">
                            <h3>${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} <fmt:message bundle="${lang}" key="header.userflights"/></h3>
                        </c:when>
                        <c:otherwise>
                            <h3><fmt:message bundle="${lang}" key="headermenu.myflights"/></h3>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="row" style="text-align: left">
            <c:forEach items="${requestScope.flightDTOList}" var="flight">
                <div class="col-sm">
                    <div class="login-one-userform2">
                        <div class="form-group2">
                            <div>
                                <label class="flightlabel"><fmt:message bundle="${lang}" key="label.callsignflight"/></label>
                                    ${flight.flightCallsign}
                            </div>
                            <div>
                                <label class="flightlabel"><fmt:message bundle="${lang}" key="label.from"/></label>
                                    ${flight.departureAirport.name},
                                    ${flight.departureAirport.country},
                                    ${flight.departureAirport.city}
                            </div>
                            <div>
                                <label class="flightlabel"><fmt:message bundle="${lang}" key="label.to"/></label>
                                    ${flight.destinationAirport.name},
                                    ${flight.destinationAirport.country},
                                    ${flight.destinationAirport.city}
                            </div>
                            <div>
                                <label class="flightlabel"><fmt:message bundle="${lang}" key="label.time"/></label>
                                <fmt:formatDate value="${flight.departureDateTime}" pattern="MM/dd/yyyy HH:mm"/>
                            </div>
                            <div>
                                <label class="flightlabel"><fmt:message bundle="${lang}" key="label.brigadeflight"/></label>
                                    ${flight.brigadeDTO.brigadeName}
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
