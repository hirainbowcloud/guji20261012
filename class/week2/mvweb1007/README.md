# Supplier 供應商管理系統 — 製作說明文件

本專案是一個以 **MVC 架構** 實作的供應商（Supplier）CRUD 網頁應用程式：
瀏覽器 → Controller（Servlet）→ DAO（JDBC）→ MySQL，最後由 JSP + JSTL 畫面呈現。

---

## 1. 技術棧總覽

| 項目 | 技術 / 版本 | 說明 |
|---|---|---|
| 語言 | Java 21 | `maven.compiler.source/target = 21`（`pom.xml:18-19`） |
| 建置 | Maven（war 打包） | `finalName = mvweb1007`（`pom.xml:49`） |
| Web 框架 | Jakarta Servlet 6.0 | `jakarta.servlet-api`，scope=provided，僅編譯期使用，執行期由容器提供（`pom.xml:24-29`） |
| 畫面 | JSP + JSTL 3.0 | `jakarta.servlet.jsp.jstl-api 3.0.0` + GlassFish 實作 `3.0.1`（`pom.xml:37-46`），taglib URI 為 `jakarta.tags.core` |
| 資料存取 | JDBC（原生 SQL） | `mysql-connector-j 8.4.0`（`pom.xml:31-35`） |
| 資料庫 | MySQL 8.0，DB `classicmodels` | 帳號 `root` / 密碼 `1234`，表 `SUPPLIERS` |
| 容器 | Apache Tomcat 10.1（支援 Jakarta EE） | 應用部署於 `webapps/mvweb1007`，HTTP port 8080 |

---

## 2. 系統架構（MVC 分層）

```
瀏覽器 (JSP + JSTL 畫面)
   │ HTTP 請求 (/SupplierList, /SupplierAdd, /SupplierUpdate, /SupplierDelete)
   ▼
Controller 層 ── controller/ (HttpServlet)
   │ 呼叫 DAO、放 request attribute、forward 轉送
   ▼
DAO 層 ── dao/SupplierDAO (介面) + dao/SupplierDAOImpl (JDBC 實作)
   ▼
MySQL ── classicmodels.SUPPLIERS
```

- **Model**：`Supplier`（Entity）+ DAO 回傳的 `List<Supplier>`
- **View**：`viewsupplier.jsp`、`addsupplier.jsp`、`updatesupplier.jsp`、`deletesupplier.jsp`
- **Controller**：`SupplierController`、`SupplierAddController`、`SupplierUpdateController`、`SupplierDeleteController`

### 專案目錄結構

```
mvweb1007/
├── pom.xml
└── src/main/
    ├── java/
    │   ├── dao/
    │   │   ├── Supplier.java          # Entity（POJO）
    │   │   ├── SupplierDAO.java       # DAO 介面（CRUD 契約）
    │   │   └── SupplierDAOImpl.java   # DAO 實作（JDBC + MySQL）
    │   └── controller/
    │       ├── SupplierController.java        # 查詢列表
    │       ├── SupplierAddController.java      # 新增
    │       ├── SupplierUpdateController.java   # 修改
    │       └── SupplierDeleteController.java   # 刪除
    └── webapp/
        ├── viewsupplier.jsp     # 列表畫面（JSTL）
        ├── addsupplier.jsp      # 新增表單
        ├── updatesupplier.jsp   # 修改表單
        ├── deletesupplier.jsp   # 刪除表單
        ├── index.jsp
        └── WEB-INF/web.xml
```

---

## 3. 資料庫準備

資料表 `classicmodels.SUPPLIERS` 結構如下（欄位與 Entity 一一對應）：

| 欄位 | 型別 | 說明 |
|---|---|---|
| SUP_ID | INT（PK） | 對應 `supId` |
| SUP_NAME | VARCHAR(40) NOT NULL | 對應 `supName` |
| STREET | VARCHAR(40) NOT NULL | 對應 `street` |
| CITY | VARCHAR(20) NOT NULL | 對應 `city` |
| STATE | CHAR(2) NOT NULL | 對應 `state`（如 `CA`） |
| ZIP | CHAR(5) | 對應 `zip`，可為空 |

