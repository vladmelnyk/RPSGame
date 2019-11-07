package com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class GameOverException extends RuntimeException {

    public GameOverException(String message) {
        super(message);

    }
}
