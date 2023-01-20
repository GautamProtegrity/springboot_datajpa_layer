package com.bl.data.controller;

import com.bl.data.domain.dto.AuthorDTO;
import com.bl.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @GetMapping
    public ResponseEntity fetchAllAuthor(){
        return ResponseEntity.ok(authorService.fetchAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity fetchById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping
    public ResponseEntity createAuthor(@RequestBody AuthorDTO request){
        return ResponseEntity.ok(authorService.createAuthor(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAuthor(@PathVariable("id") Integer id, @RequestBody AuthorDTO request){
        return ResponseEntity.ok(authorService.updateAuthor(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Integer id){
        return ResponseEntity.ok(authorService.deleteAuthor(id));
    }
}
