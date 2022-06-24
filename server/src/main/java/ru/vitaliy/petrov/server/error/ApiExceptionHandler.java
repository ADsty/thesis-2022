package ru.vitaliy.petrov.server.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleApiRequestException(Exception e) {
        ApiException responseBody;

        e.printStackTrace();

        if (e instanceof ru.vitaliy.petrov.server.error.ApiRequestException) {
            ru.vitaliy.petrov.server.error.ApiRequestException apiRequestException = (ru.vitaliy.petrov.server.error.ApiRequestException) e;
            responseBody = new ApiException(apiRequestException.getMessage(), apiRequestException.getHttpStatus());
        } else {
            responseBody = new ApiException(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<>(responseBody, responseBody.getHttpStatus());
    }
}
