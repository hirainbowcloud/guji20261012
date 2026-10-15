<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
 Object obj = session.getAttribute("time");
 if(obj == null)
 {
	 out.println("time attribute 為儲存");
 }
 else
 {
	 String d1 = (String)obj;
	 out.println("old time: "+d1);
	 String d2 = new java.util.Date().toString();
	 session.setAttribute("time",d2);
	 out.println("new time: "+d2);
 }
%>
</body>
</html>