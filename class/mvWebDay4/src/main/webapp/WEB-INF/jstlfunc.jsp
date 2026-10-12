<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="text" value="Hello World 你好世界" />
<!--
<c:out value="${text}" default="Hello World 你好世界" />
這樣不可以，程式會當掉，c:out value="${text}"是印出，沒有給value
-->
<%-- 字串長度 --%>
<p>長度：${fn:length(text)}</p>

<%-- 大小寫轉換 --%>
<p>大寫：${fn:toUpperCase(text)}</p>
<p>小寫：${fn:toLowerCase(text)}</p>

<%-- 字串搜尋 --%>
<p>包含 'World'：${fn:contains(text, 'World')}</p>
<p>位置：${fn:indexOf(text, 'World')}</p>

<%-- 字串截斷 --%>
<p>前5字元：${fn:substring(text, 0, 5)}</p>

<%-- 字串取代 --%>
<p>取代：${fn:replace(text, 'World', 'EE')}</p>

<%-- HTML 轉義 --%>
<c:set var="html" value="<script>alert('XSS')</script>" />
<p>轉義：${fn:escapeXml(html)}</p>

</body>
</html>