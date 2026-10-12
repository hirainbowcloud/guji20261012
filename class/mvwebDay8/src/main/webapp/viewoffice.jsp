<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>Office List</title>
</head>
<body>
<h2>Office 資料一覽</h2>
<p><a href="${pageContext.request.contextPath}/offices?action=add">新增 Office</a></p>
<table border="1" cellpadding="5" cellspacing="0">
<tr>
    <th>Office Code</th>
    <th>City</th>
    <th>Phone</th>
    <th>Address Line1</th>
    <th>Address Line2</th>
    <th>State</th>
    <th>Country</th>
    <th>Postal Code</th>
    <th>Territory</th>
    <th>Action</th>
</tr>
<c:forEach var="o" items="${offices}">
<tr>
    <td>${o.officeCode}</td>
    <td>${o.city}</td>
    <td>${o.phone}</td>
    <td>${o.addressLine1}</td>
    <td>${o.addressLine2}</td>
    <td>${o.state}</td>
    <td>${o.country}</td>
    <td>${o.postalCode}</td>
    <td>${o.territory}</td>
    <td>
         <a href="offices?action=update&oc=${o.officeCode}">修改</a>
         <a href="offices?action=delete&oc=${o.officeCode}">刪除</a>
    </td>
</tr>
</c:forEach>
<c:if test="${empty offices}">
<tr><td colspan="10">查無資料</td></tr>
</c:if>
</table>
</body>
</html>
