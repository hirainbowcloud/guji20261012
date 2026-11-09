<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Name Age</title>
</head>
<body>
<c:set var="name" value="${param.name}" />
<c:set var="age" value="${param.age}" />
<h2>
   <p>你輸入資料</p>
   Name: ${name} <br/>
   Age : ${age}
</h2>
</body>
</html>