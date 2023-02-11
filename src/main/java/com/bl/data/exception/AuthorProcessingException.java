package com.bl.data.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthorProcessingException extends RuntimeException{

    private String message;
    private LocalDateTime dateTime;
}
