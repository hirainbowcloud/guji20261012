# mvweb1002 — Webapp 程式製作說明文件

本資料夾為 `mvweb1002` 專案中 `src/main/webapp/` 底下所有程式檔的完整製作說明文件。

## 專案一句話介紹

一個純 **JSP + JSTL**（JavaServerPages + Java Standard Tag Library）的教學專案，
沒有 Servlet、沒有 Controller、沒有 Java 類別，也沒有資料庫程式。
所有邏輯都寫在 `.jsp` 檔案中，用來示範 **JSTL 三大標籤函式庫**的基本應用。

---

## 文件索引

| 編號 | 文件 | 內容摘要 |
|------|------|----------|
| 01 | [01-專案總覽與環境建置.md](./01-專案總覽與環境建置.md) | 技術堆疊、檔案地圖、環境需求、執行與部署方式 |
| 02 | [02-零基礎實作教學.md](./02-零基礎實作教學.md) | 從零開始一步步做出整個專案（JSP 基礎 → JSTL core → fmt → fn） |
| 03 | [03-程式碼逐檔說明.md](./03-程式碼逐檔說明.md) | webapp 下 13 個檔案逐一逐行說明、執行結果、已知問題 |
| 04 | [04-JSTL-語法速查表.md](./04-JSTL-語法速查表.md) | JSTL + EL 語法對照速查表（皆附本專案實例） |

---

## 建議閱讀順序

1. **只看程式在做什麼** → 直接跳到 [03-程式碼逐檔說明.md](./03-程式碼逐檔說明.md)
2. **想從頭學一遍** → 依序讀 `01` → `02` → `03` → `04`
3. **忘了某個標籤怎麼寫** → 查 [04-JSTL-語法速查表.md](./04-JSTL-語法速查表.md)

---

## webapp 檔案清單（13 個）

| 檔案 | 分類 | 說明 |
|------|------|------|
| `WEB-INF/web.xml` | 設定 | Web 應用程式部署描述檔，定義歡迎頁清單 |
| `index.jsp` | JSP | 最小化 JSP 範例，使用 `<%= %>` 輸出 |
| `nameage.html` | HTML | 表單頁，送出 `name` / `age` 參數 |
| `jstl1.jsp` | JSTL core | `c:set` 設定變數 |
| `jstl2.jsp` | JSTL core | `${param.xxx}` 接收表單參數 |
| `jstl3.jsp` | JSTL core | `c:if` 判斷奇偶 |
| `jstl4.jsp` | JSTL core | `c:choose` 多分支等第判斷 |
| `jstlmoney.jsp` | JSTL core | `c:choose` 階梯式折扣判斷 |
| `jstl5.jsp` | JSTL core | `c:forEach` 迴圈（集合與數字區間） |
| `jstl6.jsp` | JSTL core | `c:out` 輸出與預設值 |
| `jstldate.jsp` | JSTL fmt | `fmt:formatDate` 日期格式化 |
| `jstlformat.jsp` | JSTL fmt | `fmt:formatNumber` 數字／貨幣格式化 |
| `jstlfunc.jsp` | JSTL fn | 字串處理函式與 XSS 轉義 |

---

## 維護說明

- 新增教學檔案時，請同步更新本檔案的「文件索引」與「webapp 檔案清單」兩張表。
- 若調整了 `pom.xml` 的 JSTL 版本或 Tomcat 版本，請一併更新 `01` 文件的版本對照表。