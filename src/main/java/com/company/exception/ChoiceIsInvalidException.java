package com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ChoiceIsInvalidException extends RuntimeException {

    public ChoiceIsInvalidException(String message) {
        super(message);
    }
}
