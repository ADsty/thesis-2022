package ru.vitaliy.petrov.server.forms.requests.userprofile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRequest {

    @NotNull
    @NotEmpty
    private final String phoneNumber;

    @NotNull
    @NotEmpty
    private final String password;
}
