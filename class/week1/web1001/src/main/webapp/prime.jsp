<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="datamodel.NumberLogic" %>
 <%
     String n=request.getParameter("num");
     int value=Integer.parseInt(n);
     boolean b=NumberLogic.isPrime(value);
     String message="";
     if(b==true){
    	     message=n+" 是質數";
     }else{
    	     message=n+" 不是質數"; 
     }
     pageContext.setAttribute("msg",message);
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prime JSP</title>
</head>
<body>
 <h1>
     ${msg}
 </h1>
</body>
</html>