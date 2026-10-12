<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>新增供應商</title>
</head>
<body>
    <h1>新增供應商</h1>
    <form action="${pageContext.request.contextPath}/suppliers/insert" method="post" accept-charset="UTF-8">
        <div>
            <label for="supId">供應商編號：</label>
            <input id="supId" name="supId" type="number" min="1" max="2147483647" required>
        </div>
        <div>
            <label for="supName">供應商名稱：</label>
            <input id="supName" name="supName" type="text" maxlength="40" required>
        </div>
        <div>
            <label for="street">街道：</label>
            <input id="street" name="street" type="text" maxlength="40" required>
        </div>
        <div>
            <label for="city">城市：</label>
            <input id="city" name="city" type="text" maxlength="20" required>
        </div>
        <div>
            <label for="state">州／省：</label>
            <input id="state" name="state" type="text" maxlength="2" required>
        </div>
        <div>
            <label for="zip">郵遞區號：</label>
            <input id="zip" name="zip" type="text" maxlength="5">
        </div>
        <button type="submit">新增</button>
    </form>
    <p><a href="${pageContext.request.contextPath}/suppliers">返回供應商列表</a></p>
</body>
</html>
