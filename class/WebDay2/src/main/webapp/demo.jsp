<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HTML Page</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/demo" method="post">
User:<input type="text" name="username"/><br/>
Data:<input type="number" name="num" value="5"/>
<input type="submit" value="傳送資料"/>
</form>
</body>
</html>