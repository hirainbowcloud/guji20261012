<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--@ page import="model.Person" --%>


<%--
Person p = new Person("Lala",20,"0989-899-888");
pageContext.setAttribute("p1", p);
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <p>name:${p1.name} -age:${p1.age} -phone:${p1.phone}</p> -->
<hr>
<jsp:useBean id="p1" class="model.Person" scope="session"></jsp:useBean>
<jsp:setProperty property="name" name="p1" value="Kiki"/>
<jsp:setProperty property="age" name="p1" value="25"/>
<jsp:setProperty property="phone" name="p1" value="0989-999-888"/>

<h2>
name:${p1.name}- age:${p1.age}- phone:${p1.phone}
</h2>
</body>
</html>