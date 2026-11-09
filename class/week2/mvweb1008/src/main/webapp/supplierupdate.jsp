<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>修改 Supplier</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>修改 Supplier</h2>
<form action="${pageContext.request.contextPath}/suppliers" method="post">
    Sup ID: <input type="text" name="supId" value="${supplier.supId}" readonly="readonly"/><br/>
    Sup Name: <input type="text" name="supName" value="${supplier.supName}"/><br/>    
    Street: <input type="text" name="street" value="${supplier.street}"/><br/>
    City: <input type="text" name="city" value="${supplier.city}"/><br/>
    State: <input type="text" name="state" size="2" value="${supplier.state}"/><br/>    
    Zip: <input type="text" name="zip" size="5" value="${supplier.zip}"/><br/>    
    <input type="submit" name="submit" value="修改" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/suppliers">回列表</a>
</body>
</html>
