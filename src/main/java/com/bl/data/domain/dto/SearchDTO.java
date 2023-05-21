package com.bl.data.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

    private String authorName;
    private String authorDetails;
    private String bookName;
    private String publisher;
}
