package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import java.util.*;

@Service
public class BookService {
	@Autowired
	BookRepository  bookRepository;
	
	public List<Book> findByCategory(String c){
		return bookRepository.findByCategory(c);
	}
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Optional<Book> findById(Long id){
		return bookRepository.findById(id);
	}
	
	public Book create(Book book) {
	        // 業務規則：ISBN 不可重複
	        if (bookRepository.existsByIsbn(book.getIsbn())) {
	            throw new IllegalArgumentException("ISBN 已存在：" + book.getIsbn());
	        }
	        return bookRepository.save(book);
	}
	public Optional<Book> update(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existing -> {
            existing.setTitle(updatedBook.getTitle());
            existing.setAuthor(updatedBook.getAuthor());
            existing.setIsbn(updatedBook.getIsbn());
            existing.setPrice(updatedBook.getPrice());
            existing.setStock(updatedBook.getStock());
            existing.setCategory(updatedBook.getCategory());
            return bookRepository.save(existing);
        });
    }
	public boolean delete(Long id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }

}
