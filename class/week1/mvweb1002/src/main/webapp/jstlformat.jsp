<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>       <%-- 格式化標籤 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Format</title>
</head>
<body>
<%-- 貨幣格式 --%>
<fmt:formatNumber value="123456" type="currency" currencyCode="TWD" /><br/>
<fmt:formatNumber value="123456" type="currency" currencyCode="USD" /><br/>
<fmt:formatNumber value="123456" type="currency" currencyCode="JPY" />
<p>
<%-- 千分位 --%>
<fmt:formatNumber value="1234567.89" pattern="#,###.##" />
<p>
<%-- 百分比 --%>
<fmt:formatNumber value="0.856" type="percent" />

</body>
</html>