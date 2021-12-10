<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" var="lang"/>
<c:forEach items="${requestScope.errors}" var="error">
    <div class="alert  alert-danger alert-dismissible fade show" role="alert">
        <strong><fmt:message bundle="${lang}" key="errorcode.${error}"/> </strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:forEach>
<c:if test="${not empty requestScope.oneError}">
    <div class="alert  alert-danger alert-dismissible fade show" role="alert">
        <strong><fmt:message bundle="${lang}" key="errorcode.${requestScope.oneError}"/></strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>