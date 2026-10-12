<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Date Format</title>
</head>
<body>
<%-- 建立日期物件 --%>
<%
   java.util.Date d=new java.util.Date();
   pageContext.setAttribute("now",d);
%>

<%-- 日期格式 --%>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
<p>
<%-- 時間格式 --%>
<fmt:formatDate value="${now}" pattern="HH:mm:ss" />
<p>
<%-- 日期時間格式 --%>
<fmt:formatDate value="${now}" pattern="yyyy年MM月dd日 HH:mm:ss" />

</body>
</html>