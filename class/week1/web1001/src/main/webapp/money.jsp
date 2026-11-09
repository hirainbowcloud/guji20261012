<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="datamodel.NumberLogic" %>
<%
   String m=request.getParameter("money");
   int money=Integer.parseInt(m);
   int discount=NumberLogic.discount(money);
   pageContext.setAttribute("dismoney",discount);
   pageContext.setAttribute("dismsg",NumberLogic.discountMessage);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Money JSP</title>
</head>
<body>
<h1>
  <table width="50%" border="1">
     <tr><td>* money &lt;= 5000 </td><td>不打折</td></tr>
     <tr><td>* 5001 &lt;= money &lt;= 10000</td><td>打 9 折</td></tr>
     <tr><td>* 10001 &lt;= money &lt;= 20000</td><td>打 85 折 </td></tr>
     <tr><td>* 20001 &lt;= money </td><td>打 8 折 </td></tr>
  </table> 
  <p> 
   原來購買金額:${param.money}     <br/>
   折扣: ${dismsg} <br/>
   打折後金額:${dismoney}
</h1>
</body>
</html>