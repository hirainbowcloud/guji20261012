# Day 1 — Maven/Gradle + Spring IoC/DI 基礎

## 學習目標
- 理解 Maven/Gradle 建構工具與依賴管理
- 理解 Spring IoC (控制反轉) 與 DI (依賴注入) 核心概念
- 能使用 `@Component` / `@Autowired` / 建構子注入
- 理解 Spring MVC 請求流程，掌握 `@Controller` 與 `@RestController` 的差異與使用時機

---

## 1. Maven 專案結構

```
my-app/
├── pom.xml                    # Maven 設定檔：管理依賴、建構生命週期
├── src/
│   ├── main/
│   │   ├── java/com/example/  # Java 原始碼
│   │   └── resources/         # 設定檔（application.properties 等）
│   └── test/java/com/example/ # 單元測試
```

### 完整 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- Spring Boot Parent：管理版本號、外掛、預設設定 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <java.version>21</java.version>
    </properties>

    <dependencies>
        <!-- spring-boot-starter-web 包含：
             Spring MVC（@RestController）、內嵌 Tomcat、Jackson JSON -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- spring-boot-maven-plugin：打包成可執行 JAR -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

**Maven 指令**：
| 指令 | 用途 |
|------|------|
| `mvn compile` | 編譯原始碼 |
| `mvn test` | 執行測試 |
| `mvn package` | 打包為 JAR/WAR |
| `mvn spring-boot:run` | 直接在 Maven 中啟動 Spring Boot |
| `mvn clean` | 清除 target 目錄 |
| `mvn dependency:tree` | 檢視完整依賴樹（排除衝突時常用） |

---

## 2. Spring IoC 概念

傳統開發中，類別自行建立依賴物件，導致高度耦合：

```java
public class OrderService {
    private EmailService email = new EmailService(); // 問題：OrderService 直接依賴 EmailService 實作
}
```

IoC（控制反轉）將「建立物件」的控制權從類別本身交給 Spring 容器。類別只需要宣告需要什麼依賴，容器會自動注入：

```java
public class OrderService {
    private final EmailService email; // 由容器注入，不需自行 new

    public OrderService(EmailService email) { // 建構子注入
        this.email = email;
    }
}
```

**好處**：
- **鬆耦合**：OrderService 只依賴 EmailService 介面，不依賴實作
- **易測試**：可在測試時注入 Mock 物件
- **易維護**：更換 EmailService 實作時不需修改 OrderService

---

## 3. 完整 Spring Boot 專案範例

### 3.1 啟動類別

```java
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 啟動 Spring Boot，自動掃描同 package 及子 package 的 @Component
        SpringApplication.run(Application.class, args);
    }
}
```

### 3.2 Service 層

```java
package com.example.service;

import org.springframework.stereotype.Service;

// @Service：標記此類別為業務邏輯元件，Spring 會自動掃描並註冊為 Bean
@Service
public class GreetingService {

    /**
     * 根據名稱產生問候訊息
     * @param name 使用者名稱
     * @return 問候字串
     */
    public String greet(String name) {
        // 這裡可以加入複雜的商業邏輯，例如查詢資料庫、呼叫外部 API 等
        return "Hello, " + name + "! 歡迎使用 Spring Boot！";
    }
}
```

### 3.3 Controller 層

```java
package com.example.controller;

import com.example.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestController：標記為 RESTful API 控制器，回傳 JSON 而非頁面
// @Controller 與 @RestController 的差異詳見第 4 章
@RestController
public class HelloController {

    // 建構子注入：Spring 會自動建立 GreetingService 並注入
    // 不需要 @Autowired，單一建構子可省略
    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // @GetMapping("/hello")：處理 HTTP GET 請求，路徑為 /hello
    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "World") String name) {
        // @RequestParam 從 URL 查詢參數取值，如 /hello?name=Alice
        // defaultValue：當參數未提供時使用預設值
        return greetingService.greet(name);
    }
}
```

### 3.4 application.properties

```properties
# 啟動後可在 application.properties 中自訂設定
server.port=8080
```

**流程說明**：
1. `Application.main()` 啟動 Spring Boot
2. `@SpringBootApplication` 觸發元件掃描 → 找到 `GreetingService`、`HelloController`
3. Spring 建立 `GreetingService` 實例（Bean）
4. Spring 建立 `HelloController` 時，透過建構子傳入 `GreetingService`
5. 內嵌 Tomcat 啟動，監聽 8080 port
6. 訪問 `http://localhost:8080/hello?name=Alice` → Controller 接收請求 → Service 處理 → 回傳 `"Hello, Alice! 歡迎使用 Spring Boot！"`

