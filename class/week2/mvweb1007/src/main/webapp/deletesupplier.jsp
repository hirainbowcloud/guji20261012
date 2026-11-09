<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>刪除 Supplier</title>
</head>
<body>
<h2>刪除 Supplier</h2>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<form action="${pageContext.request.contextPath}/SupplierDelete" method="post">
    SupId: <input type="text" name="supId" value=${sp1.supId}/><br/>
    <input type="submit" value="刪除" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/SupplierList">回列表</a>
</body>
</html>