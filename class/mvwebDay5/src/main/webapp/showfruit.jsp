<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Fruit" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   Fruit f1=new Fruit("Pear",150,"Taiwan");
   pageContext.setAttribute("fruit",f1);
%>

<!--  Fruit fruit2=new Fruit();  -->
<jsp:useBean id="fruit2" class="model.Fruit">
</jsp:useBean>
<jsp:setProperty property="name" name="fruit2" value="Apple"/>
<jsp:setProperty property="price" name="fruit2" value="15"/>
<jsp:setProperty property="place" name="fruit2" value="USA"/>
<hr>
<h2>
   Name:${fruit.name} <br/>
   Price:${fruit.price} <br/>   
   Place:${fruit.place} <br/>
</h2>
<hr>
<h3>
   Name:${fruit2.name} <br/>
   Price:${fruit2.price} <br/>   
   Place:${fruit2.place} <br/>
</h3>
</body>
</html>