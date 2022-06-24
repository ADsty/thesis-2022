package ru.vitaliy.petrov.server.forms.responses;

import lombok.Data;

@Data
public class StringResponse {
    private final String response;

    public StringResponse(String response) {
        this.response = response;
    }
}
