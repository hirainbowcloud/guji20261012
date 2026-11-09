<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Person" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Person View</title>
</head>
<body>
<jsp:useBean id="p1" class="model.Person" scope="session"></jsp:useBean>
<jsp:setProperty  property="name" name="p1" value="Andy" ></jsp:setProperty>
<jsp:setProperty  property="age" name="p1" value="25" ></jsp:setProperty>
<jsp:setProperty  property="phone" name="p1" value="123456789" ></jsp:setProperty>
<h2>
   Name: ${p1.name} <br/>
   Age: ${p1.age} <br/>
   Phone:${p1.phone} <br/>
</h2>
</body>
</html>