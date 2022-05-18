package ru.vitaliy.petrov.server.error;

import org.springframework.http.HttpStatus;

public class UnauthorizedApiException extends ApiRequestException {

    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public UnauthorizedApiException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
