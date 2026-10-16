package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController 
{
	//訪問 `http://localhost:8080/greet?name=Alice` → Controller 接收請，@RequestParam的意思
    // GET /greet → 對應 src/main/resources/templates/greet.html
    // Model 用來傳遞資料給模板（如同前端的變數）
    @GetMapping("/greet") //重點!網址 http://localhost:8080/greet的意思
    public String greet(@RequestParam(defaultValue = "訪客") String name, Model model) 
    {
        model.addAttribute("name", name);  // 將 name 傳給模板，模板中用 ${name} 取值
        return "greet";                   // 回傳視圖名稱 → templates/greet.html
    }
    
    @ResponseBody //回傳在body
    @GetMapping("/ping") //一個網址一個方法，spring boot語法規範
    public String ping()
    {
    	 	return "pow"; //回傳字串
    }
}
