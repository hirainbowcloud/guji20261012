<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>修改 Office</title>
</head>
<body>
<c:if test="${not empty error}">
    <p style="color:red"><c:out value="${error}" /></p>
</c:if>
<h2>修改 Office</h2>
<form action="${pageContext.request.contextPath}/offices" method="post">
    Office Code: <input type="text" name="officeCode" value="${office.officeCode}" readonly/><br/>
    City: <input type="text" name="city" value="${office.city}"/><br/>
    Phone: <input type="text" name="phone" value="${office.phone}"/><br/>
    Address Line1: <input type="text" name="addressLine1" value="${office.addressLine1}"/><br/>
    Address Line2: <input type="text" name="addressLine2" value="${office.addressLine2}"/><br/>
    State: <input type="text" name="state" value="${office.state}"/><br/>
    Country: <input type="text" name="country" value="${office.country}"/><br/>
    Postal Code: <input type="text" name="postalCode" value="${office.postalCode}"/><br/>
    Territory: <input type="text" name="territory" value="${office.territory}"/><br/>
    <input type="submit" name="submit" value="修改" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/offices">回列表</a>
</body>
</html>