---

## 4. Spring Web：@Controller 與 @RestController

Spring Boot 透過 `spring-boot-starter-web` 整合 Spring MVC，控制器（Controller）負責接收 HTTP 請求並回傳結果。Spring MVC 提供兩種控制器註解：

| 註解 | 回傳內容 | 適用場景 |
|------|---------|---------|
| `@Controller` | 視圖名稱（HTML 頁面） | 傳統多頁面應用（搭配 Thymeleaf 等模板引擎） |
| `@RestController` | 資料（JSON/XML） | RESTful API 後端服務 |

### 4.1 Spring MVC 請求處理流程

```
用戶端（瀏覽器 / App / curl）
    │  HTTP 請求 GET /api/hello?name=Alice
    ▼
DispatcherServlet ← 所有請求的統一入口（Spring MVC 前端控制器）
    │  依 URL 路由到對應的 Controller 方法
    ▼
HelloController            ← 接收請求參數
    │  呼叫
    ▼
GreetingService            ← 處理業務邏輯
    │  回傳結果
    ▼
Controller 回傳
    ├─ @RestController → Jackson 自動將物件轉成 JSON，寫入 HTTP Response Body
    └─ @Controller     → 回傳視圖名稱，Thymeleaf 渲染成 HTML 後回傳
```

### 4.2 @Controller — 回傳統圖（HTML 頁面）

`@Controller` 用於伺服器端渲染（Server-Side Rendering）場景：方法回傳**視圖名稱**字串，由模板引擎轉換為 HTML 頁面。

**依賴**：`pom.xml` 需加入 Thymeleaf：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

**控制器範例**：

```java
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controller：回傳統圖名稱，由模板引擎渲染為 HTML
@Controller
public class PageController {

    // GET /greet → 對應 src/main/resources/templates/greet.html
    // Model 用來傳遞資料給模板（如同前端的變數）
    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "訪客") String name, Model model) {
        model.addAttribute("name", name);  // 將 name 傳給模板，模板中用 ${name} 取值
        return "greet";                   // 回傳視圖名稱 → templates/greet.html
    }
}
```

**對應模板** `src/main/resources/templates/greet.html`：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>問候頁面</title>
</head>
<body>
    <h1 th:text="'Hello, ' + ${name} + '!'">Hello!</h1>
</body>
</html>
```

**特殊技巧**：若 `@Controller` 的**個別方法**想直接回傳資料（而非頁面），可單獨加上 `@ResponseBody`：

```java
@ResponseBody // 單一方法加 @ResponseBody → 忽略視圖解析，直接將回傳值寫入 Response Body
@GetMapping("/ping")
public String ping() {
    return "pong";
}
```

### 4.3 @RestController — 回傳資料（JSON）

`@RestController` 是 RESTful API 最常用的控制器，本質等於：

```java
@RestController
// 等同於
@Controller + @ResponseBody  // 在類別層級套用：所有方法回傳值都直接寫入 Response Body
```

方法回傳的物件會由 **Jackson** 自動轉成 JSON（`spring-boot-starter-web` 已內建 Jackson）：

```java
package com.example.controller;

import com.example.service.GreetingService;
import org.springframework.web.bind.annotation.*;

// @RestController：所有方法回傳值自動轉為 JSON
@RestController
// @RequestMapping("/api")：類別層級設定共同路徑前綴，方法上的路徑會接在後面
@RequestMapping("/api")
public class ApiController {

    private final GreetingService greetingService;

    public ApiController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // 實際路由 = 類別前綴 /api + 方法路徑 /hello
    @GetMapping("/hello")
    public GreetingResponse hello(@RequestParam(defaultValue = "World") String name) {
        // 回傳物件 → Jackson 自動序列化為 JSON
        return new GreetingResponse("Hello, " + name + "!", System.currentTimeMillis());
    }

    // record：不可變的資料载体（Java 16+），API 回傳結構化資料時很實用
    public record GreetingResponse(String message, long timestamp) {}
}
```

實際回應：

```
GET /api/hello?name=Alice
Content-Type: application/json

{"message":"Hello, Alice!","timestamp":1729080000000}
```

### 4.4 常用請求映射註解

| 註解 | HTTP 方法 | 情境 |
|------|----------|------|
| `@GetMapping` | GET | 查詢資源 |
| `@PostMapping` | POST | 新增資源 |
| `@PutMapping` | PUT | 全量更新 |
| `@PatchMapping` | PATCH | 部分更新 |
| `@DeleteMapping` | DELETE | 刪除資源 |

```java
@PostMapping("/users")        // 新增：POST /users
public User create(@RequestBody User user) { ... }

