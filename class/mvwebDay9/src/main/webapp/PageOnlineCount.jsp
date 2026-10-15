<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8">
<title>線上人數</title>
</head>
<body>
    <h2>目前線上人數：${applicationScope.onlineCount}</h2>
    <a href="closeSession.jsp">Close Session</a>
</body>
</html>