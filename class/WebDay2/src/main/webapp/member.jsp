<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckMember</title>
</head>
<body>
<!-- http://localhost:8080/web0930/check -->
<!-- <form action="http://localhost:8080/WebDay2/member" method="post"> -->
<form action="${pageContext.request.contextPath}/member" method="post"> 

UserName:<input type="text" name="username"/><br/>
<!-- Password:<input type="text" name="password"/><br/> -->
<input type="submit" value="送出檢查"/>

</form>
</body>
</html>