@PutMapping("/users/{id}")     // 更新：PUT /users/1
public User update(@PathVariable Long id, @RequestBody User user) { ... }

@DeleteMapping("/users/{id}")  // 刪除：DELETE /users/1
public void delete(@PathVariable Long id) { ... }
```

### 4.5 常用方法參數註解

| 註解 | 取值來源 | 範例 |
|------|---------|------|
| `@PathVariable` | URL 路徑變數 | `/users/1` → `@PathVariable Long id` |
| `@RequestParam` | 查詢字串參數 | `/search?keyword=java` → `@RequestParam String keyword` |
| `@RequestBody` | Request Body（JSON） | POST body `{"name":"Alice"}` → `@RequestBody User user` |
| `@RequestHeader` | HTTP Header | `@RequestHeader("Content-Type") String contentType` |

對應範例：

```java
// ① @PathVariable：擷取 URL 中的路徑變數，{id} 對應參數名 id
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) { ... }

// ② @RequestParam：擷取查詢參數；required = false 表示可不傳
@GetMapping("/users")
public List<User> search(@RequestParam String keyword,
                         @RequestParam(defaultValue = "10") int limit) { ... }

// ③ @RequestBody：將 JSON 反序列化為 Java 物件（Jackson）；@Valid 觸發欄位驗證
@PostMapping("/users")
public User create(@RequestBody @Valid User user) { ... }
```

### 4.6 @Controller vs @RestController 差異總整理

| 比較項目 | `@Controller` | `@RestController` |
|---------|--------------|-------------------|
| 本質 | Spring MVC 傳統控制器 | `@Controller` + `@ResponseBody` |
| 回傳值 | 視圖名稱（字串）或 `ModelAndView` | 資料物件/字串（自動轉 JSON/XML） |
| `@ResponseBody` | 需個別方法或類別加上 | 已隱含，不需再加 |
| 典型搭配 | Thymeleaf、JSP 模板引擎 | Jackson（JSON）、前端 SPA / 行動 App |
| 應用場景 | 伺服器端渲染 HTML 頁面 | RESTful API 後端服務 |
| Content-Type | `text/html` | `application/json`（預設） |

> **挑選原則**：
> - 做 **API 後端**（前後端分離）→ 用 `@RestController`
> - 做 **傳統多頁面網站**（伺服器渲染 HTML）→ 用 `@Controller`
> - 兩者可共存於同一專案（例如 `page/` 放頁面控制器、`api/` 放 API 控制器）

### 4.7 實戰：完整 CRUD REST API

```java
package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")   // 共同前綴：所有路由皆以 /api/products 開頭
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products → 查詢全部
    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    // GET /api/products/1 → 依 id 查詢
    // 查無資料時回傳 404（透過 ResponseEntity 自訂狀態碼）
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)                       // 找到 → 200 OK + 資料
                .orElse(ResponseEntity.notFound().build());   // 找不到 → 404 空 Body
    }

    // POST /api/products → 新增，回傳 201 Created
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 方法執行成功後回傳 201
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    // PUT /api/products/1 → 全量更新
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    // DELETE /api/products/1 → 刪除，成功回傳 204 No Content
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
```

**Entity 範例**（`com.example.model.Product`）：

```java
package com.example.model;

// 商品 Entity：@RestController 回傳此物件時，Jackson 會自動轉為 JSON
public class Product {

    private Long id;
    private String name;
    private Integer price;

    // 建構子、getter/setter 省略（實際專案可搭配 Lombok @Data 簡化）
}
```

**curl 測試**：

```bash
# 新增
curl -X POST http://localhost:8080/api/products \
     -H "Content-Type: application/json" \
     -d '{"name":"滑鼠","price":599}'

# 查詢全部
curl http://localhost:8080/api/products

# 更新
curl -X PUT http://localhost:8080/api/products/1 \
     -H "Content-Type: application/json" \
     -d '{"name":"機械鍵盤","price":1899}'

# 刪除
curl -X DELETE http://localhost:8080/api/products/1
```

---

## 5. 三種依賴注入方式

Spring 支援三種注入方式，建議優先使用**建構子注入**：

```java
@Component
public class UserService {

