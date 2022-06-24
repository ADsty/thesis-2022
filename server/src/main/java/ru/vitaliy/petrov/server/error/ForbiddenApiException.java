package ru.vitaliy.petrov.server.error;

import org.springframework.http.HttpStatus;

public class ForbiddenApiException extends ApiRequestException {
    private final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

    public ForbiddenApiException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
