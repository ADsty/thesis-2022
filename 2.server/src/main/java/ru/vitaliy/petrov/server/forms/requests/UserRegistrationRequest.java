package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private final String phoneNumber;
    private final String password;
    private final String roleName;
}
