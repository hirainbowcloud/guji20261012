<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@page import="java.util.*" %>

<%
   List<String> ft=List.of("蘋果", "香蕉", "橘子");
   pageContext.setAttribute("fts",ft);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 迭代陣列 --%>
<!-- 
<c:set var="fruits" value="${['蘋果', '香蕉', '橘子']}" />
<c:forEach var="fruit" items="${fruits}" varStatus="status">
    <p>${status.index + 1}. ${fruit}</p>
</c:forEach>
 -->
<%-- 數字範圍迭代 --%>
<c:forEach begin="1" end="5" var="i">
    <span>${i}</span>
</c:forEach>
<hr>
<c:set var="fruits" value="${fts}" />
<c:forEach var="fruit" items="${fruits}" varStatus="status">
    <p>${status.index + 1}. ${fruit}</p>
</c:forEach>
</body>
</html>