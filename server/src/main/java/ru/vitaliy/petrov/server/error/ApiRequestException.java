package ru.vitaliy.petrov.server.error;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {
    private HttpStatus httpStatus;

    public ApiRequestException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
