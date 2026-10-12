<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String userName = request.getParameter("username");
	String number = request.getParameter("num");
	

	pageContext.setAttribute("usename",userName);
	pageContext.setAttribute("usenumber",number);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catchDemo</title>
</head>
<body>
<h3>
姓名:<%= userName %><br>
數字:<%= number %><br>
</h3>
<hr/>

<h3>
姓名:${param.username}<br>
數字:${param.num}<br>
</h3>

<hr/>
<p>姓名:(pageContext)：<strong>${usename}</strong></p>
<p>數字:(pageContext)：<strong>${usenumber}</strong></p>
</body>
</html>