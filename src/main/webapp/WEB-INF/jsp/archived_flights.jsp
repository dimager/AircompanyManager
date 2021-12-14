<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.archiveflights" var="pageName"/>
<fmt:message bundle="${lang}" key="tablename.flights" var="tableName"/>
<fmt:message bundle="${lang}" key="buttonname.addflight" var="btnAddFlight"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.brigade" var="colBrigade"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.aircraft" var="colAircraft"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.callsign" var="colCallsign"/>
<fmt:message bundle="${lang}" key="buttonname.changebrigade" var="btnChangeBrigade"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.depairport" var="colDepAirport"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.destairport" var="colDestAirport"/>
<fmt:message bundle="${lang}" key="tablecolumnlabel.depaturedatetime" var="colDepDateTime"/>


<html>
<head>
    <title>${pageName}</title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <section class="mt-4">
        <div class="row">
            <div class="col" style="padding-right: 45px;padding-left: 45px;">
                <div class="card shadow">
                    <div class="card-header py-2">
                        <p class="lead text-info m-0">
                            ${tableName}
                        </p>
                    </div>
                    <div class="card-body">
                        <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                            <div style="padding-top: 10px;padding-bottom: 10px;text-align: right;">
                                <form method="get" action="/controller">
                                    <input type="hidden" name="command" value="SHOW_ADD_FLIGHT_PAGE">
                                    <button class="btn btn-primary shadow" type="submit">
                                            ${btnAddFlight}
                                    </button>
                                </form>
                            </div>
                        </c:if>
                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th>${colCallsign}</th>
                                    <th>${colAircraft}</th>
                                    <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                                        <th>${colBrigade}</th>
                                    </c:if>
                                    <th>${colDepAirport}</th>
                                    <th>${colDestAirport}</th>
                                    <th>${colDepDateTime}</th>
                                    <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                                        <th></th>
                                    </c:if>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.flightDTOList}" var="flight">
                                    <c:if test="${flight.isArchived}">
                                    <tr>
                                        <td>${flight.flightCallsign}</td>
                                        <td>${flight.aircraftDTO.registrationCode} (${flight.aircraftDTO.model})</td>
                                        <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                                        <td>${flight.brigadeDTO.brigadeName}</td>

                                        </c:if>
                                        <td>${flight.departureAirport.IATACode}</td>
                                        <td>${flight.destinationAirport.IATACode}</td>

                                        <td><fmt:formatDate value="${flight.departureDateTime}" pattern="MM/dd/yyyy HH:mm" var="datetime"/>${datetime}</td>

                                        <c:if test="${sessionScope.loggedinUser.role == Role_MANAGER}">
                                            <td>
                                                <c:url value="/controller" var="showBrigadeURL">
                                                    <c:param name="command" value="SHOW_BRIGADE_WITH_USERS_PAGE_ARCHIVE"/>
                                                    <c:param name="brigade_id" value="${flight.brigadeDTO.brigadeId}"/>
                                                </c:url>
                                                <a href="${showBrigadeURL}">
                                                    <i class="icon ion-ios-people" style="margin-left: 10px;"></i>
                                                </a>
                                            </td>
                                        </c:if>
                                    </tr>
                                    </c:if>
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