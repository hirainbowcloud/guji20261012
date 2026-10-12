<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import ="datamodel.ExponentLogic" %>
<%@ page import ="java.util.*" %>  
    
<%
String base = request.getParameter("base");
String power = request.getParameter("exponent");

int b = Integer.parseInt(base);
int exp = Integer.parseInt(power);
long ans = ExponentLogic.powerByLoop(b, exp);
pageContext.setAttribute("GetPowerValue",ans);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catchPower.jsp</title>
</head>
<body>
<h3>
${param.base} ^ ${param.exponent} = ${GetPowerValue}
</h3>
</body>
</html>