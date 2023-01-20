package com.bl.data.service.impl;

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
    
    @Override
    public List<BookDTO> fetchAllBooks() {
        return mapBook(bookRepository.findAll());
    }

    @Override
    public BookDTO createBook(BookDTO request) {
        Book book = new Book();
        book.setBookCode(request.getBookCode());
        book.setBookName(request.getBookName());
        book.setPublisher(request.getPublisher());
        book.setAuthor(authorRepository.getReferenceById(request.getAuthorId()));
        bookRepository.save(book);
        return mapBook(List.of(book)).get(0);
    }

    @Override
    public BookDTO getBookById(Integer id) {
        BookDTO bookDTO = null;
        try{
            Book book = bookRepository.findById(id).get();
             bookDTO = mapBook(List.of(book)).get(0);
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
            bookDTO = mapBook(List.of(book)).get(0);
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
            bookDto = mapBook(List.of(book)).get(0);
        }catch (Exception ex){
            throw BookNotFoundException.builder()
                    .message(ex.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }

        return bookDto;
    }

    private List<BookDTO> mapBook(List<Book> books){
        List<BookDTO> bookDTOS = new ArrayList<>();
        books.parallelStream().forEach(
                x-> bookDTOS.add(BookDTO.builder()
                        .bookCode(x.getBookCode())
                        .bookName(x.getBookName())
                        .publisher(x.getPublisher())
                        .author(x.getAuthor())
                        .authorId(null)
                        .build())
        );
        return bookDTOS;
    }
}
