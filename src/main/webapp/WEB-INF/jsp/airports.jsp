<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<html>
<head>
    <title>
        <fmt:message bundle="${lang}" key="pagename.airports"/>
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
                            <fmt:message bundle="${lang}" key="tablename.airports"/>
                        </p>
                    </div>
                    <div class="card-body">
                        <div style="padding-top: 10px;padding-bottom: 10px;text-align: right;">
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                                <fmt:message bundle="${lang}" key="buttonname.addairport"/>
                            </button>
                        </div>
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">
                                            <div class="img-modal">
                                                <img src="${pageContext.request.contextPath}/assets/img/airport.jpg" width="498" height="200" >
                                            </div>
                                            <fmt:message bundle="${lang}" key="modaltitle.addairport"/>
                                        </h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form action="/controller" method="get">
                                        <input type="hidden" name="command" value="ADD_AIRPORT">
                                        <div class="modal-body">
                                            <label>
                                                <fmt:message bundle="${lang}" key="label.airoportName"/>
                                            </label>
                                            <input class="form-control" type="text" id="input-1" name="airportName"
                                                   placeholder=
                                                           "<fmt:message bundle="${lang}" key="placeholder.airoportName"/>"
                                                           minlength="1" maxlength="100">
                                            <label>
                                                <fmt:message bundle="${lang}" key="label.airoportCoutry"/>
                                            </label>
                                            <input class="form-control" type="text" id="input-2" name="airportCountry"
                                                   placeholder=
                                                           "<fmt:message bundle="${lang}" key="placeholder.airportCountry"/>"
                                                           minlength="1" maxlength="100">
                                            <label>
                                                <fmt:message bundle="${lang}" key="label.airoportCity"/>
                                            </label>
                                            <input class="form-control" type="text" id="input-3" name="airportCity"
                                                   placeholder=
                                                           "<fmt:message bundle="${lang}" key="placeholder.airoportCity"/>"
                                                           minlength="1" maxlength="100">
                                            <label>
                                                <fmt:message bundle="${lang}" key="label.airoportIATACode"/>
                                            </label>
                                            <input class="form-control" type="text" id="input-4" name="airportIATAcode"
                                                   placeholder=
                                                           "<fmt:message bundle="${lang}" key="placeholder.IATAcode"/>"
                                                           minlength="3" maxlength="3">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                <fmt:message bundle="${lang}" key="buttonname.close"/>
                                            </button>
                                            <button type="submit" class="btn btn-primary">
                                                <fmt:message bundle="${lang}" key="buttonname.addairport"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive table mb-0 pt-3 pr-2">
                            <table class="table table-striped table-sm my-0 mydatatable">
                                <thead>
                                <tr>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.name"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.country"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.city"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${lang}" key="tablecolumnlabel.IATAcode"/>
                                    </th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.airportDTOList}" var="airport">
                                    <tr>
                                        <td>${airport.name}</td>
                                        <td>${airport.country}</td>
                                        <td>${airport.city}</td>
                                        <td>${airport.IATACode}</td>
                                        <td>
                                            <i class="icon ion-edit editcolor"  data-toggle="modal" data-target="#exampleModal${airport.id}" style=" margin-left: 10px;"></i>
                                            <c:url value="/controller" var="deleteURL">
                                                <c:param name="command" value="DELETE_AIRPORT"/>
                                                <c:param name="delete_airport_id" value="${airport.id}"/>
                                            </c:url>
                                            <a href="${deleteURL}">
                                                <i class="icon ion-android-delete" style="margin-left: 10px;"></i>
                                            </a>
                                            <form action="/controller" method="get">
                                                <input type="hidden" name="command" value="EDIT_AIRPORT">
                                                <input type="hidden" name="edit_airport_id" value="${airport.id}">
                                                <div class="modal fade" id="exampleModal${airport.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel${airport.id}" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel${airport.id}">
                                                                    <fmt:message bundle="${lang}" key="modaltitle.editairport"/>
                                                                        ${airport.name}
                                                                </h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <label>
                                                                    <fmt:message bundle="${lang}" key="label.airoportName"/>
                                                                </label>
                                                                <input class="form-control" type="text" id="input-1${airport.id}" name="airportName"
                                                                       value="${airport.name}"
                                                                       placeholder="
                                                         <fmt:message bundle="${lang}" key="placeholder.airoportName"/>
                                                         " minlength="1" maxlength="100">
                                                                <label>
                                                                    <fmt:message bundle="${lang}" key="label.airoportCoutry"/>
                                                                </label>
                                                                <input class="form-control" type="text" id="input-2${airport.id}" name="airportCountry"
                                                                       value="${airport.country}"
                                                                       placeholder=
                                                                               "
                                                         <fmt:message bundle="${lang}" key="placeholder.airportCountry"/>
                                                         " minlength="1" maxlength="100">
                                                                <label>
                                                                    <fmt:message bundle="${lang}" key="label.airoportCity"/>
                                                                </label>
                                                                <input class="form-control" type="text" id="input-3${airport.id}" name="airportCity"
                                                                       value="${airport.city}"
                                                                       placeholder="
                                                         <fmt:message bundle="${lang}" key="placeholder.airoportCity"/>
                                                         " minlength="1" maxlength="100">
                                                                <label>
                                                                    <fmt:message bundle="${lang}" key="label.airoportIATACode"/>
                                                                </label>
                                                                <input class="form-control" type="text" id="input-4${airport.id}" name="airportIATAcode"
                                                                       value="${airport.IATACode}"
                                                                       placeholder="
                                                         <fmt:message bundle="${lang}" key="placeholder.IATAcode"/>
                                                         " minlength="3" maxlength="3">
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                <button type="submit" class="btn btn-primary">
                                                                    <fmt:message bundle="${lang}" key="buttonname.saveairport"/>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
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
</div>
<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>