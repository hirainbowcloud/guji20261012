<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>Coffee List</title>
</head>
<body>
<h2>Coffee 資料一覽</h2>
<p><a href="${pageContext.request.contextPath}/coffees?action=add">新增 Coffee</a></p>
<table border="1" cellpadding="5" cellspacing="0">
<tr>    
    <th>Coffee Name</th>
    <th>Suplier ID</th>
    <th>Price</th>
    <th>Sales</th>
    <th>Total</th>
    <th>Action</th>
</tr>
<c:forEach var="s" items="${coffees}">
<tr>
    <td>${s.cofName}</td>
    <td><c:out value="${s.supId}" /></td>
    <td><c:out value="${s.price}" /></td>
    <td><c:out value="${s.sales}" /></td>
    <td>${s.total}</td>    
    <td>
         <a href="coffees?action=update&cn=${s.cofName}">修改</a>
         <a href="coffees?action=delete&cn=${s.cofName}">刪除</a>
    </td>
</tr>
</c:forEach>
<c:if test="${empty coffees}">
<tr><td colspan="6">查無資料</td></tr>
</c:if>
</table>
</body>
</html>
