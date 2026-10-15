<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Use Time</title>
</head>
<body>
<%
try
{
	int t = (int)(Math.random()*2000)+500;
	Thread.sleep(t);
}
catch(Exception ex)
{
}
%>
<p>hi Use Time JSP</p>
</body>
</html>