<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Attribute OP</title>
</head>
<body>
<h2>
   Session Attribute Add key=value <br/>
   Key=time  value=java.util.Date <br/>
   <% session.setAttribute("time",new java.util.Date().toString()); %>   
   <a href="updatetime.jsp">修改time變數</a>
   <a href="removetime.jsp">移除time變數</a>
</h2>
</body>
</html>