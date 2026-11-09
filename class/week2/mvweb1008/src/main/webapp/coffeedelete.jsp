<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>刪除 Coffee</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>刪除 Coffee</h2>
<form action="${pageContext.request.contextPath}/coffees" method="post">
    Coffee Name: <input type="text" name="cofName" value="${coffee.cofName}"/><br/>    
    <input type="submit" name="submit" value="刪除" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/coffees">回列表</a>
</body>
</html>