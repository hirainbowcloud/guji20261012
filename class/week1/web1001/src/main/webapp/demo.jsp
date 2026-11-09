<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String name=request.getParameter("username");
    String n=request.getParameter("num");
    pageContext.setAttribute("user", name);
    pageContext.setAttribute("number", n);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo JSP</title>
</head>
<body>
<h2 style="color:blue">
    名字:<%= name %>  <br/>
    數字:<%= n %>
</h2>
<hr/>
<h2 style="color:purple">
    名字(EL): ${param.username} <br/>
    數字(EL): ${param.num}
</h2>
<hr/>
<h2 style="color:green">
    名字(pageContext): ${user} <br/>
    數字(pageContext): ${number}
</h2>
</body>
</html>