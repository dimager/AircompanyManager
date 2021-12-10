<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.myflightshistory" var="pagename"/>
<fmt:message bundle="${lang}" key="headermenu.myflightsHistory" var="headerName"/>
<fmt:message bundle="${lang}" key="tablename.myhistoryflights" var="tableName"/>
<fmt:message bundle="${lang}" key="tablename.userhistoryflights" var="usertableName"/>
<fmt:message bundle="${lang}" key="pagename.userhistoryflights" var="userpagename"/>
<fmt:message bundle="${lang}" key="header.userflightshistory" var="userHeaderName"/>
<fmt:message bundle="${lang}" key="label.brigadeflight" var="labelFlightBrigade"/>
<fmt:message bundle="${lang}" key="label.doesntHaveHistoryFlights" var="doesntHaveHistoryFlights"/>
<fmt:message bundle="${lang}" key="label.userDoesntHaveHistoryFlights" var="userDoesntHaveHistoryFligts"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.brigade" var="colBrigade"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.aircraft" var="colAircraft"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.callsign" var="colCallsign"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.depairport" var="colDepAirport"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.destairport" var="colDestAirport"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.depaturedatetime" var="colDepDateTime"/>
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

    <c:choose>
        <c:when test="${empty requestScope.flightDTOList}">
            <c:choose>
                <c:when test="${not empty param.show_user_id}">
                    <h3>${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} - ${userHeaderName}</h3>
                        <h5>${userDoesntHaveHistoryFligts}</h5>
                </c:when>
                <c:otherwise>
                    <h3>${headerName}</h3>
                        <h5>${doesntHaveHistoryFlights}</h5>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <section class="mt-4">
                <div class="row">
                    <div class="col" style="padding-right: 45px;padding-left: 45px;">
                        <div class="card shadow">
                            <div class="card-header py-2">
                                <p class="lead text-info m-0">
                                    <c:choose>
                                        <c:when test="${not empty param.show_user_id}">
                                            ${requestScope.userDTO.firstName} ${requestScope.userDTO.lastName} - ${usertableName}
                                        </c:when>
                                        <c:otherwise>
                                            ${tableName}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive table mb-0 pt-3 pr-2">
                                    <table class="table table-striped table-sm my-0 mydatatable">
                                        <thead>
                                        <tr>
                                            <th>${colCallsign}</th>
                                            <th>${colAircraft}</th>
                                            <th>${colBrigade}</th>
                                            <th>${colDepAirport}</th>
                                            <th>${colDestAirport}</th>
                                            <th>${colDepDateTime}</th>
                                            <th></th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.flightDTOList}" var="flight">
                                            <tr>
                                                <td>${flight.flightCallsign}</td>
                                                <td>${flight.aircraftDTO.registrationCode} (${flight.aircraftDTO.model})</td>
                                                <td>${flight.brigadeDTO.brigadeName}</td>
                                                <td>${flight.departureAirport.IATACode}</td>
                                                <td>${flight.destinationAirport.IATACode}</td>
                                                <td><fmt:formatDate value="${flight.departureDateTime}" pattern="MM/dd/yyyy HH:mm" var="datetime"/>${datetime}</td>
                                                <td>
                                                    <c:url value="/controller" var="showBrigadeURL">
                                                        <c:param name="command" value="SHOW_BRIGADE_WITH_USERS_PAGE_ARCHIVE"/>
                                                        <c:param name="brigade_id" value="${flight.brigadeDTO.brigadeId}"/>
                                                    </c:url>
                                                    <a href="${showBrigadeURL}">
                                                        <i class="icon ion-ios-people" style="margin-left: 10px;"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
