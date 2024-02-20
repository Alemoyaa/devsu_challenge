package com.devsu.llc.microaccountancy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotEnoughAmountException extends RuntimeException {

    private String errorCode;

    public ResourceNotEnoughAmountException(String message) {
        super(message);
    }

    public ResourceNotEnoughAmountException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
