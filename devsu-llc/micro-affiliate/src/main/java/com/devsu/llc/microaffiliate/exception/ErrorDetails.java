package com.devsu.llc.microaffiliate.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private int status;
    private HttpStatus error;
    private String errorCode;
    private String path;
    private StackTraceElement[] errors;
}