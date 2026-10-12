# WebDay2

## 專案簡介

WebDay2 是一個基於 **Jakarta EE** 的 Java Web 應用程式，使用 **Servlet** 與 **JSP** 技術開發，部署於 **Apache Tomcat 10.1** 伺服器上。本專案為教學示範用途，展示 Servlet 的基本操作與資料處理邏輯。

---

## 技術棧

| 項目 | 版本/技術 |
|------|----------|
| Java | Java SE 21 |
| Servlet | Jakarta EE (Servlet 6.0) |
| 伺服器 | Apache Tomcat v10.1 |
| 視圖技術 | JSP |
| 開發工具 | Eclipse IDE |

---

## 專案結構

```
WebDay2/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── datamodel/          # 資料模型與商業邏輯
│   │   │   │   ├── ExponentLogic.java   # 冪次計算與折扣邏輯
│   │   │   │   ├── NumberLogic.java     # 質數判斷邏輯
│   │   │   │   ├── UserData.java        # 使用者資料
│   │   │   │   └── MemberServlet.java    # 會員驗證 Servlet
│   │   │   ├── DemoServlet.java         # 示範 Servlet
│   │   │   ├── NumberServlet.java       # 質數檢查 Servlet
│   │   │   ├── MoneyServlet.java        # 折扣計算 Servlet
│   │   │   └── ExponentLogicServlet.java # 冪次計算 Servlet
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml              # Web 應用程式部署描述檔
│   │       ├── demo.jsp                 # 示範表單
│   │       ├── number.jsp               # 質數檢查表單
│   │       ├── number2.jsp              # 冪次計算表單
│   │       ├── money.jsp                # 折扣計算表單
│   │       └── member.jsp               # 會員驗證表單
├── build/                               # 編譯輸出目錄
├── .classpath                           # Eclipse 類別路徑設定
└── .project                             # Eclipse 專案設定
```

---

## Servlet 配置方式

本專案使用 **Jakarta EE 6.0（Servlet 6.0）** 的 **註解（Annotation）** 方式配置 Servlet，而非傳統的 `web.xml` XML 配置。

### 註解配置（本專案採用）

```java
@WebServlet("/prime")
public class NumberServlet extends HttpServlet {
    // ...
}
```

### 傳統 XML 配置（Servlet 2.x 風格）

```xml
<servlet>
    <servlet-name>NumberServlet</servlet-name>
    <servlet-class>NumberServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>NumberServlet</servlet-name>
    <url-pattern>/prime</url-pattern>
</servlet-mapping>
```

### 本專案 Servlet 映射總覽

| Servlet 類別 | 註解 | URL 映射 |
|-------------|------|----------|
| `DemoServlet` | `@WebServlet({"/demo","/srv1"})` | `/demo`, `/srv1` |
| `NumberServlet` | `@WebServlet("/prime")` | `/prime` |
| `MoneyServlet` | `@WebServlet("/money")` | `/money` |
| `ExponentLogicServlet` | `@WebServlet("/explogicservlet")` | `/explogicservlet` |
| `MemberServlet` | `@WebServlet("/member")` | `/member` |

> **說明**：從 Servlet 3.0 開始支援註解配置，`web.xml` 中無需再定義 `<servlet>` 與 `<servlet-mapping>`。

---

## 功能模組

### 1. DemoServlet (`/demo`, `/srv1`)
- **功能**：示範基本的 GET 與 POST 請求處理
- **GET 請求**：回傳固定訊息
- **POST 請求**：接收 `username` 與 `num` 參數並回傳
- **對應頁面**：`demo.jsp`

### 2. NumberServlet (`/prime`)
- **功能**：判斷輸入的數字是否為質數
- **處理流程**：接收 `num` 參數 → 呼叫 `NumberLogic.isPrime()` → 回傳結果
- **對應頁面**：`number.jsp`

### 3. MoneyServlet (`/money`)
- **功能**：根據輸入金額計算折扣後金額
- **折扣規則**：
  | 金額範圍 | 折扣率 |
  |---------|--------|
  | ≤ 5,000 | 無折扣 (1.0) |
  | 5,001 ~ 10,000 | 9折 (0.9) |
  | 10,001 ~ 20,000 | 85折 (0.85) |
  | > 20,000 | 8折 (0.8) |
- **對應頁面**：`money.jsp`

### 4. ExponentLogicServlet (`/explogicservlet`)
- **功能**：計算指定底數與指數的冪次
- **處理流程**：接收 `base` 與 `exponent` 參數 → 呼叫 `ExponentLogic.powerByLoop()` → 回傳結果
- **對應頁面**：`number2.jsp`

### 5. MemberServlet (`/member`)
- **功能**：驗證輸入的使用者名稱是否為會員
- **會員名單**：Kiki、Lala、Dannie
- **對應頁面**：`member.jsp`

---

## 部署與執行

### 環境需求
- JDK 21 或以上版本
- Apache Tomcat v10.1

### 部署步驟
1. 將專案匯出為 WAR 檔案，或將 `WebDay2` 資料夾複製到 Tomcat 的 `webapps` 目錄
2. 啟動 Tomcat 伺服器
3. 透過瀏覽器存取各功能頁面

### 存取網址
```
http://localhost:8080/WebDay2/
```

---

## 注意事項

- 本專案為教學用途，部分表單使用絕對路徑（`http://localhost:8080/WebDay2/...`），部署時請根據實際環境調整
- `MoneyServlet` 與 `ExponentLogicServlet` 未對輸入參數進行完整的例外處理，實際應用中應加入輸入驗證
- `MemberServlet` 中的會員名單為硬編碼，實際應用應連接資料庫進行驗證
