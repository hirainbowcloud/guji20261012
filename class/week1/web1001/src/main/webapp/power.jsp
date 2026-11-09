<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="datamodel.NumberLogic" %>
<%
   String base=request.getParameter("base");
   String power=request.getParameter("power");
   int b=Integer.parseInt(base);
   int p=Integer.parseInt(power);
   int value=NumberLogic.power(b, p);
   pageContext.setAttribute("powervalue",value);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Power JSP</title>
</head>
<body>
<h1>
    ${param.base} ^ ${param.power} = ${powervalue}
</h1>
</body>
</html>