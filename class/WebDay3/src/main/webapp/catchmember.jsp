<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="datamodel.UserData" %>
<%@ page import ="java.util.*" %>
    
<%

String name = request.getParameter("username");
List<String> names = UserData.userName();
boolean flag = names.contains(name);
String msg = "";
if(flag)
{
	msg = "Hi! "+name;
}
else
{
	msg = "沒有找到名! "+name;
}

pageContext.setAttribute("GetName",msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catchMember</title>
</head>
<body>

<h3>${GetName}</h3>

</body>
</html>