參考建表 SQL：

```sql
CREATE DATABASE IF NOT EXISTS classicmodels
  DEFAULT CHARACTER SET utf8mb4;
USE classicmodels;
CREATE TABLE IF NOT EXISTS SUPPLIERS (
  SUP_ID   INT PRIMARY KEY,
  SUP_NAME VARCHAR(40) NOT NULL,
  STREET   VARCHAR(40) NOT NULL,
  CITY     VARCHAR(20) NOT NULL,
  STATE    CHAR(2)     NOT NULL,
  ZIP      CHAR(5)
);
```

---

## 4. 各層程式說明與技術重點

### 4.1 Entity 層 — `dao/Supplier.java`

標準 POJO：無參數建構子（JDBC 逐欄 `set` 需要）+ 全參數建構子 + 全部 getter/setter。

**技術重點**

1. **欄位封裝為 `private`**：外部只能經由 getter/setter 存取；JSP 的 EL 運算式（`${s.supName}`）也是透過 `getSupName()` 解析，不受影響。
2. **覆寫 `toString()`**：除錯時可直接印出物件內容，不必逐欄輸出。
3. **命名對應**：Java 欄位 `supId` ↔ 資料庫欄位 `SUP_ID`，在 DAO 的 `mapRow()` 內顯式對應，避免大小寫/底線差異造成錯誤。

### 4.2 DAO 介面 — `dao/SupplierDAO.java`

```java
public interface SupplierDAO {
    int insert(Supplier supplier);   // 回傳影響筆數
    int update(Supplier supplier);
    int delete(int supId);
    Supplier findById(int supId);    // 查無資料回傳 null
    List<Supplier> findAll();
}
```

**技術重點**

1. **介面與實作分離**：Controller 只依賴 `SupplierDAO` 介面（`new SupplierDAOImpl()` 可隨時替換實作，如改用連線池或 mock 測試），符合依賴反轉原則。
2. **回傳值設計**：`insert/update/delete` 回傳 `int`（JDBC `executeUpdate()` 的影響筆數），呼叫端可判斷是否真的寫入；`findById` 查無資料回傳 `null`，呼叫端需做 null 檢查。
3. **主鍵型別一致**：`delete(int supId)`、`findById(int supId)` 都以 `int` 主鍵為參數，與 Entity 的 `supId` 型別一致。

### 4.3 DAO 實作 — `dao/SupplierDAOImpl.java`

**連線設定**

```java
private static final String URL = "jdbc:mysql://localhost:3306/classicmodels"
    + "?useSSL=false&serverTimezone=Asia/Taipei"
    + "&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
private static final String USER = "root";
private static final String PASSWORD = "1234";
```

**技術重點**

1. **`Class.forName("com.mysql.cj.jdbc.Driver")`**：在 Tomcat 這類 Web 容器中，`DriverManager` 不一定能自動載入 `WEB-INF/lib` 裡的驅動（會報 `No suitable driver found`），顯式載入驅動類別是最穩妥的做法，錯誤時包裝成 `SQLException("找不到 MySQL Driver")` 明確提示。
2. **一律使用 `PreparedStatement`（含 `?` 佔位符）**：防止 SQL Injection，`findAll()` 也不例外（全專案統一風格）；JDBC 會正確處理字串跳脫與型別轉換。
3. **try-with-resources**：`Connection`、`PreparedStatement`、`ResultSet` 都在 try 括號內宣告，方法結束（即使發生例外）自動關閉，避免連線洩漏。
4. **`mapRow(ResultSet)` 集中對應**：`findById` 與 `findAll` 共用同一個欄位映射方法，新增欄位時只需改一處。
5. **例外包裝為 `RuntimeException`**：介面方法沒有宣告 `throws SQLException`，實作層捕捉後包裝成 `RuntimeException("insert Supplier 失敗: ...", e)` 往上丟，保留原始堆疊，同時讓 Controller 可以用統一的 `try/catch (RuntimeException)` 處理。
6. **JDBC URL 參數**：`useSSL=false`（本機開發免憑證）、`serverTimezone=Asia/Taipei`（避免時區警告/日期偏差）、`allowPublicKeyRetrieval=true`（MySQL 8 預設認證插件連線需要）、`characterEncoding=UTF-8`（中文讀寫正常）。

