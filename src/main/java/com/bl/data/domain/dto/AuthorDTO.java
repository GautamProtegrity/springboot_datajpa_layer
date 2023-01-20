package com.bl.data.domain.dto;

import com.bl.data.domain.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {
    private Integer authorCode;
    private String authorName;
    private String authorDetails;
    private List<Book> books;
}
