<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   List<String> ft=List.of("蘋果", "香蕉", "橘子");
   pageContext.setAttribute("fts",ft);
%>
<c:set var="fruits" value="${fts}" />
<c:forEach var="fruit" items="${fruits}" varStatus="status">
    <p>${status.index + 1}. ${fruit}</p>
</c:forEach>
<c:forEach begin="1" end="5" var="i">
    <span>${i}</span>
</c:forEach>
</body>
</html>