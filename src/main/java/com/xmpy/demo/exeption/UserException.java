package com.xmpy.demo.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// ENUM으로도 강사님이 할 수 있다고 햇는데,, 그건 일단 나중에..
@Getter
public class UserException extends RuntimeException {
    private final HttpStatus status;

    public UserException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
