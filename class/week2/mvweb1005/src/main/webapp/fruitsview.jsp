<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fruit View</title>
</head>
<body>
<table border="1" width="50%">
<tr><th>筆次</th><th>水果名稱</th><th>水果價格</th><th>水果產地</th></tr>
<c:forEach var="fruit" items="${fruit2s}" varStatus="status">
    <tr><td>${status.index + 1}</td>
    <td>${fruit.name}</td>
    <td>${fruit.price}</td>
    <td>${fruit.place}</td></tr>
</c:forEach>
</table>
</body>
</html>