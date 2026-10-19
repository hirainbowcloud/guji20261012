package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.GreetingService;

//@RestController 註解的作用：
//1. 標記此類別為 RESTful API 控制器
//2. 所有方法的回傳值會被自動序列化為 JSON（不是 View 名稱）
//3. 等同於 @Controller + @ResponseBody
@RestController

public class HelloController {

	 // 建構子注入（推薦方式）
    // Spring 會自動建立 GreetingService 實例並注入
    // 不需要 @Autowired 註解，因為只有一個建構子
    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
        System.out.println("[Controller] HelloController 已初始化，Service 已注入");
    }

    // @GetMapping("/hello") = @RequestMapping(value = "/hello", method = GET)
    // 處理 HTTP GET 請求，路徑為 /hello
    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "World") String name) {
        // @RequestParam 從 URL 查詢參數取值
        // 範例：/hello?name=Alice
        // defaultValue 是當參數未提供時的預設值
        System.out.println("[Controller] 收到請求，name = " + name);
        
        String result = greetingService.greet(name);
        System.out.println("[Controller] 回傳結果: " + result);
        return result;
    }	
}
