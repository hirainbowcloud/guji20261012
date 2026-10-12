<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="datamodel.ExponentLogic" %>   
 <%
 
 String strNum = request.getParameter("money");
 int intNum = Integer.parseInt(strNum);
 double[] getDiscount = ExponentLogic.discount(intNum);
 pageContext.setAttribute("Discount",getDiscount[0]);
 
 double rate = getDiscount[1];
 String rateText = "不打折";
 if(rate == 0.9)
 {
	 rateText = "9折";
 }
 else if(rate == 0.85)
 {
	 rateText = "85折";
			 
 }
 else if(rate == 0.8)
 {
	 rateText = "8折";
			 
 }
 
 pageContext.setAttribute("Rate",rateText);
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>折扣: ${Rate}</h3>
<h3>最後金額: ${Discount}</h3>
<h3>原始價格: ${param.money}</h3>

<table width="50%" border="1">
     <tr style="${Rate == '不打折' ? 'background-color:lightblue;' : ''}"><td>* money &lt;= 5000 </td><td>不打折</td></tr>
     <tr style="${Rate == '9折' ? 'background-color:lightblue;' : ''}"><td>* 5001 &lt;= money &lt;= 10000</td><td>打 9 折</td></tr>
     <tr style="${Rate == '85折' ? 'background-color:lightblue;' : ''}"><td>* 10001 &lt;= money &lt;= 20000</td><td>打 85 折 </td></tr>
     <tr style="${Rate == '8折' ? 'background-color:lightblue;' : ''}"><td>* 20001 &lt;= money </td><td>打 8 折 </td></tr>
  </table> 
</body>
</html>