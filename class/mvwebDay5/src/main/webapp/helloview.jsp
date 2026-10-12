<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>Message: ${msg}</p>
<hr>
<form action="hello.do" method="get">

user name:
<select name="user">
<option>John</option>
<option>Ray</option>
<option>Vincent</option>
<input value="送出" type="submit">
</select>

</form>
</body>
</html>