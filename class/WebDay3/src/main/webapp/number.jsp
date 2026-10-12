<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <form action="${pageContext.request.contextPath}/prime" method="post">  -->
<!-- <form action="http://localhost:8080/WebDay2/prime" method="post"> -->
<form action="catchprime.jsp" method="post"> 

數字:<input type="number" name="num" min="10" max="100" value="10"/><br/>

<input type="submit" value="送出檢查"/>
</form>
</body>
</html>