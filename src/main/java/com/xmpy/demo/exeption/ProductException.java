package com.xmpy.demo.exeption;

import org.springframework.http.HttpStatus;

public class ProductException extends RuntimeException {

    private final HttpStatus status;

    public ProductException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    // ✅ 이거 추가
    public HttpStatus getStatus() {
        return status;
    }
}