package com.cydeo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseWrapper {
    private boolean success;
    private String message;
    private Integer code;
    private Object data;

    public ResponseWrapper(String message, HttpStatus httpStatus, Object data) {
        this.message = message;
        this.code = httpStatus.value();
        this.data = data;
        this.success =true;
    }

    public ResponseWrapper(String message, HttpStatus httpStatus) {
        this.message = message;
        this.code = httpStatus.value();
        this.success = true;
    }
}
