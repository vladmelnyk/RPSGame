package com.company.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(final GameNotFoundException e, final WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({GameOverException.class})
    public ResponseEntity<Object> handleBadRequest(final GameOverException e, final WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ChoiceIsInvalidException.class})
    public ResponseEntity<Object> handleBadRequest(final ChoiceIsInvalidException e, final WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleInternalServerException(final RuntimeException e, final WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private static class ErrorResponse {
        private String message;
        private HttpStatus httpStatus;

        public ErrorResponse(String message, HttpStatus httpStatus) {
            this.message = message;
            this.httpStatus = httpStatus;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }
    }
}
