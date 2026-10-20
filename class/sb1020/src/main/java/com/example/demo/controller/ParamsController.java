package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/params")
public class ParamsController {
	// @RequestParam：從 URL 查詢參數取值
    // 範例：GET /api/params/query?name=Alice&age=25
	// @RequestParam 是 Spring Boot 中用來**接收 URL 問號後面的「查詢參數（Query Parameters）」**的註解
    @GetMapping("/query")
    public ResponseEntity<Map<String, Object>> requestParam(
            @RequestParam(defaultValue = "未提供") String name,
            @RequestParam(defaultValue = "0") int age,
            @RequestParam(required = false) String email) {
        
        return ResponseEntity.ok(Map.of(
            "type", "RequestParam",
            "name", name,
            "age", age,
            "email", email != null ? email : "未提供",
            "description", "從 URL 查詢參數取值"
        ));
    }
    
 // @RequestBody：從 HTTP Request Body 取值
    // 範例：POST /api/params/body
    @PostMapping("/body")
    public ResponseEntity<Map<String, Object>> requestBody(@RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(Map.of(
            "type", "RequestBody",
            "receivedData", body,
            "description", "從 HTTP Request Body 取值"
        ));
    }
    
    // @RequestHeader：從 HTTP 標頭取值
    // 範例：GET /api/params/header（需帶 Authorization 標頭）
    @GetMapping("/header")
    public ResponseEntity<Map<String, Object>> requestHeader(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader(value = "User-Agent", required = false) String userAgent) {
        
        return ResponseEntity.ok(Map.of(
            "type", "RequestHeader",
            "authorization", authorization,
            "userAgent", userAgent != null ? userAgent : "未提供",
            "description", "從 HTTP 標頭取值"
        ));
    }
}