package com.bl.data.controller;

import com.bl.data.domain.dto.BookDTO;
import com.bl.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity fetchAllBooks(){
        return ResponseEntity.ok(bookService.fetchAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity fetchById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PostMapping
    public ResponseEntity createBook(@RequestBody BookDTO request){
        return ResponseEntity.ok(bookService.createBook(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateBook(@PathVariable("id") Integer id, @RequestBody BookDTO request){
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable("id")Integer id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

}
