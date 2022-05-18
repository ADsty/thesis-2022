package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.forms.requests.UserLoginRequest;
import ru.vitaliy.petrov.server.forms.requests.UserRegistrationRequest;
import ru.vitaliy.petrov.server.forms.requests.UserUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.forms.responses.TokenResponse;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.UsersService;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public TokenResponse registration(UserRegistrationRequest userRegistrationRequest){
        String jwtToken = usersService.register(userRegistrationRequest);
        return new TokenResponse(jwtToken);
    }

    @PostMapping("/login")
    public TokenResponse login(UserLoginRequest userLoginRequest){
        String jwtToken = usersService.login(userLoginRequest);
        return new TokenResponse(jwtToken);
    }

    @GetMapping("/validate")
    public TokenResponse validate(@RequestHeader("Authorization") String jwtToken){
        String validatedToken = usersService.validateToken(jwtToken);
        return new TokenResponse(validatedToken);
    }

    @PatchMapping("/update-user")
    public TokenResponse updateUser(@RequestHeader("Authorization") String jwtToken, UserUpdateRequest userUpdateRequest){
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        String updatedToken = usersService.updateUser(userUpdateRequest, userID);
        return new TokenResponse(updatedToken);
    }

    @DeleteMapping("/delete-user")
    public StringResponse deleteUser(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        String result = usersService.deleteUser(userID);
        return new StringResponse(result);
    }

}
