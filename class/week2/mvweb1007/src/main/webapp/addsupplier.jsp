<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>新增 Supplier</title>
</head>
<body>
<h2>新增 Supplier</h2>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<form action="${pageContext.request.contextPath}/SupplierAdd" method="post">
    SupId: <input type="text" name="supId" /><br/>
    SupName: <input type="text" name="supName" /><br/>
    Street: <input type="text" name="street" /><br/>
    City: <input type="text" name="city" /><br/>
    State: <input type="text" name="state" /><br/>
    Zip: <input type="text" name="zip" /><br/>
    <input type="submit" value="新增" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/SupplierList">回列表</a>
</body>
</html>