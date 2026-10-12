<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "datamodel.NumberLogic" %>
 
 <%
 	String strNum = request.getParameter("num");
 	int intNum = Integer.parseInt(strNum);
 	boolean flag = NumberLogic.isPrime(intNum);
 	String message = "";
 	if(flag)
 	{
 		message += strNum+"是質數"; 		
 	}
 	else
 	{
 		message += strNum+"不是質數"; 
 	}
 	pageContext.setAttribute("checkNumberMsg",message);
 %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prime</title>
</head>
<body>
<h3>${checkNumberMsg}</h3>
</body>
</html>