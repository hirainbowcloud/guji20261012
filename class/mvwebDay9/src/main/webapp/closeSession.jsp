<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Close Session</title>
</head>
<body>
<% session.invalidate(); %>
<h2>目前線上人數：${applicationScope.onlineCount}</h2>
</body>
</html>