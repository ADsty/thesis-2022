package ru.vitaliy.petrov.server.forms.responses;

import lombok.Data;

@Data
public class TokenResponse {
    private final String jwt;

    public TokenResponse(String jwt) {
        this.jwt = jwt;
    }

}

