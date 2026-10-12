<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="errorHandler.jsp"%>
<%-- 使用 JavaBean 來處理遊戲邏輯 --%>
<jsp:useBean id="Fortune" class="domain.GuessGameLogic" scope="session">
  <% Fortune.initialize(1, 10); %>
</jsp:useBean>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <title>猜數字結果</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; text-align: center; }
        .hint { color: #FF6B6B; font-weight: bold; }
        .chances { color: #4ECDC4; }
        .btn { padding: 10px 20px; background-color: #45B7D1; color: white; text-decoration: none; border-radius: 5px; margin: 10px; }
    </style>
</head>
<body>
    <%
        // 取得用戶猜測的數字
        String guess = request.getParameter("number");
        int guessNum = Integer.parseInt(guess);
        
        // 檢查是否猜中
        if (Fortune.isCorrectGuess(guessNum)) {
            // 猜中了，清除 session 並轉向成功頁面
            session.invalidate();
    %>
            <jsp:forward page="bingo.jsp" />
    <%
        } else {
            // 沒猜中，顯示提示和剩餘機會
            int remainder = Fortune.getRemainder();
            if (remainder > 0) {
    %>
                <h2>很抱歉，數字 <%= guess %> 不正確</h2>
                <p class="chances">您還有 <strong><%= remainder %></strong> 次機會</p>
                <p class="hint">💡 提示：<%= Fortune.getHint() %></p>
                <a href="guess.html" class="btn">再試一次</a>
    <%
            } else {
                // 沒有機會了，清除 session 並轉向失敗頁面
                session.invalidate();
    %>
                <jsp:forward page="noChances.jsp" />
    <%
            }
        }
    %>
</body>
</html>