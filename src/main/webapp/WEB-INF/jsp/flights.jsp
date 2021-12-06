<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <title>
        <fmt:message bundle="${lang}" key="pagename.flights"/>
    </title>
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
                        <p class="lead text-info m-0">
                            <fmt:message bundle="${lang}" key="tablename.flights"/>
                        </p>
                    </div>
                    <div class="card-body">
                        <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                            <div style="padding-top: 10px;padding-bottom: 10px;text-align: right;">
                                <form method="get" action="/controller">
                                    <input type="hidden" name="command" value="SHOW_ADD_FLIGHT_PAGE">
                                    <button class="btn btn-primary shadow" type="submit">
                                        <fmt:message bundle="${lang}" key="buttonname.addflight"/>
                                    </button>
                                </form>
                            </div>
                        </c:if>
                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.callsign"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.aircraft"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.brigade"/>
                                    </th>
                                    <c:if test="${sessionScope.loggedinUser.role.roleId >= 2}">
                                        <th>
                                            <fmt:message bundle="${lang}" key="tablecolumnlabel.brigade"/>
                                        </th>
                                    </c:if>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.depairport"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.destairport"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.depaturedatetime"/>
                                    </th>
                                    <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                                        <th></th>
                                    </c:if>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.flightDTOList}" var="flight">
                                    <tr>
                                        <td>${flight.flightCallsign}</td>
                                        <td>${flight.aircraftDTO.registrationCode} (${flight.aircraftDTO.model})</td>
                                        <td>${flight.brigadeDTO.brigadeName}</td>
                                        <c:if test="${sessionScope.loggedinUser.role.roleId >= Role_MANAGER.roleId}">
                                            <td>
                                                <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#exampleModalLong${flight.id}">
                                                    <fmt:message bundle="${lang}" key="buttonname.changebrigade"/>
                                                </button>
                                                <div class="modal fade" id="exampleModalLong${flight.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle${flight.id}" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLongTitle${flight.id}">
                                                                        ${flight.flightCallsign} -
                                                                    <fmt:message bundle="${lang}" key="buttonname.changebrigade"/>
                                                                </h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="list-group">
                                                                    <c:forEach var="brigade" items="${brigadeDTOList}">
                                                                        <c:if test="${brigade.brigadeId != flight.brigadeDTO.brigadeId}">
                                                                            <c:url value="/controller" var="changeBrigade">
                                                                                <c:param name="command" value="CHANGE_BRIGADE"/>
                                                                                <c:param name="new_brigade" value="${brigade.brigadeId}"/>
                                                                                <c:param name="edit_flight_id" value="${flight.id}"/>
                                                                            </c:url>
                                                                            <a href="${changeBrigade}" class="list-group-item list-group-item-action">
                                                                                    ${brigade.brigadeName}
                                                                            </a>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </c:if>
                                        <td>${flight.departureAirport.IATACode}</td>
                                        <td>${flight.destinationAirport.IATACode}</td>

                                        <td><fmt:formatDate value="${flight.departureDateTime}" pattern="MM/dd/yyyy HH:mm"/></td>
                                        <c:if test="${sessionScope.loggedinUser.role == Role_ADMIN}">
                                            <td>
                                                <c:url value="/controller" var="editURL">
                                                    <c:param name="command" value="SHOW_EDIT_FLIGHT_PAGE"/>
                                                    <c:param name="edit_flight_id" value="${flight.id}"/>
                                                </c:url>
                                                <a href="${editURL}">
                                                    <i class="icon ion-edit" style=" margin-left: 10px;"></i>
                                                </a>
                                                <c:url value="/controller" var="editURL">
                                                    <c:param name="command" value="DELETE_FLIGHT"/>
                                                    <c:param name="delete_flight_id" value="${flight.id}"/>
                                                </c:url>
                                                <a href="${editURL}">
                                                    <i class="icon ion-android-delete   " style=" margin-left: 10px;"></i>
                                                </a>
                                            </td>
                                        </c:if>
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