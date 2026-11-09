<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>新增 Supplier</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>新增 Supplier</h2>
<form action="${pageContext.request.contextPath}/suppliers" method="post">
    Sup ID: <input type="text" name="supId" /><br/>
    Sup Name: <input type="text" name="supName" /><br/>    
    Street: <input type="text" name="street" /><br/>
    City: <input type="text" name="city" /><br/>
    State: <input type="text" name="state" size="2" /><br/>    
    Zip: <input type="text" name="zip" size="5" /><br/>    
    <input type="submit" name="submit" value="新增" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/suppliers">回列表</a>
</body>
</html>
