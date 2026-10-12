<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<thead>
<th width="20%">次</th>
<th width="30%">名稱</th>
<th width="30%">價格</th>
<th width="40%">產地</th>
</thead>
<tbody>
<c:forEach var="item" items="${fruitsArray}" varStatus="status">
<tr>
<td>${status.index + 1}</td>
<td>${item.name}</td>
<td>${item.price}</td>
<td>${item.place}</td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>