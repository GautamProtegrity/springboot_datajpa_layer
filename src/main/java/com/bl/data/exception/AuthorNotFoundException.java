package com.bl.data.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AuthorNotFoundException extends RuntimeException{

    private String message;
    private LocalDateTime dateTime;
}
