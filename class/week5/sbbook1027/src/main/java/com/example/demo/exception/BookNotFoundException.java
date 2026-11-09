package com.example.demo.exception;


//繼承 RuntimeException：不需要在方法簽名宣告 throws（Unchecked Exception）
public class BookNotFoundException extends RuntimeException {

 public BookNotFoundException(Long id) {
     super("書本不存在，id: " + id);
 }

 public BookNotFoundException(String message) {
     super(message);
 }
}

