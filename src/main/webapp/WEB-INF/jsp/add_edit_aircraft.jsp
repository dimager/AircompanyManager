<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value=""/>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${requestScope.editpage}">
                <fmt:message key="pagename.editaircraft"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="pagename.createaircraft"/>
            </c:otherwise>
        </c:choose>
    </title>
    <c:import url="meta.jsp"/>
</head>
<body>
<c:import url="header.jsp"/>
<div id="login-one" class="login-one">
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <form class="login-one-form">
        <div class="col">
            <div class="login-one-ico"><i class="fa fa-plane" id="lockico"></i></div>
            <div class="form-group">
                <div>
                    <c:choose>
                        <c:when test="${requestScope.editpage}">
                            <h3 id="heading"><fmt:message key="formname.editaircraft"/></h3>
                        </c:when>
                        <c:otherwise>
                            <h3 id="heading"><fmt:message key="formname.addaircraft"/></h3>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:out value="${requestScope.command}"></c:out>
                <label>Reg code</label>
                <input class="form-control" type="text" id="input-1" name="regCode"
                       <c:if test="${not empty requestScope.aircraftDTO}">value="${requestScope.aircraftDTO.registrationCode}"</c:if>
                       placeholder="reg code" minlength="1" maxlength="10">
                <label>Producer</label>
                <input class="form-control" type="text" id="input-4" name="aircraftProducer"
                       <c:if test="${not empty requestScope.aircraftDTO}">value="${requestScope.aircraftDTO.producer}"</c:if>
                       placeholder="producer" minlength="1" maxlength="50">
                <label>Model</label>
                <input class="form-control" type="text" id="input-3" name="aircraftModel" placeholder="model"
                       <c:if test="${not empty requestScope.aircraftDTO}">value="${requestScope.aircraftDTO.model}"</c:if>
                       minlength="1" maxlength="50">
                <input type="hidden" name="aircraftId"
                       <c:if test="${not empty requestScope.aircraftDTO}">value="${requestScope.aircraftDTO.aircraftId}"></c:if>


                <div style="margin-top: 10px">
                    <c:choose>
                        <c:when test="${fn:contains(pageContext.request.queryString,'edit_aircraft_id')}">
                            <input type="hidden" name="command" value="EDIT_AIRCRAFT"/>
                            <button class="btn btn-primary" id="button" style="background-color:#007ac9;" type="submit">
                                <fmt:message key="button.editaircraft"/></button>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="command" value="ADD_AIRCRAFT"/>
                            <button class="btn btn-primary" id="button" style="background-color:#007ac9;" type="submit">
                                <fmt:message key="button.addaircraft"/></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

        </div>
    </form>
</div>
<c:import url="footer.jsp"/>
<c:import url="scripts.jsp"/>
</body>
</html>
