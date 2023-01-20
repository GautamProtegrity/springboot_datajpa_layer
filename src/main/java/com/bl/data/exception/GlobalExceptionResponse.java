package com.bl.data.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GlobalExceptionResponse {
    private String message;
    private LocalDateTime dateTime;
}
