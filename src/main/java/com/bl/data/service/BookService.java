package com.bl.data.service;

import com.bl.data.domain.dto.BookDTO;
import com.bl.data.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    public List<BookDTO> fetchAllBooks();

    public BookDTO createBook(BookDTO request);

    public BookDTO getBookById(Integer id)throws BookNotFoundException;

    public BookDTO updateBook(Integer id, BookDTO request)throws BookNotFoundException;

    public BookDTO deleteBook(Integer id)throws BookNotFoundException;
}
