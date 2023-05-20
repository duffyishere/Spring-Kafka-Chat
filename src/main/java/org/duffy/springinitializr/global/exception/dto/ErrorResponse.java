package org.duffy.springinitializr.global.exception.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private int code;

    private String message;

    public ErrorResponse(IllegalArgumentException exception) {
        this.code = 400;
        this.message = exception.getMessage();
    }
}
