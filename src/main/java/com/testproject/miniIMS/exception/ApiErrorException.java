package com.testproject.miniIMS.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiErrorException extends RuntimeException{
    private HttpStatus status;
    private String message;

}
