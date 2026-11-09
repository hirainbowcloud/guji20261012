# Apache Tomcat 10 安裝與設定完全指南

> **文件版本：** 2.0  
> **適用平台：** Windows 10 / 11  
> **適用版本：** Tomcat 10.1.x（Jakarta EE 10）、Tomcat 11.x（Jakarta EE 11）  
> **JDK 需求：** Java 17 以上（Tomcat 10.1）、Java 21 以上（Tomcat 11）

> ⚠️ **重要提醒：** Tomcat 10 起已從 `javax.*` 套件全面切換為 `jakarta.*`。
> 舊有 Java EE 應用（`javax.*`）**無法直接**部署至 Tomcat 10/11，需先進行遷移或改用 Tomcat 9。

---

## 目錄

1. [版本與相容性速覽](#1-版本與相容性速覽)
2. [安裝前置作業（JDK）](#2-安裝前置作業jdk)
3. [下載與安裝 Tomcat](#3-下載與安裝-tomcat)
4. [設定環境變數](#4-設定環境變數)
5. [啟動與停止 Tomcat](#5-啟動與停止-tomcat)
6. [安裝為 Windows 服務](#6-安裝為-windows-服務)
7. [設定管理介面（Manager App）](#7-設定管理介面manager-app)
8. [部署 Web 應用程式（.war）](#8-部署-web-應用程式war)
9. [常用設定調校](#9-常用設定調校)
10. [HTTPS / TLS 設定](#10-https--tls-設定)
11. [Jakarta EE 遷移指南](#11-jakarta-ee-遷移指南)
12. [目錄結構說明](#12-目錄結構說明)
13. [常見問題 FAQ](#13-常見問題-faq)
14. [安全性建議](#14-安全性建議)

---

## 1. 版本與相容性速覽

| Tomcat 版本 | 規格實作 | 命名空間 | 最低 JDK | 建議使用情境 |
|---|---|---|---|---|
| **11.x** | Jakarta EE 11 | `jakarta.*` | Java 21 | 新專案、前瞻技術 |
| **10.1.x** ✅ | Jakarta EE 10 | `jakarta.*` | Java 17 | 推薦主流選擇 |
| 9.0.x | Java EE 8 | `javax.*` | Java 8 | 舊有 javax.* 應用維護 |
| 8.5.x | Java EE 7 | `javax.*` | Java 7 | ⛔ 已終止支援，請升級 |

### 選擇建議

- **新開發專案** → Tomcat 10.1.x 或 11.x（搭配 Jakarta EE 10/11）
- **維護既有 Java EE 應用** → Tomcat 9.0.x（維持 `javax.*`，減少改動）
- **計劃遷移** → 先用 Migration Tool 轉換 WAR，再部署至 Tomcat 10.1.x

---

## 2. 安裝前置作業（JDK）

### 2.1 確認已安裝 JDK

開啟 PowerShell，執行：

```powershell
java -version
javac -version
```

**預期輸出（以 JDK 21 為例）：**

```
java version "21.0.3" 2024-04-16 LTS
Java(TM) SE Runtime Environment (build 21.0.3+7-LTS)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.3+7-LTS, mixed mode)
```

### 2.2 下載 JDK（若未安裝）

| 發行版 | 下載網址 | 說明 |
|---|---|---|
| Eclipse Temurin (OpenJDK) | https://adoptium.net | 推薦，免費開源 |
| Oracle JDK | https://www.oracle.com/java/ | 需接受授權條款 |
| Microsoft Build of OpenJDK | https://aka.ms/download-jdk | Azure 部署適用 |

> **建議：** 開發環境使用 Eclipse Temurin JDK 21 LTS，與 Tomcat 10.1 / 11 完全相容。

### 2.3 設定 JAVA_HOME

1. 搜尋「**編輯系統環境變數**」→ 「環境變數」
2. **系統變數** → 「新增」：

   | 變數名稱 | 值（範例） |
   |---|---|
   | `JAVA_HOME` | `C:\Program Files\Eclipse Adoptium\jdk-21.0.3.9-hotspot` |

3. 編輯 `Path`，加入 `%JAVA_HOME%\bin`
4. 開新終端機確認：

```powershell
$env:JAVA_HOME
java -version
```

---

## 3. 下載與安裝 Tomcat

### 3.1 下載

前往 [https://tomcat.apache.org/](https://tomcat.apache.org/)，選擇對應版本：

- 點選左側 **Tomcat 10** → **10.1.x**
- 下載 **Binary Distributions → Core → zip**

### 3.2 解壓縮

```powershell
# 建議安裝路徑（避免空格與中文路徑）
Expand-Archive -Path "$HOME\Downloads\apache-tomcat-10.1.x.zip" -DestinationPath "C:\"

# 重新命名為簡短路徑（選擇性）
Rename-Item "C:\apache-tomcat-10.1.x" "C:\tomcat10"
```

### 3.3 驗證目錄結構

```
C:\tomcat10\
├── bin\          ← 啟動/停止腳本
├── conf\         ← 設定檔（server.xml, web.xml...）
├── lib\          ← Tomcat 核心 JAR
├── logs\         ← 執行日誌
├── temp\         ← 暫存檔
├── webapps\      ← 部署目錄
│   ├── ROOT\     ← 預設根應用（http://localhost:8080/）
│   ├── manager\  ← 管理介面
│   └── examples\ ← 範例應用
└── work\         ← JSP 編譯暫存
```

---

## 4. 設定環境變數

### 4.1 系統環境變數

| 變數名稱 | 建議值 |
|---|---|
| `JAVA_HOME` | `C:\Program Files\Eclipse Adoptium\jdk-21.0.3.9-hotspot` |
| `CATALINA_HOME` | `C:\tomcat10` |
| `CATALINA_BASE` | 同 `CATALINA_HOME`（多實例時可分離） |

新增後，編輯 `Path` 加入 `%CATALINA_HOME%\bin`。

### 4.2 建立 setenv.bat（JVM 調校）

在 `C:\tomcat10\bin\` 建立 `setenv.bat`（若不存在）：

```bat
@echo off
rem JVM 記憶體設定（依伺服器規格調整）
set CATALINA_OPTS=-Xms512m -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200

rem 時區設定（避免日誌時間錯誤）
set CATALINA_OPTS=%CATALINA_OPTS% -Duser.timezone=Asia/Taipei

rem 編碼設定
set CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF-8
```

> **說明：** `setenv.bat` 會在 `startup.bat` 執行時自動載入，無需修改 `catalina.bat`。

---

## 5. 啟動與停止 Tomcat

### 5.1 直接啟動（開發用）

```powershell
cd C:\tomcat10\bin
.\startup.bat
```

啟動後開啟瀏覽器：[http://localhost:8080/](http://localhost:8080/)

若看到 Tomcat 貓咪歡迎頁面，即表示安裝成功。

### 5.2 停止 Tomcat

```powershell
cd C:\tomcat10\bin
.\shutdown.bat
```

### 5.3 查看即時日誌

```powershell
# 追蹤主日誌（Tomcat 啟動後另開終端機）
Get-Content "C:\tomcat10\logs\catalina.out" -Wait -Tail 50
```

---

## 6. 安裝為 Windows 服務

以系統管理員身份開啟 PowerShell：

```powershell
cd C:\tomcat10\bin

# 安裝服務（預設服務名稱：Tomcat10）
.\service.bat install

# 啟動服務
Start-Service -Name "Tomcat10"

# 停止服務
Stop-Service -Name "Tomcat10"

# 設定為開機自動啟動
Set-Service -Name "Tomcat10" -StartupType Automatic

# 移除服務（若需要）
.\service.bat remove
```

或使用圖形介面工具 `tomcat10w.exe`（位於 `bin\`）進行服務管理與 JVM 設定。

> **建議：** 正式環境以 Windows 服務方式執行，確保系統重啟後自動恢復服務。

---

## 7. 設定管理介面（Manager App）

> ⚠️ **安全警告：** Manager App 擁有部署/解除部署應用的權限，**正式環境嚴禁對外開放**。

### 7.1 新增管理帳號

編輯 `C:\tomcat10\conf\tomcat-users.xml`：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
              version="1.0">

  <!-- 角色定義 -->
  <role rolename="manager-gui"/>     <!-- Manager 網頁介面 -->
  <role rolename="manager-script"/>  <!-- Manager REST API -->
  <role rolename="admin-gui"/>       <!-- Host Manager 介面 -->

  <!-- 管理帳號（僅限開發環境，正式環境請移除） -->
  <user username="admin"
        password="Change_Me_Str0ng!"
        roles="manager-gui,manager-script,admin-gui"/>

</tomcat-users>
```

### 7.2 開放本機以外的 IP 存取（開發環境）

預設 Manager App 只允許 `127.0.0.1` 存取。若需從其他 IP 存取，編輯：

`C:\tomcat10\webapps\manager\META-INF\context.xml`

```xml
<Context antiResourceLocking="false" privileged="true">
  <CookieProcessor className="org.apache.tomcat.util.http.Rfc6265CookieProcessor"
                   sameSiteCookies="strict" />
  <!-- 原始設定僅允許 127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1 -->
  <!-- 開發環境：允許內網 192.168.x.x（正式環境務必還原或移除） -->
  <Valve className="org.apache.catalina.valves.RemoteAddrValve"
         allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1|192\.168\.\d+\.\d+"/>
</Context>
```

重啟 Tomcat 後，前往 [http://localhost:8080/manager/html](http://localhost:8080/manager/html) 登入。

---

## 8. 部署 Web 應用程式（.war）

### 方法一：手動複製（熱部署）

```powershell
# 複製 WAR 到 webapps 目錄
Copy-Item "yourapp.war" -Destination "C:\tomcat10\webapps\"
```

Tomcat 偵測到 WAR 後會自動展開並部署，無需重啟。
瀏覽：[http://localhost:8080/yourapp/](http://localhost:8080/yourapp/)

### 方法二：Manager REST API

```powershell
# 部署（Tomcat Manager API）
$cred = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("admin:Change_Me_Str0ng!"))
Invoke-WebRequest -Uri "http://localhost:8080/manager/text/deploy?path=/yourapp&update=true" `
    -Method PUT `
    -Headers @{ Authorization = "Basic $cred" } `
    -InFile "yourapp.war"
```

### 方法三：Manager 網頁介面

1. 前往 [http://localhost:8080/manager/html](http://localhost:8080/manager/html)
2. 登入管理帳號
3. 找到「WAR file to deploy」區塊
4. 選擇 WAR 檔案並按「Deploy」

### 部署根應用（ROOT）

將應用部署至根路徑（`http://localhost:8080/`）：

```powershell
# 移除原有 ROOT 應用，再複製
Remove-Item "C:\tomcat10\webapps\ROOT" -Recurse -Force
Copy-Item "yourapp.war" -Destination "C:\tomcat10\webapps\ROOT.war"
```

---

## 9. 常用設定調校

### 9.1 變更 HTTP 連接埠

編輯 `C:\tomcat10\conf\server.xml`，找到 Connector 標籤：

```xml
<!-- 將 8080 改為所需連接埠，例如 80 或 9090 -->
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443"
           maxThreads="200"
           minSpareThreads="10"
           acceptCount="100"
           URIEncoding="UTF-8" />
```

### 9.2 調整執行緒與連線數

```xml
<Connector port="8080" protocol="HTTP/1.1"
           maxThreads="300"        <!-- 最大執行緒數（依 CPU 核心數調整） -->
           minSpareThreads="25"    <!-- 最小閒置執行緒 -->
           acceptCount="200"       <!-- 等待佇列最大長度 -->
           connectionTimeout="20000"
           keepAliveTimeout="5000"
           URIEncoding="UTF-8" />
```

### 9.3 啟用壓縮（Gzip）

```xml
<Connector port="8080" protocol="HTTP/1.1"
           compression="on"
           compressionMinSize="2048"
           compressableMimeType="text/html,text/xml,text/plain,text/css,
                                  application/json,application/javascript" />
```

### 9.4 設定日誌格式（Access Log）

在 `conf/server.xml` 的 `<Host>` 區塊內加入：

```xml
<Valve className="org.apache.catalina.valves.AccessLogValve"
       directory="logs"
       prefix="access_log"
       suffix=".txt"
       pattern="%h %l %u %t &quot;%r&quot; %s %b %D ms"
       rotatable="true" />
```

---

## 10. HTTPS / TLS 設定

### 方法一：自簽憑證（開發環境）

```powershell
# 使用 Java keytool 產生自簽憑證
keytool -genkeypair `
    -alias tomcat `
    -keyalg RSA `
    -keysize 2048 `
    -validity 365 `
    -keystore "C:\tomcat10\conf\keystore.p12" `
    -storetype PKCS12 `
    -storepass changeit `
    -dname "CN=localhost, OU=Dev, O=MyOrg, L=Taipei, ST=Taiwan, C=TW"
```

在 `conf/server.xml` 啟用 HTTPS Connector：

```xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
           maxThreads="150" SSLEnabled="true">
    <UpgradeProtocol className="org.apache.coyote.http2.Http2Protocol" />
    <SSLHostConfig>
        <Certificate certificateKeystoreFile="conf/keystore.p12"
                     certificateKeystorePassword="changeit"
                     type="RSA" />
    </SSLHostConfig>
</Connector>
```
---

## 11. Jakarta EE 遷移指南

### 11.1 javax.* vs jakarta.* 差異

Tomcat 10 起，所有 Servlet / JSP / EL / WebSocket API 套件前綴由 `javax.` 改為 `jakarta.`：

| Java EE（javax.*） | Jakarta EE（jakarta.*） |
|---|---|
| `javax.servlet.Servlet` | `jakarta.servlet.Servlet` |
| `javax.servlet.http.HttpServlet` | `jakarta.servlet.http.HttpServlet` |
| `javax.servlet.jsp.JspPage` | `jakarta.servlet.jsp.JspPage` |
| `javax.websocket.Session` | `jakarta.websocket.Session` |

### 11.2 使用 Apache Migration Tool 轉換 WAR

1. 下載工具：[https://tomcat.apache.org/migration.html](https://tomcat.apache.org/migration.html)

2. 執行轉換：

```powershell
# 下載後解壓，執行轉換
java -jar jakartaee-migration-1.x.x-shaded.jar `
    input-app.war `
    output-app-jakarta.war
```

3. 將轉換後的 WAR 部署至 Tomcat 10/11。

### 11.3 Maven 專案依賴更新

```xml
<!-- 舊版 Java EE（javax.*） -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>

<!-- 新版 Jakarta EE 10（jakarta.*） -->
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>6.0.0</version>
    <scope>provided</scope>
</dependency>
```

---

## 12. 目錄結構說明

| 目錄 / 檔案 | 用途 |
|---|---|
| `bin/startup.bat` | 啟動 Tomcat |
| `bin/shutdown.bat` | 停止 Tomcat |
| `bin/setenv.bat` | 自訂 JVM 參數（需手動建立） |
| `conf/server.xml` | 核心設定（連接埠、Connector、虛擬主機） |
| `conf/web.xml` | 全域 Servlet 設定 |
| `conf/tomcat-users.xml` | 管理帳號與角色設定 |
| `conf/context.xml` | 全域 Context 設定（JNDI、資源） |
| `webapps/` | 應用部署目錄 |
| `logs/catalina.out` | 主要系統日誌 |
| `logs/localhost.YYYY-MM-DD.log` | 應用層錯誤日誌 |
| `logs/access_log.*` | HTTP 存取日誌 |
| `work/` | JSP 編譯後的 .java / .class（可安全刪除） |

---

## 13. 常見問題 FAQ

**Q1：啟動後瀏覽 http://localhost:8080/ 出現「拒絕連線」？**

> - 確認 Tomcat 程序已啟動（查看 `logs/catalina.out` 是否有錯誤）
> - 確認防火牆未封鎖 8080 連接埠
> - 確認沒有其他程序佔用 8080：
>   ```powershell
>   netstat -ano | Select-String ":8080"
>   ```

**Q2：啟動時出現「JAVA_HOME is not set」？**

> - 重新設定系統環境變數 `JAVA_HOME`，並開新終端機重試
> - 或在 `bin/setenv.bat` 明確指定：`set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.3.9-hotspot`

**Q3：應用使用 `javax.*`，部署至 Tomcat 10 後出現 ClassNotFoundException？**

> - Tomcat 10 使用 `jakarta.*`，`javax.servlet.*` 不存在
> - 解決方案：（a）改用 Tomcat 9；（b）用 Migration Tool 轉換 WAR；（c）修改原始碼 import

**Q4：Manager App 出現 403 Access Denied？**

> - 確認 `tomcat-users.xml` 已新增帳號且 role 正確
> - 確認 `webapps/manager/META-INF/context.xml` 的 `RemoteAddrValve` 允許您的 IP

**Q5：中文字元在 URL 出現亂碼？**

> - 確認 `server.xml` Connector 已加入 `URIEncoding="UTF-8"`
> - 確認應用的 `request.setCharacterEncoding("UTF-8")` 已設定

**Q6：部署後修改了 JSP 但沒有生效？**

> - 刪除 `work\` 目錄下對應應用的暫存 class，再重啟 Tomcat
> - 或在 `context.xml` 設定 `reloadable="true"`（僅限開發環境）

---

## 14. 安全性建議

| 項目 | 建議 |
|---|---|
| **移除預設範例應用** | 刪除 `webapps/examples`、`webapps/docs`（正式環境） |
| **停用 AJP Connector** | 若不使用 Apache HTTPD 整合，在 `server.xml` 中註解或移除 AJP Connector |
| **Manager App 帳號** | 正式環境應停用或僅限內網 IP 存取 |
| **強密碼政策** | `tomcat-users.xml` 密碼至少 12 字元，含大小寫、數字、符號 |
| **最新版本** | 定期更新 Tomcat 以修補安全漏洞 |
| **以非 root 執行** | Windows 服務帳號使用最小權限的專屬帳號 |
| **啟用 HTTPS** | 正式環境透過反向代理（Nginx/IIS）強制 HTTPS |
| **禁用目錄瀏覽** | `conf/web.xml` 中 DefaultServlet 的 `listings` 參數設為 `false` |

```xml
<!-- conf/web.xml — 確認此設定為 false -->
<servlet>
    <servlet-name>default</servlet-name>
    <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
    <init-param>
        <param-name>listings</param-name>
        <param-value>false</param-value>  <!-- 禁止目錄列表 -->
    </init-param>
</servlet>
```

---

*文件版本：2.0 | 最後更新：2026-09-28 | 適用 Tomcat 10.1.x / 11.x + JDK 17/21*
