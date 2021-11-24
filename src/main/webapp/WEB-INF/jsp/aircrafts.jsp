<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../assets/css/styles.css"/>
    <title><fmt:message key="pagename.aircrafts"/></title>
    <c:import url="meta.jsp"/>
</head>
<body>
<c:import url="header.jsp"/>
<div id="login-one" class="login-one" style="padding-top: 20px;">
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <section class="mt-4">
        <div class="container-fluid">
            <div class="row">
                <div class="col" style="padding-right: 45px;padding-left: 45px;">
                    <div class="card shadow">
                        <div class="card-header py-2">
                            <p class="lead text-info m-0"><fmt:message key="tablename.aircrafts"/></p>
                        </div>
                        <div class="card-body">
                            <div style="padding-top: 10px;padding-bottom: 10px;text-align: right;">
                                <form method="get" action="/controller">
                                    <input type="hidden" name="command" value="SHOW_ADD_AIRCRAFT_PAGE">
                                    <button class="btn btn-primary shadow" type="submit"><fmt:message
                                            key="button.addaircraft"/></button>
                                </form>
                            </div>
                            <div class="table-responsive table mb-0 pt-3 pr-2">
                                <table class="table table-striped table-sm my-0 mydatatable">
                                    <thead>
                                    <tr>

                                        <th></th>
                                        <th><fmt:message key="tablecolumnlabel.regcode"/></th>
                                        <th><fmt:message key="tablecolumnlabel.producer"/></th>
                                        <th><fmt:message key="tablecolumnlabel.model"/></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.aircraftDTOList}" var="aircraft">
                                        <tr>

                                            <td style="width: 30px;text-align: center;"> <i  class="ion ion-ios-paperplane"></i></td>
                                            <td> ${aircraft.registrationCode}</td>
                                            <td>${aircraft.producer}</td>
                                            <td>${aircraft.model}</td>
                                            <td class="table-buttons" id="table-buttons" >
                                                <c:url value="/controller" var="editURL">
                                                    <c:param name="command" value="SHOW_EDIT_AIRCRAFT_PAGE"/>
                                                    <c:param name="edit_aircraft_id" value="${aircraft.aircraftId}"/>
                                                </c:url>
                                                <c:url value="/controller" var="deleteURL">
                                                    <c:param name="command" value="DELETE_AIRCRAFT"/>
                                                    <c:param name="delete_aircraft_id" value="${aircraft.aircraftId}"/>
                                                </c:url>

                                                <a href="${editURL}"> <i class="icon ion-edit"  style=" margin-left: 10px;"></i></a>
                                                <a href="${deleteURL}"> <i class="icon ion-android-delete" style="margin-left: 10px;"></i></a>
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
        </div>
    </section>
</div>
<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
