<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Discount</title>
</head>
<body>
<c:set var="money" value="25000" />
<h2>
   金額:${money}
</h2>
<c:choose>
    <c:when test="${money >= 30000}">
        <p>打8折</p>
        <p>${money*0.8}</p>
    </c:when>
    <c:when test="${money >= 20000}">
        <p>打9折</p>
        <p>${money*0.9}</p>
    </c:when>
    <c:when test="${money>= 10000}">
       <p>打95折</p>
        <p>${money*0.95}</p>
    </c:when>
    <c:otherwise>
        <p>不打折</p>
        <p>${money}</p>
    </c:otherwise>
</c:choose>
</body>
</html>