package com.bl.data.domain.dto;

import com.bl.data.domain.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
    private String authorIntro;
    private JsonNode introLayout;
    @JsonIgnoreProperties(value = {"author"})
    private List<BookDTO> books;
}
