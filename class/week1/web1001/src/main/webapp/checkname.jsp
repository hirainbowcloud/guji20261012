<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="datamodel.UserData" %>
<%@page import="java.util.*" %>
<%
   String name=request.getParameter("user");
   List<String> names=UserData.userNames();
   boolean b=names.contains(name);
   String msg="";
   if(b==true){
	   msg="找到名字"+name;
   }else{
	   msg="沒有找到名字"+name;
   }
   pageContext.setAttribute("found",msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Names List</title>
</head>
<body>
<h1>
   ${found}
</h1>
</body>
</html>