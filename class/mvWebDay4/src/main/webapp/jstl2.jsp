<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl語法+EL語法</title>
</head>
<body>
<%-- 設定簡單變數 --%>
<c:set var="name" value="${param.name}" />
<c:set var="age" value="${param.age}" />
<p>---Data---</p>
<p>Name: ${name}</p>
<p>Age: ${age}</p>
</body>
</html>