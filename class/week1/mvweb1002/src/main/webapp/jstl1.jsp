<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Variables</title>
</head>
<body>
<%-- 設定簡單變數 --%>
<core:set var="name" value="張小明" />

<h1>
    名字: ${name}
</h1>
</body>
</html>