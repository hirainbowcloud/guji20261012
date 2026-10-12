<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // 取得表單參數
    String favoriteAnimal = request.getParameter("favoriteAnimal");
    
    // 空值處理
    if (favoriteAnimal == null || favoriteAnimal.trim().isEmpty()) {
        favoriteAnimal = "未知動物";
    }
    pageContext.setAttribute("name", "可愛的: " + favoriteAnimal); // 正確：以逗號隔開名稱與數值

%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <title>調查結果</title>
</head>
<body>
    <h1>調查結果</h1>
    <p>您最喜歡的動物是：<strong><%= favoriteAnimal %></strong></p>
    <p>您最喜歡的動物是(EL)：<strong>${param.favoriteAnimal}</strong></p>
    <p>您最喜歡的動物是(pageContext)：<strong>${name}</strong></p>
    <p><a href="myAnimal.html">返回重新選擇</a></p>
</body>
</html>