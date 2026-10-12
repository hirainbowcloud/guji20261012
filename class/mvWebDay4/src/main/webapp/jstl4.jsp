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
<c:set var="score" value="85" />
<p>分數：${score}</p>
<c:choose>
    <c:when test="${score >= 90}">
        <p>等級：A</p>
    </c:when>
    <c:when test="${score >= 80}">
        <p>等級：B</p>
    </c:when>
    <c:when test="${score >= 70}">
        <p>等級：C</p>
    </c:when>
    <c:otherwise>
        <p>等級：D</p>
    </c:otherwise>
</c:choose>

</body>
</html>