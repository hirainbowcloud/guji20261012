package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
	//  http://localhost:8080/greet?name=Alice → Controller 接收請求
	// GET /greet → 對應 src/main/resources/templates/greet.html
    // Model 用來傳遞資料給模板（如同前端的變數）
    @GetMapping("/greet")
    public String greet(@RequestParam(defaultValue = "訪客") String name, Model model) {
        model.addAttribute("name", name);  // 將 name 傳給模板，模板中用 ${name} 取值
        return "greet";                   // 回傳視圖名稱 → templates/greet.html
    }
    
    @ResponseBody // 單一方法加 @ResponseBody → 忽略視圖解析，直接將回傳值寫入 Response Body
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