### 4.4 Controller 層 — `controller/`（4 支 Servlet）

| Servlet | URL | GET | POST |
|---|---|---|---|
| `SupplierController` | `/SupplierList` | `findAll()` → 轉送列表 | 同 GET |
| `SupplierAddController` | `/SupplierAdd` | 顯示新增表單 | 驗證 → `insert()` → `findAll()` → 轉送列表 |
| `SupplierUpdateController` | `/SupplierUpdate` | 顯示修改表單 | 驗證 → `update()` → `findAll()` → 轉送列表 |
| `SupplierDeleteController` | `/SupplierDelete` | 顯示刪除表單 | 驗證 → `delete()` → `findAll()` → 轉送列表 |

以新增為例（`SupplierAddController.java`）：

```java
request.setCharacterEncoding("UTF-8");          // 先設編碼再讀參數
int supId = Integer.parseInt(request.getParameter("supId").trim());
Supplier s = new Supplier(supId, supName.trim(), ...);
dao.insert(s);                                  // 寫入
request.setAttribute("supplierList", dao.findAll()); // 重讀全部
request.getRequestDispatcher("/viewsupplier.jsp").forward(request, response);
```

**技術重點**

1. **`@WebServlet` 註解映射**：`urlPatterns` 取代 `web.xml` 的 `<servlet-mapping>`，新增 Servlet 不需改設定檔。
2. **`request.setCharacterEncoding("UTF-8")` 必須在讀參數之前**：POST 表單中文若出現亂碼，九成是少了這一行（本專案 4 支 Controller 的 `doGet/doPost` 皆已補上）。
3. **輸入驗證流程統一**：`supId` 非整數 → 回表單頁顯示 `SupId 必須為整數`；`supName` 空白 → 顯示 `SupName 不得為空`。驗證失敗用 `return` 中斷，不會走到 DAO。
4. **DAO 呼叫包 `try/catch (RuntimeException)`**：例如主鍵重複導致 `insert` 失敗時，把錯誤訊息放回 `error` attribute 並回到表單頁，而不是直接噴 500 錯誤頁。
5. **`forward` 而非 `redirect`**：`request.getRequestDispatcher(...).forward()` 是伺服器內部轉送，`request` attribute（`supplierList`、`error`）會保留；若用 `sendRedirect`，attribute 會遺失，列表頁將顯示「查無資料」。
6. **`serialVersionUID`**：`HttpServlet` 實作 `Serializable`，宣告固定版本號可消除編譯警告。

### 4.5 View 層 — JSP + JSTL

以 `viewsupplier.jsp` 為例：

```jsp
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:forEach var="s" items="${supplierList}">
<tr>
    <td>${s.supId}</td>
    <td><c:out value="${s.supName}" /></td>
    ...
</tr>
</c:forEach>
<c:if test="${empty supplierList}">
<tr><td colspan="6">查無資料</td></tr>
</c:if>
```

**技術重點**

1. **page 指令雙編碼設定**：`contentType`（回應給瀏覽器）與 `pageEncoding`（JSP 原始檔編碼）都要是 UTF-8，中文才不會亂碼。
2. **JSTL 取代 Scriptlet**：畫面中沒有任何 `<% %>` Java 程式碼，迴圈用 `<c:forEach>`、空集合用 `<c:if test="${empty ...}">` 顯示「查無資料」。
3. **EL 屬性解析**：`${s.supName}` 會呼叫 `getSupName()`；`${empty supplierList}` 同時涵蓋 `null` 與空集合兩種情況。
4. **`<c:out>` 防 XSS**：使用者輸入的文字（如公司名含 `<`、`&`）經由 `<c:out>` 會自動做 HTML 跳脫，避免惡意腳本被執行。
5. **路徑寫法**：表單 `action` 與連結使用 `${pageContext.request.contextPath}/SupplierAdd`，部署的 context path 改名（如從 `mvweb1007` 改成別的）也不會壞掉。

