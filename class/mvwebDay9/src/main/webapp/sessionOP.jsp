<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>
Session Attribute Add key = value <br/>
Key = time value = java.util.Data <br/>
</h3>
<% session.setAttribute("time",new java.util.Date().toString()); %>
<p><a href ="updatetime.jsp">修改time變數</a></p>
<p><a href="removetime.jsp">移除time變數</a></p>
</body>
</html>