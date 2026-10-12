<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 帶預設值 --%>
<%--  <c:set var="nickname" value="Johnny"/> --%>
<c:out value="${nickname}" default="匿名使用者" />
<hr>
<%-- 貨幣格式 --%>
<fmt:formatNumber value="123456" type="currency" currencyCode="TWD" /><br/>
<fmt:formatNumber value="123456" type="currency" currencyCode="USD" /><br/>
<fmt:formatNumber value="123456" type="currency" currencyCode="JPY" /><br/>

<%-- 千分位 --%>
<fmt:formatNumber value="1234567.89" pattern="#,###.##" /><br/>

<%-- 百分比 --%>
<fmt:formatNumber value="0.856" type="percent" /><br/>
</body>
</html>