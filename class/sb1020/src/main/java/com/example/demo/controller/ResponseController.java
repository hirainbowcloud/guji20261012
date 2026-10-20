package com.example.demo.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/response")
public class ResponseController {
	 // 自訂標頭
    @GetMapping("/custom-headers")
    public ResponseEntity<Map<String, Object>> customHeaders() {    	    
        HttpHeaders headers = new HttpHeaders();
        String chineseValue = URLEncoder.encode("自訂標頭值", StandardCharsets.UTF_8);       
        headers.add("X-Custom-Header", chineseValue); 
        //postman
        String x = URLDecoder.decode("%E8%87%AA%E8%A8%82%E6%A8%99%E9%A0%AD%E5%80%BC");
        System.out.println(x);        
        headers.add("X-Request-Time", LocalDateTime.now().toString());   
        
        Map<String, Object> body = new HashMap<>();
        body.put("message", "帶有自訂標頭的回應");
        body.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(body);
    }
}
