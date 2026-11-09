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
<c:set var="fruits" value="${fts}" />
<c:forEach var="fruit" items="${fruits}" varStatus="status">
    <p>${status.index + 1}. ${fruit}</p>
</c:forEach>

</body>
</html>