<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.myflights" var="pagename"/>
<fmt:message bundle="${lang}" key="headermenu.myflights" var="headerName"/>
<fmt:message bundle="${lang}" key="pagename.userflights" var="userpagename"/>
<fmt:message bundle="${lang}" key="header.userflights" var="userHeaderName"/>
<fmt:message bundle="${lang}" key="label.brigadeflight" var="labelFlightBrigade"/>
<fmt:message bundle="${lang}" key="label.doesntHaveFlights" var="doesntHaveFlights"/>
<fmt:message bundle="${lang}" key="label.userDoesntHaveFlights" var="userDoesntHaveFligts"/>
<html>
<head>
    <c:choose>
        <c:when test="${not empty param.show_user_id}">
            <title> ${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} ${userpagename} </title>
        </c:when>
        <c:otherwise>
            <title>${pagename}</title>
        </c:otherwise>
    </c:choose>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <div class="login-one-userform2">
                    <c:choose>
                        <c:when test="${not empty param.show_user_id}">
                            <h3>${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} ${userHeaderName}</h3>
                            <c:if test="${empty requestScope.flightDTOList}">
                                <h5>${userDoesntHaveFligts}</h5>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <h3>${headerName}</h3>
                            <c:if test="${empty requestScope.flightDTOList}">
                                <h5>${doesntHaveFlights}</h5>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
            <div class="row" >
                <c:forEach items="${requestScope.flightDTOList}" var="flight">
                    <div class="col-sm">
                        <div class="login-one-userform2">
                            <div class="form-group2">
                                <div>
                                    <fmt:message bundle="${lang}" key="label.callsignflight" var="labelCallsign"/>
                                    <fmt:message bundle="${lang}" key="label.from" var="labelFrom"/>
                                    <fmt:message bundle="${lang}" key="label.to" var="labelTo"/>
                                    <fmt:message bundle="${lang}" key="label.time" var="labelTime"/>
                                    <label class="flightlabel">${labelCallsign}</label>
                                        ${flight.flightCallsign}
                                </div>
                                <div>
                                    <label class="flightlabel">${labelFrom}</label>
                                        ${flight.departureAirport.name},
                                        ${flight.departureAirport.country},
                                        ${flight.departureAirport.city}
                                </div>
                                <div>
                                    <label class="flightlabel">${labelTo}</label>
                                        ${flight.destinationAirport.name},
                                        ${flight.destinationAirport.country},
                                        ${flight.destinationAirport.city}
                                </div>
                                <div>
                                    <label class="flightlabel">${labelTime}</label>
                                    <fmt:formatDate value="${flight.departureDateTime}" pattern="MM/dd/yyyy HH:mm"/>
                                </div>
                                <div>
                                    <label class="flightlabel">${labelFlightBrigade}</label>
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
