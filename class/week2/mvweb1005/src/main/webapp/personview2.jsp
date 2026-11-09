<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="p1" class="model.Person" scope="session"></jsp:useBean>
<h2>
   Name: ${p1.name} <br/>
   Age: ${p1.age} <br/>
   Phone:${p1.phone} <br/>
</h2>
</body>
</html>