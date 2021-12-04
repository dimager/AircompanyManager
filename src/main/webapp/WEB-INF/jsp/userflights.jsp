<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <title><fmt:message bundle="${lang}" key="pagename.flights"/></title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <section class="mt-4">
        <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0">${sessionScope.loggedinUser.firstName} ${sessionScope.loggedinUser.lastName} <fmt:message bundle="${lang}" key="tablename.userflights"/></p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.callsign"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.aircraft"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.brigade"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.depairport"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.destairport"/></th>
                                    <th><fmt:message bundle="${lang}" key="tablecolumnlabel.depaturedatetime"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.flightDTOList}" var="flight">
                                    <tr>
                                        <td>${flight.flightCallsign}</td>
                                        <td>${flight.aircraftDTO.registrationCode}</td>
                                        <td>${flight.brigadeDTO.brigadeName}</td>
                                        <td>${flight.departureAirport.IATACode}</td>
                                        <td>${flight.destinationAirport.IATACode}</td>
                                        <td><fmt:formatDate value="${flight.departureDateTime}" pattern="MM/dd/yyyy HH:mm"/></td>
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
</div>

<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
