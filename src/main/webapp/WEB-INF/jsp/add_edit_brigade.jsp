<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<fmt:message bundle="${lang}" key="pagename.editbrigade" var="editbrigade"/>
<fmt:message bundle="${lang}" key="pagename.createbrigade" var="createbrigade"/>
<fmt:message bundle="${lang}" key="formname.editbrigade" var="formeditbrigade"/>
<fmt:message bundle="${lang}" key="formname.addbrigade" var="formaddbrigade"/>
<fmt:message bundle="${lang}" key="buttonname.editbrigade" var="btneditbrigade"/>
<fmt:message bundle="${lang}" key="buttonname.addbrigade" var="btnaddbrigade"/>
<fmt:message bundle="${lang}" key="label.brigadename" var="labelbrigadename"/>
<fmt:message bundle="${lang}" key="label.brigadename" var="phbrigadename"/>

<html>
<head>
    <title>
        <c:choose>
            <c:when test="${requestScope.editpage}">
                ${editbrigade}
            </c:when>
            <c:otherwise>
                ${createbrigade}
            </c:otherwise>
        </c:choose>
    </title>
    <c:import url="meta.jsp"/>
</head>
<body>
<div id="login-one" class="login-one">
    <c:import url="header.jsp"/>
    <c:import url="exception.jsp"/>
    <c:import url="command_result_state.jsp"/>
    <c:import url="errors.jsp"/>
    <form class="login-one-userform">
        <div class="img-loggin">
            <img src="${pageContext.request.contextPath}/assets/img/brigade.png" width="500" height="200" >
        </div>
        <div class="col">
            <div class="login-one-ico"><i class="fa fa-users" id="lockico"></i></div>
            <div class="form-group">
                <div>
                    <c:choose>
                        <c:when test="${requestScope.editpage}">
                            <h3 id="heading">${formeditbrigade}</h3>
                        </c:when>
                        <c:otherwise>
                            <h3 id="heading">${formaddbrigade}</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
                <label>
                    ${labelbrigadename}
                </label>
                <input class="form-control" type="text" id="input-1" name="brigadename"
                <c:if test="${not empty requestScope.brigadeDTO}">
                       value="${requestScope.brigadeDTO.brigadeName}"
                </c:if>
                       placeholder="${phbrigadename}" name="brigadename" minlength="1" maxlength="50">
                <div style="margin-top: 10px">
                    <c:choose>
                        <c:when test="${requestScope.editpage}">
                            <input type="hidden" name="command" value="EDIT_BRIGADE">
                            <input type="hidden" name="edit_brigade_id" value="${requestScope.brigadeDTO.brigadeId}">
                            <button class="btn btn-primary" id="button" style="background-color:#007ac9;" type="submit">${btneditbrigade}</button>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="command" value="ADD_BRIGADE">
                            <button class="btn btn-primary" id="button" style="background-color:#007ac9;" type="submit">${btnaddbrigade}</button>
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