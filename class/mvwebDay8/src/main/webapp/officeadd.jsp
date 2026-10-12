<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>新增 Office</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>新增 Office</h2>
<form action="${pageContext.request.contextPath}/offices" method="post">
    Office Code: <input type="text" name="officeCode" /><br/>
    City: <input type="text" name="city" /><br/>
    Phone: <input type="text" name="phone" /><br/>
    Address Line1: <input type="text" name="addressLine1" /><br/>
    Address Line2: <input type="text" name="addressLine2" /><br/>
    State: <input type="text" name="state" /><br/>
    Country: <input type="text" name="country" /><br/>
    Postal Code: <input type="text" name="postalCode" /><br/>
    Territory: <input type="text" name="territory" /><br/>
    <input type="submit" name="submit" value="新增" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/offices">回列表</a>
</body>
</html>
