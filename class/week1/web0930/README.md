# web0930 — Servlet 表單處理教學專案

以原生 Servlet（JSP 之前的寫法）示範「HTML 表單 → 後端 Servlet → 業務邏輯 → 回傳結果」的完整流程。
共 5 個獨立範例，每個範例都是一支 Servlet 加一頁 HTML 表單。

---

## 1. 環境需求

| 項目 | 版本／說明 |
| --- | --- |
| JDK | Java SE 21 |
| Servlet API | Jakarta Servlet 6.0（`jakarta.servlet.*`） |
| 應用伺服器 | Apache Tomcat 10.1 |
| IDE | Eclipse IDE for Enterprise Java and Web Developer |
| 專案類型 | Dynamic Web Project（Facets 勾選 `jst.web`） |
| Context Path | `web0930` |
| 建置輸出目錄 | `build/classes` |

> Tomcat 9 以下為 `javax.servlet.*`，本專案使用 Tomcat 10.1 的 `jakarta.*`，套件名稱不可混用。

---

## 2. 專案結構

```
web0930/
├── src/main/java/                    ← Servlet 與業務邏輯
│   ├── datamodel/                    ← Model：純邏輯，不依賴 Servlet
│   │   ├── NumberLogic.java          質數、次方、折扣
│   │   └── UserData.java             使用者名單資料來源
│   ├── CheckServlet.java             /check
│   ├── DemoServlet.java              /demo、/srv1
│   ├── MoneyServlet.java             /money
│   ├── PowerServlet.java             /power
│   └── PrimeServlet.java             /prime
├── src/main/webapp/                  ← Web 內容（對外 URL 的根）
│   ├── META-INF/
│   │   └── MANIFEST.MF
│   ├── WEB-INF/
│   │   └── web.xml                   僅設定 welcome-file-list，Servlet 全靠註解對映
│   ├── demo.html                     表單頁 → /demo
│   ├── userform.html                 表單頁 → /check
│   ├── numberform.html               表單頁 → /prime
│   ├── powerform.html                表單頁 → /power
│   └── money.html                    表單頁 → /money
├── build/classes/                    ← 編譯結果（Eclipse 自動產生）
├── .classpath / .project             ← Eclipse 中繼資料
└── README.md
```

### 設計慣例

- **Servlet 一律用註解對映**：`@WebServlet("/xxx")`，`web.xml` 不寫 `<servlet>`。
- **業務邏輯全部放 `datamodel`**：Servlet 只負責收參數、呼叫邏輯、輸出結果。
- **回應一定先設定編碼**：`response.setContentType("text/html;charset=UTF-8")`，避免中文亂碼。
- **POST 先設請求編碼**：`request.setCharacterEncoding("UTF-8")`，再呼叫 `getParameter()`。

---

## 3. URL 對照表

| 功能 | 表單頁 | 方法 | Servlet URL 對映 | 參數 | 回傳範例 |
| --- | --- | --- | --- | --- | --- |
| 取得/傳送資料 | `demo.html` | POST | `/demo`、`/srv1` | `username`、`num` | `doPost DemoServlet 數字值:5 名字:王小明` |
| 名字比對 | `userform.html` | POST | `/check` | `user` | `找到名字:mary` / `沒有找到名字:tom` |
| 質數判斷 | `numberform.html` | POST | `/prime` | `num` | `質數:13` / `不是質數:10` |
| 次方計算 | `powerform.html` | POST | `/power` | `base`、`power` | `2^5=32` |
| 金額折扣 | `money.html` | POST | `/money` | `money` | `12000=10200` |

完整網址前綴：`http://localhost:8080/web0930/`

---

## 4. 部署與執行

1. 匯入專案到 Eclipse，確認 Project Facets 的 Runtime 勾選 **Apache Tomcat v10.1**。
2. 右鍵專案 → **Run As → Run on Server** → 選擇 Tomcat 10.1。
3. 伺服器啟動後開啟：`http://localhost:8080/web0930/demo.html`

### 快速測試（不開瀏覽器）

```bash
# DemoServlet（POST）
curl -X POST "http://localhost:8080/web0930/demo" -d "username=王小明&num=5"

# PrimeServlet（POST）
curl -X POST "http://localhost:8080/web0930/prime" -d "num=13"

# PowerServlet（POST）
curl -X POST "http://localhost:8080/web0930/power" -d "base=2&power=5"

# MoneyServlet（POST）
curl -X POST "http://localhost:8080/web0930/money" -d "money=12000"

# CheckServlet（POST）
curl -X POST "http://localhost:8080/web0930/check" -d "user=mary"

# DemoServlet 直接以 GET 存取（/srv1 為別名）
curl "http://localhost:8080/web0930/srv1"
```

---

## 5. 處理流程說明

所有 POST 表單的流程皆相同：

