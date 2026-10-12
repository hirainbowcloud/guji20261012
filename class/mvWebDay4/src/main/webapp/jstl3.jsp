<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Even Odd</title>
</head>
<body>
<c:set var="num" value="${param.num}" />

<c:if test="${num %2 == 0}">
    <p>${num},偶數</p>
</c:if>

<c:if test="${num %2 != 0}">
    <p>${num},奇數</p>
</c:if>

</body>
</html>