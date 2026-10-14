
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首頁</title>
</head>

<body>
<h2>online: ${applicationScope.onlineCount}</h2>
<h2><%= "Hello World!" %></h2>
<h3>Start Tomcat Time: ${applicationScope.appStart}</h3>
<h3>address: ${applicationScope.appAddress}</h3>

</body>
</html>
