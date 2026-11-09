<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>修改 Coffee</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>修改 Coffee</h2>
<form action="${pageContext.request.contextPath}/coffees" method="post">
    Coffee Name: <input type="text" name="cofName" value="${coffee.cofName}"/><br/>
    Supplier ID: <input type="text" name="supId" value="${coffee.supId}"/><br/>    
    Price: <input type="text" name="price" value="${coffee.price}"/><br/>
    Sales: <input type="text" name="sales" value="${coffee.sales}"/><br/>
    Total: <input type="text" name="total" value="${coffee.total}"/><br/>    
    <input type="submit" name="submit" value="修改" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/coffees">回列表</a>
</body>
</html>