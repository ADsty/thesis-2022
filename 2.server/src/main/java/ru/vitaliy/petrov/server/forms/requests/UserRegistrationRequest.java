package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationRequest {

    @NotNull
    @Size(min = 11, max = 12)
    private final String phoneNumber;

    @NotNull
    @NotEmpty
    private final String password;

    @NotNull
    @NotEmpty
    private final String roleName;

}
