<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<title>Supplier List</title>
</head>
<body>
<h2>Supplier 資料一覽</h2>
<p><a href="${pageContext.request.contextPath}/suppliers?action=add">新增 Supplier</a></p>
<table border="1" cellpadding="5" cellspacing="0">
<tr>    
    <th>Sup ID</th>
    <th>Sup Name</th>
    <th>Street</th>
    <th>City</th>
    <th>State</th>
    <th>Zip</th>
    <th>Action</th>
</tr>
<c:forEach var="s" items="${suppliers}">
<tr>
    <td><c:out value="${s.supId}" /></td>
    <td>${s.supName}</td>
    <td><c:out value="${s.street}" /></td>
    <td><c:out value="${s.city}" /></td>
    <td><c:out value="${s.state}" /></td>
    <td><c:out value="${s.zip}" /></td>
    <td>
         <a href="suppliers?action=update&sid=${s.supId}">修改</a>
         <a href="suppliers?action=delete&sid=${s.supId}">刪除</a>
    </td>
</tr>
</c:forEach>
<c:if test="${empty suppliers}">
<tr><td colspan="7">查無資料</td></tr>
</c:if>
</table>
</body>
</html>