```
瀏覽器
  │  method="post" 送出 application/x-www-form-urlencoded
  ▼
Tomcat 依 @WebServlet 的 urlPatterns 找到對應 Servlet
  │
  ▼
HttpServlet.service() 依 HTTP method 分派
  ├─ GET  → doGet()
  └─ POST → doPost()
                │
                ├─ 1. request.setCharacterEncoding("UTF-8")
                ├─ 2. response.setContentType("text/html;charset=UTF-8")
                ├─ 3. request.getParameter("欄位名稱")  取值
                ├─ 4. 轉型 / 檢查是否合法（NumberFormatException）
                ├─ 5. 呼叫 datamodel 的靜態方法做運算
                └─ 6. response.getWriter().append(...) 輸出
  ▼
瀏覽器顯示結果
```

---

## 6. 各範例說明

### 6.1 DemoServlet — `/demo`、`/srv1`

`@WebServlet(urlPatterns = { "/demo", "/srv1" })` 示範**一個 Servlet 對應多個 URL**。
用來說明 `doGet` 與 `doPost` 的差別，以及兩個欄位如何同時送出。

- `demo.html` 以 POST 送出 `username` 與 `num`
- 直接在網址列打 `/srv1` 則會走 `doGet`，輸出 `輸出訊息 :doGet DemoServlet`

### 6.2 CheckServlet — `/check`

從 `UserData.userNames()` 取得不可變的 `List<String>`，用 `contains()` 判斷輸入的名字是否存在。
`List.of(...)` 產生的清單不可修改，適合當作唯讀的測試資料。

- 輸入 `mary` → `找到名字:mary`
- 輸入 `tom` → `沒有找到名字:tom`
- 輸入空字串 → `請輸入名字`（`isBlank()` 可檢查 null、空字串與全空白）

### 6.3 PrimeServlet — `/prime`

`NumberLogic.isPrime()` 以「因數個數 == 2」判定質數。
整理時補上 `n < 2` 的短路判斷，因為 1 以下不是質數，可省去無用的迴圈。

- `num=13` → `質數:13`
- `num=10` → `不是質數:10`
- `num=abc` → `請輸入整數:abc`（`Integer.parseInt` 拋出 `NumberFormatException`）

### 6.4 PowerServlet — `/power`

`NumberLogic.power()` 以連乘計算次方。

- `base=2, power=5` → `2^5=32`
- 任一欄位非整數 → 回傳提示訊息，欄位值以 `append(String)` 輸出避免拋例外

### 6.5 MoneyServlet — `/money`

`NumberLogic.discount()` 依金額區間給不同折扣：

| 金額範圍 | 折扣 |
| --- | --- |
| `money <= 5000` | 不打折（100%） |
| `5001 ~ 10000` | 9 折 |
| `10001 ~ 20000` | 85 折 |
| `> 20000` | 8 折 |

- `money=12000` → `12000=10200`
- `money=3000` → `3000=3000`
- `money=30000` → `30000=24000`

---

## 7. 本次整理內容

程式邏輯與類別名稱、package 結構皆未變動，僅做以下整理：

1. **移除 Eclipse 樣板產物**：移除 `// TODO Auto-generated method stub`、多餘的預設建構式與檔頭空行。
2. **修正中文亂碼風險**：所有 `doGet` / `doPost` 補上 `setContentType("text/html;charset=UTF-8")`；POST 補上 `request.setCharacterEncoding("UTF-8")`（原 `DemoServlet` 以外的 Servlet 回傳中文時會變亂碼）。
3. **補上輸入檢查**：`Integer.parseInt` 包在 `try/catch (NumberFormatException)`，輸入非數字時回傳友善訊息而非拋出 500 錯誤；`CheckServlet` 增加空值檢查。
4. **補上程式邏輯邊界**：`NumberLogic.isPrime()` 加上 `n < 2` 短路判斷。
5. **統一排版**：Tab 縮排、import 分組（`jakarta.*` / `java.*` / 專案類別）、移除多餘括號。
6. **補上 Javadoc**：`datamodel` 的公開方法加上說明註解。

---

## 8. 常見問題

**Q：開啟頁面中文顯示成 `??` 或乱码？**
確認所有 Servlet 都有呼叫 `response.setContentType("text/html;charset=UTF-8")`，且 HTML 檔有 `<meta charset="UTF-8">`。POST 送出中文時還需要 Servlet 有呼叫 `request.setCharacterEncoding("UTF-8")`。

**Q：`ClassNotFoundException` / `javax.servlet` 錯誤？**
本專案使用 Jakarta Servlet 6.0，必須搭配 Tomcat 10.1。若沿用舊的 `javax.servlet.*` 匯入，會找不到類別。

**Q：表單送出後 404？**
檢查 `web.xml` 的 `<display-name>` 是否為 `web0930`，且 action 的 URL 與 `@WebServlet` 的 `urlPatterns` 一致。

**Q：如何新增一個範例？**
1. 在 `src/main/java` 新增 Servlet，標上 `@WebServlet("/xxx")`
2. 在 `src/main/webapp` 新增 HTML 表單，action 指向 `http://localhost:8080/web0930/xxx`
3. 需要運算邏輯就加到 `datamodel` 套件
