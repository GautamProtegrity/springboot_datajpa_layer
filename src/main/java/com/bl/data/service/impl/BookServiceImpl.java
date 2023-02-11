package com.bl.data.service.impl;

import com.bl.data.common.utils.CommonUtil;
import com.bl.data.domain.Book;
import com.bl.data.domain.dto.BookDTO;
import com.bl.data.exception.BookNotFoundException;
import com.bl.data.repositories.AuthorRepository;
import com.bl.data.repositories.BookRepository;
import com.bl.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CommonUtil commonUtil;
    
    @Override
    public List<BookDTO> fetchAllBooks() {
        return commonUtil.mapBook(bookRepository.findAll());
    }

    @Override
    public BookDTO createBook(BookDTO request) {
        Book book = new Book();
        book.setBookCode(request.getBookCode());
        book.setBookName(request.getBookName());
        book.setPublisher(request.getPublisher());
        book.setAuthor(authorRepository.getReferenceById(request.getAuthorId()));
        bookRepository.save(book);
        return commonUtil.mapBook(List.of(book)).get(0);
    }

    @Override
    public BookDTO getBookById(Integer id) {
        BookDTO bookDTO = null;
        try{
            Book book = bookRepository.findById(id).get();
             bookDTO = commonUtil.mapBook(List.of(book)).get(0);
        }catch(Exception ex){
            throw BookNotFoundException.builder()
                    .message(ex.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }
        return bookDTO;
    }

    @Override
    public BookDTO updateBook(Integer id, BookDTO request) {
        BookDTO bookDTO = null;
        try{

            Book book = bookRepository.getReferenceById(id);
            book.setBookName(request.getBookName());
            book.setPublisher(request.getPublisher());
            bookRepository.saveAndFlush(book);
            bookDTO = commonUtil.mapBook(List.of(book)).get(0);
        }catch(Exception ex){
            throw BookNotFoundException.builder()
                    .message(ex.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }
        return bookDTO;
    }

    @Override
    public BookDTO deleteBook(Integer id) {
        BookDTO bookDto = null;
        try{
            Book book = bookRepository.getReferenceById(id);
            bookRepository.delete(book);
            bookDto = commonUtil.mapBook(List.of(book)).get(0);
        }catch (Exception ex){
            throw BookNotFoundException.builder()
                    .message(ex.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }

        return bookDto;
    }
}
