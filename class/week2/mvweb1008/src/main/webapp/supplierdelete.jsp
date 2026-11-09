<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>刪除 Supplier</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>刪除 Supplier</h2>
<form action="${pageContext.request.contextPath}/suppliers" method="post">
    Sup ID: <input type="text" name="supId" value="${supplier.supId}" readonly="readonly"/><br/>
    Sup Name: <input type="text" name="supName" value="${supplier.supName}" readonly="readonly"/><br/>    
    <input type="submit" name="submit" value="刪除" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/suppliers">回列表</a>
</body>
</html>