    // 方式 1：@Autowired 欄位注入（❌ 不推薦）
    // 優點：寫法簡潔
    // 缺點：難以測試、無法設為 final、破壞封裝
    @Autowired
    private UserRepository userRepository;

    // 方式 2：Setter 注入（⚠ 少用）
    // 優點：可在物件建立後改變依賴
    // 缺點：可變性增加風險，測試時需記得呼叫 setter
    private UserRepository repo;
    @Autowired
    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    // 方式 3：建構子注入（✅ 推薦）
    // 優點：不可變（final）、必填依賴明確、易測試（new 時傳入 Mock）
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

> **原則**：單一建構子可省略 `@Autowired`，Spring 會自動注入。

---

## 6. 註冊 Bean 的方式

| 方式 | 註解 | 適用情境 |
|------|------|---------|
| 元件掃描 | `@Component`, `@Service`, `@Repository`, `@Controller` | 自己寫的類別，Spring 自動掃描 |
| Java Config | `@Configuration` + `@Bean` | 第三方類別（如 DataSource、RestTemplate） |

```java
// 方式 A：@Component 系列 — Spring 自動掃描註冊
@Service                                                  // 業務邏輯層
public class UserService { ... }

@Repository                                               // 資料存取層
public class UserRepository { ... }

@RestController                                           // HTTP API 層
public class UserController { ... }

// 方式 B：@Bean — 手動註冊第三方類別
@Configuration                                            // 標記此類別為設定來源
public class AppConfig {

    @Bean                                                 // 方法回傳值將被註冊為 Bean
    public RestTemplate restTemplate() {
        // RestTemplate 是 Spring 提供的 HTTP 客戶端，用來呼叫其他 REST API
        return new RestTemplate();
    }
}
```

---

## 7. 完整專案啟動與測試

```bash
# 方法一：使用 Maven 直接啟動（最簡單）
mvn spring-boot:run

# 方法二：先打包再執行
mvn clean package
java -jar target/my-app-1.0.0.jar

# 測試 API（開啟另一個終端機）
curl http://localhost:8080/hello
# 輸出：Hello, World! 歡迎使用 Spring Boot！

curl http://localhost:8080/hello?name=Alice
# 輸出：Hello, Alice! 歡迎使用 Spring Boot！
```

---

## 8. 動手練習

1. 從 [start.spring.io](https://start.spring.io/) 產生 Spring Boot 專案（加入 `spring-boot-starter-web`）
2. 將上述完整程式碼貼入對應檔案
3. 執行 `mvn spring-boot:run` 啟動
4. 瀏覽器訪問 `http://localhost:8080/hello?name=你的名字`
5. 練習將 `GreetingService` 改為介面 + 實作，體會鬆耦合的好處
6. 參考第 4 章，建立一個 `ProductController`（`@RestController`）實作 CRUD 四種 HTTP 方法
7. 建立 Thymeleaf 頁面控制器（`@Controller`），比較兩者回傳內容的差異（`text/html` vs `application/json`）

---

## 9. 優化與進階學習

### 實作練習
為了加深對 Spring Boot 基礎的理解，我們提供了完整的實作練習文件，包含 8 個梯度式練習：
- **練習 1**：建立基礎 Spring Boot 應用程式 ⭐
- **練習 2**：建立 Service 層和依賴注入 ⭐⭐
- **練習 3**：多種依賴注入方式比較 ⭐⭐
- **練習 4**：Bean 註冊方式實作 ⭐⭐
- **練習 5**：建立完整的使用者管理 API ⭐⭐⭐
- **練習 6**：整合單元測試 ⭐⭐
- **練習 7**：配置檔使用 ⭐⭐
- **練習 8**：整合 Swagger API 文檔 ⭐⭐⭐

**實作練習文件**：[Spring Boot Day 01 實作練習](springboot-day01-practice.md)

### 學習建議
1. **循序漸進**：按照練習順序完成，先掌握基礎再挑戰進階
2. **動手實作**：不要只看程式碼，務必親自輸入並執行
3. **除錯練習**：故意製造錯誤，學習如何排除問題
4. **擴展功能**：在完成基礎練習後，嘗試加入新功能或優化現有程式碼
5. **撰寫測試**：為自己的程式碼編寫單元測試，確保程式品質

### 常見問題
在學習過程中遇到問題時，可以參考：
- [實作練習 - 常見問題排除](springboot-day01-practice.md#常見問題排除)
- [Spring Boot 官方文件](https://spring.io/projects/spring-boot)
- [Spring Framework 官方文件](https://docs.spring.io/spring-framework/reference/)
