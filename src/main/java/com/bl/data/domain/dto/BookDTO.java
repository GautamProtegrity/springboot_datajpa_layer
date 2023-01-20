package com.bl.data.domain.dto;

import com.bl.data.domain.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    private Integer bookCode;
    private String bookName;
    private String publisher;
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Author author;
    private Integer authorId;

}
