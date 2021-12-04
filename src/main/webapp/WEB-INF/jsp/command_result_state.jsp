<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<c:if test="${not empty requestScope.commandResult}">
    <div class="alert  alert-success alert-dismissible fade show" role="alert">
        <strong><fmt:message bundle="${lang}" key="result.${requestScope.commandResult}"/> </strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>