<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>供應商列表</title>
</head>
<body>
    <h1>供應商列表</h1>
    <p><a href="${pageContext.request.contextPath}/addsupplier.jsp">新增供應商</a></p>
    <c:if test="${not empty deleteSuccess}">
        <p role="status" style="color: green"><c:out value="${deleteSuccess}" /></p>
    </c:if>
    <c:if test="${not empty deleteError}">
        <p role="alert" style="color: red"><c:out value="${deleteError}" /></p>
    </c:if>
    <table border="1">
        <thead>
            <tr>
                <th>供應商編號</th>
                <th>供應商名稱</th>
                <th>街道</th>
                <th>城市</th>
                <th>州／省</th>
                <th>郵遞區號</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty supplierList}">
                <tr><td colspan="7">目前沒有供應商資料</td></tr>
            </c:if>
            <c:forEach var="supplier" items="${supplierList}">
                <c:url var="editUrl" value="/suppliers/edit">
                    <c:param name="supId" value="${supplier.supId}" />
                </c:url>
                <tr onclick="window.location.href=this.querySelector('a').href" style="cursor: pointer" title="點擊此列修改">
                    <td><a href="${editUrl}"><c:out value="${supplier.supId}" /></a></td>
                    <td><c:out value="${supplier.supName}" /></td>
                    <td><c:out value="${supplier.street}" /></td>
                    <td><c:out value="${supplier.city}" /></td>
                    <td><c:out value="${supplier.state}" /></td>
                    <td><c:out value="${supplier.zip}" /></td>
                    <td onclick="event.stopPropagation()">
                        <form action="${pageContext.request.contextPath}/suppliers/delete" method="post"
                              onsubmit="return confirm('確定要刪除這筆供應商資料嗎？');">
                            <input type="hidden" name="supId" value="<c:out value='${supplier.supId}' />">
                            <button type="submit">刪除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
