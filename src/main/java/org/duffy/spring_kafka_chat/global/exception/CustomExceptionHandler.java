package org.duffy.spring_kafka_chat.global.exception;

import org.duffy.spring_kafka_chat.global.exception.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception.getCause());
        exception.printStackTrace();
        return ResponseEntity.ok(new ErrorResponse(exception));
    }
}
