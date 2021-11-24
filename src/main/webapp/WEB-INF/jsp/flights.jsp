<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value=""/>
<html>
<head>
    <title><fmt:message key="pagename.flights"/></title>
    <c:import url="meta.jsp"/>
</head>
<body>

<c:import url="header.jsp"/>

<div id="login-one" class="login-one" style="padding-top: 20px;">
    <section class="mt-4">
        <c:import url="exception.jsp"/>
        <c:import url="command_result_state.jsp"/>
            <c:out value="${requestScope.flightDTOs}"/>

            <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0"><fmt:message key="tablename.flights"/></p>
                    </div>
                    <div class="card-body">

                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th><fmt:message key="tablecolumnlabel.callsign"/></th>
                                    <th><fmt:message key="tablecolumnlabel.aircraft"/></th>
                                    <th><fmt:message key="tablecolumnlabel.brigade"/></th>
                                    <th><fmt:message key="tablecolumnlabel.depairport"/></th>
                                    <th><fmt:message key="tablecolumnlabel.destairport"/></th>
                                    <th><fmt:message key="tablecolumnlabel.depaturedatetime"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.flightDTOS}" var="flight">
                                    <tr>
                                        <td>${flight.flightCallsign} - ${flight.aircraftDTO.registrationCode}</td>
                                        <td>
                                            ${flight.aircraftDTO.registrationCode} - ${flight.aircraftDTO.producer}
                                            - ${flight.aircraftDTO.model} </td>
                                        <td>${flight.brigadeDTO.brigadeName}</td>
                                        <td>${flight.departureAirport.IATACode} - ${flight.departureAirport.name}
                                            - ${flight.departureAirport.country} - ${flight.departureAirport.city} </td>
                                        <td>${flight.destinationAirport.IATACode} - ${flight.destinationAirport.name}
                                            - ${flight.destinationAirport.country}
                                            - ${flight.destinationAirport.city} </td>
                                        <td>${flight.departureDateTime} </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>
</section>

<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