---

## 5. 建置與部署

### 5.1 編譯（無 Maven 指令時可用 javac）

```bat
javac -encoding UTF-8 ^
  -cp "jakarta.servlet-api-6.0.0.jar;mysql-connector-j-8.4.0.jar" ^
  -d target\classes ^
  src\main\java\dao\*.java src\main\java\controller\*.java
```

> `-encoding UTF-8` 不可省略，否則中文註解在 Windows 預設編碼（MS950）下會編譯失敗。

### 5.2 部署到 Tomcat 10.1

1. 在 `webapps/` 下建立 `mvweb1007/` 目錄。
2. 複製 `src/main/webapp/` 全部內容（含 `WEB-INF/web.xml`、JSP）。
3. 編譯產出的 `.class` 放入 `WEB-INF/classes/`（保持 `dao/`、`controller/` 套件目錄）。
4. `WEB-INF/lib/` 放入三個 jar：`mysql-connector-j-8.4.0.jar`、`jakarta.servlet.jsp.jstl-api-3.0.0.jar`、`jakarta.servlet.jsp.jstl-3.0.1.jar`（`jakarta.servlet-api` 不放，容器已提供）。
5. 啟動 Tomcat（port 8080），開啟以下網址：

| 功能 | 網址 |
|---|---|
| 查詢列表 | `http://localhost:8080/mvweb1007/SupplierList` |
| 新增 | `http://localhost:8080/mvweb1007/SupplierAdd` |
| 修改 | `http://localhost:8080/mvweb1007/SupplierUpdate` |
| 刪除 | `http://localhost:8080/mvweb1007/SupplierDelete` |

### 5.3 功能驗證結果（已實測）

- `GET /SupplierList`：HTTP 200，`Content-Type: text/html;charset=UTF-8`，表格正確顯示 5 筆資料（49、101、150、456、927）。
- `POST /SupplierAdd`：新增測試資料後自動轉送列表，新資料出現在表格中。
- 新增/修改/刪除後皆重新 `findAll()` 轉送 `viewsupplier.jsp`，畫面即時反映最新資料。

---

## 6. 常見問題排解

| 症狀 | 原因 | 解法 |
|---|---|---|
| `No suitable driver found for jdbc:mysql://...` | 容器內 `DriverManager` 未自動載入驅動 | `getConnection()` 內加 `Class.forName("com.mysql.cj.jdbc.Driver")`（本專案已處理） |
| 表單中文變亂碼 | 讀參數前未設定請求編碼 | `doPost` 第一行加 `request.setCharacterEncoding("UTF-8")`（本專案已處理） |
| 新增後列表顯示「查無資料」 | 用 `sendRedirect` 導致 attribute 遺失 | 改用 `forward` 轉送（本專案已採用） |
| JSTL 標籤無效 / 500 錯誤 | taglib URI 錯誤或缺實作 jar | URI 必須是 `jakarta.tags.core`（Jakarta），且 `WEB-INF/lib` 要有 GlassFish JSTL 實作 jar |
| 改了 class 沒生效 | Tomcat 未重新載入應用 | 碰觸 `WEB-INF/web.xml`（更新修改時間）觸發 reload，或重啟 Tomcat |
| `STATE` 寫入被截斷 | 該欄為 `CHAR(2)` | 只能存 2 碼（如 `CA`、`TW`） |

---

## 7. 後續可優化方向

1. 連線設定外移：將 URL/帳密移到 `db.properties`，避免寫死在程式碼。
2. 連線池：改用 HikariCP 或 Tomcat JNDI DataSource，提升併發效能。
3. PRG 模式：新增/修改/刪除成功後用 redirect 取代 forward，避免重新整理造成重複送出。
4. 分頁查詢：資料量大時 `findAll()` 改成分頁（`LIMIT/OFFSET`）。
5. 刪除前確認：刪除表單加確認機制，避免誤刪。
