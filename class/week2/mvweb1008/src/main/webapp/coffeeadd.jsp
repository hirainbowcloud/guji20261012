<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>新增 Coffee</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>新增 Coffee</h2>
<form action="${pageContext.request.contextPath}/coffees" method="post">
    Coffee Name: <input type="text" name="cofName" /><br/>
    Supplier ID: <input type="text" name="supId" /><br/>    
    Price: <input type="text" name="price" /><br/>
    Sales: <input type="text" name="sales" /><br/>
    Total: <input type="text" name="total" /><br/>    
    <input type="submit" name="submit" value="新增" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/coffees">回列表</a>
</body>
</html>