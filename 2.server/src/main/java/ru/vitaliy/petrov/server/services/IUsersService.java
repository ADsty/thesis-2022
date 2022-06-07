package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.UserLoginRequest;
import ru.vitaliy.petrov.server.forms.requests.UserRegistrationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.UserUpdateRequest;

public interface IUsersService {
    String register(UserRegistrationRequest userRegistrationRequest);

    String login(UserLoginRequest userLoginRequest);

    String updateUser(UserUpdateRequest userUpdateRequest);

    String deleteUser(Long userID);
}
