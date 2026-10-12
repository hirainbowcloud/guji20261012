<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <form action="${pageContext.request.contextPath}/explogicservlet" method="post">  -->
<form action="http://localhost:8080/WebDay2/explogicservlet" method="post"> 

底數:<input type="number" name="base" min="1" max="100" value ="1"/><br/>

指數:<input type="number" name="exponent" min="1" max="100" value ="1"/><br/>

<input type="submit" value="計算"/>
</form>
</body>
</html>