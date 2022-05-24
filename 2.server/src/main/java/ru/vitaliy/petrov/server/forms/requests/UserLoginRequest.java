package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserLoginRequest {

    @NotNull
    @NotEmpty
    private final String phoneNumber;

    @NotNull
    @NotEmpty
    private final String password;
}
