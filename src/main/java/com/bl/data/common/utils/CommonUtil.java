package com.bl.data.common.utils;

import com.bl.data.domain.Author;
import com.bl.data.domain.Book;
import com.bl.data.domain.dto.AuthorDTO;
import com.bl.data.domain.dto.BookDTO;
import com.bl.data.exception.AuthorProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CommonUtil {

    public List<AuthorDTO> mapAuthor(List<Author> authors){
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authors.stream().forEach(x->authorDTOS.add(
                AuthorDTO.builder()
                        .authorCode(x.getAuthorCode())
                        .authorName(x.getAuthorName())
                        .authorDetails(x.getAuthorDetails())
                        .introLayout(getJsonForString(x.getIntroLayout().replace("\\","").replace("\r","").replace("\n","").replace("\t","")))
                        .books(mapBook(x.getBooks()))
                        .build()
        ));
        return authorDTOS;
    }
    public List<BookDTO> mapBook(List<Book> books){
        List<BookDTO> bookDTOS = new ArrayList<>();
        books.stream().forEach(
                x-> {
                    Author author = x.getAuthor();
                    author.setBooks(Collections.emptyList());
                         bookDTOS.add(BookDTO.builder()
                        .bookCode(x.getBookCode())
                        .bookName(x.getBookName())
                        .publisher(x.getPublisher())
                        .author(mapAuthor(List.of(author)).get(0))
                        .authorId(null)
                        .build());
                });
        return bookDTOS;
    }
    public JsonNode getJsonForString(String jsonString) {
        try {
            return new ObjectMapper().readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw AuthorProcessingException.builder()
                    .message(e.getLocalizedMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
        }
    }
}
