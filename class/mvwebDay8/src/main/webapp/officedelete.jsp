<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>刪除 Office</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>刪除 Office</h2>
<form action="${pageContext.request.contextPath}/offices" method="post">
    Office Code: <input type="text" name="officeCode" value="${office.officeCode}" readonly/><br/>
    City: <input type="text" name="city" value="${office.city}" readonly/><br/>
    <input type="submit" name="submit" value="刪除" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/offices">回列表</a>
</body>
</html>
