package com.bookhaven.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handlerBusinessException(final BusinessException businessException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(businessException.getMessage());
    }
}
