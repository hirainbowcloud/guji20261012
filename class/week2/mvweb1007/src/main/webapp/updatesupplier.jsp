<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>修改 Supplier</title>
</head>
<body>
<h2>修改 Supplier</h2>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<form action="${pageContext.request.contextPath}/SupplierUpdate" method="post">
    SupId: <input type="text" name="supId" value="${sp1.supId}"/><br/>
    SupName: <input type="text" name="supName" value="${sp1.supName}"/><br/>
    Street: <input type="text" name="street" value="${sp1.street}"/><br/>
    City: <input type="text" name="city" value="${sp1.city}"/><br/>
    State: <input type="text" name="state" value="${sp1.state}"/><br/>
    Zip: <input type="text" name="zip" value="${sp1.zip}"/><br/>
    <input type="submit" value="修改" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/SupplierList">回列表</a>
</body>
</html>