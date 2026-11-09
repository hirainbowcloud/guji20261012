<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Print</title>
</head>
<body>
<%-- 帶預設值 --%>
<%--  <c:set var="nickname" value="Johnny"/> --%>
<c:out value="${nickname}" default="匿名使用者" />

</body>
</html>