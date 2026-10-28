package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookService;
import com.example.demo.dto.*;
import com.example.demo.model.Book;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService service;
   
    @GetMapping
    public List<Book> getAll(@RequestParam(required = false) String category) {
        if (category != null) {
            return service.findByCategory(category);
        }
        return service.findAll();
    }
    // GET /api/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//    @PostMapping
//    public ResponseEntity<Book> create(@RequestBody Book book) {
//        Book saved = service.create(book);
//        URI location = URI.create("/api/books/" + saved.getId());
//        return ResponseEntity.created(location).body(saved);
//    }
    @PostMapping
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookCreateRequest req) {
        // ① @Valid 觸發驗證（@NotBlank、@Pattern、@Positive...）
        //    驗證失敗 → 拋 MethodArgumentNotValidException → GlobalExceptionHandler 處理
        // ② 請求 DTO → Entity 轉換
        Book book = new Book(
                req.getTitle(), req.getAuthor(), req.getIsbn(),
                req.getPrice(), req.getStock(), req.getCategory());
        // ③ 委派 Service
        Book saved = service.create(book);
        // ④ 建立 Location header 指向新資源
        URI location = URI.create("/api/books/" + saved.getId());
        // ⑤ Entity → 回應 DTO，回傳 201 Created
        return ResponseEntity.created(location).body(BookResponse.from(saved));
    }
    
//    @PutMapping("/{id}")
//    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
//        return service.update(id, book)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateRequest req) {
        Book updatedData = new Book(
                req.getTitle(), req.getAuthor(), req.getIsbn(),
                req.getPrice(), req.getStock(), req.getCategory());
        return service.update(id, updatedData)
                .map(BookResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        Optional<Book> found=service.findById(id);	
        if (service.delete(id)) {
            return ResponseEntity.ok(found.get());
        }
        return ResponseEntity.notFound().build();
    }
} 
