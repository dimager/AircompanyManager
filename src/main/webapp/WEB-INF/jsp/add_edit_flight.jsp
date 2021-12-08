<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.addflight" var="pagenameaddflight"/>
<fmt:message bundle="${lang}" key="pagename.editflight" var="pagenameeditflight"/>
<fmt:message bundle="${lang}" key="formname.editflight" var="formnameeditflight"/>
<fmt:message bundle="${lang}" key="option.chooseaircraft" var="chooseaircraft"/>
<fmt:message bundle="${lang}" key="label.depatureairport" var="labeldepairport"/>
<fmt:message bundle="${lang}" key="label.destinationairport" var="destinationairport"/>
<fmt:message bundle="${lang}" key="option.chooseairport" var="chooseairport"/>
<fmt:message bundle="${lang}" key="label.departuretime" var="labaldeptime"/>
<fmt:message bundle="${lang}" key="buttonname.editflight" var="btneditflight"/>
<fmt:message bundle="${lang}" key="buttonname.addflight" var="btnaddflight"/>
<fmt:message bundle="${lang}" key="formname.addflight" var="formnameaddflight"/>
<fmt:message bundle="${lang}" key="label.callsign" var="labelcallsign"/>
<fmt:message bundle="${lang}" key="label.callsign" var="phcallsign"/>



<html>
<head>
    <title>
        <c:choose>
            <c:when test="${requestScope.editpage}">
                ${pagenameeditflight}
            </c:when>
            <c:otherwise>
                ${pagenameaddflight}
            </c:otherwise>
        </c:choose>
    </title>
    <c:import url="meta.jsp"/>
    <c:import url="scripts.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <form class="login-one-userform" action="/controller">
        <div class="img-loggin">
            <img src="${pageContext.request.contextPath}/assets/img/flight.png" width="500" height="200">
        </div>
        <div class="login-one-ico"><i class="fa fa-plane" id="lockico"></i></div>
        <div>
            <c:choose>
                <c:when test="${requestScope.editpage}">
                    <h3 id="heading">${formnameeditflight}</h3>
                </c:when>
                <c:otherwise>
                    <h3 id="heading">${formnameaddflight}</h3>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group">
            <label for="exampleFormControlInput1">
                ${labalcallsign}
            </label>
            <input class="form-control" id="exampleFormControlInput1" name="callsignInput" minlength="1" maxlength="10"
                   value="${requestScope.flightDTO.flightCallsign}"
                   placeholder="${phcallsign}">
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect1">
                ${labelaircraft}
            </label>
            <select name="selectedAircraft" class="form-control" id="exampleFormControlSelect1">
                <option value="" disabled selected>
                    ${chooseaircraft}
                </option>
                <c:forEach items="${requestScope.aircraftDTOList}" var="aircraftDTO">
                    <option value="${aircraftDTO.aircraftId}"
                            <c:if test="${requestScope.flightDTO.aircraftDTO.aircraftId == aircraftDTO.aircraftId}"> selected="selected" </c:if>>
                            ${aircraftDTO.registrationCode} ${aircraftDTO.producer} ${aircraftDTO.model}  </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect2">
                ${labeldepairport}
            </label>
            <select name="selectedDepartureAirport" class="form-control" id="exampleFormControlSelect2">
                <option value="" disabled selected>
                    ${chooseairport}
                </option>
                <c:forEach items="${requestScope.airportDTOList}" var="airportDTO">
                    <option value="${airportDTO.id}"
                            <c:if test="${requestScope.flightDTO.departureAirport.id == airportDTO.id}"> selected="selected" </c:if>>${airportDTO.IATACode}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleFormControlSelect4">
                ${destinationairport}
            </label>
            <select name="selectedDestinationAirport" class="form-control" id="exampleFormControlSelect4">
                <option value="" disabled selected>
                    ${chooseairport}
                </option>
                <c:forEach items="${requestScope.airportDTOList}" var="airportDTO">
                    <option value="${airportDTO.id}"
                            <c:if test="${requestScope.flightDTO.destinationAirport.id == airportDTO.id}"> selected="selected" </c:if>>${airportDTO.IATACode} </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea2">
                ${labaldeptime}
            </label>
            <div class="form-group" id="exampleFormControlTextarea2">
                <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                    <input type="text" class="form-control" name="selectedDateTime"
                            <c:if test="${not empty requestScope.flightTime}"> value="${requestScope.flightTime}" </c:if>
                           data-target="#datetimepicker1"/>
                    <div class="input-group-append" data-target="#datetimepicker1"
                         data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker1').datetimepicker();
                });
            </script>
        </div>
        <c:choose>
            <c:when test="${requestScope.editpage}">
                <input type="hidden" name="edit_flight_id" value="${requestScope.flightDTO.id}">
                <input type="hidden" name="command" value="EDIT_FLIGHT">
                <input type="hidden" name="selectedBrigade"
                <c:choose>
                <c:when test="${not empty requestScope.flightDTO.brigadeDTO}">
                       value="${requestScope.flightDTO.brigadeDTO.brigadeId}"
                </c:when>
                <c:otherwise>
                       value="0"
                </c:otherwise>
                </c:choose>
                >
                <button class="btn btn-primary" id="button" style="background-color:#007ac9;" type="submit">
                        ${btneditflight}
                </button>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="command" value="ADD_FLIGHT">
                <button class="btn btn-primary" id="button" style="background-color:#007ac9;" type="submit">
                        ${btnaddflight}
                </button>
            </c:otherwise>
        </c:choose>
    </form>
</div>
<c:import url="scripts.jsp"/>
<c:import url="footer.jsp"/>
</body>
</html>