package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private final String phoneNumber;
    private final String password;
}
