package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.requests.UserLoginRequest;
import ru.vitaliy.petrov.server.forms.requests.UserRegistrationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.UserUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.forms.responses.TokenResponse;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.ConfirmationCodeService;
import ru.vitaliy.petrov.server.services.UsersService;

import javax.validation.Valid;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ConfirmationCodeService confirmationCodeService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public TokenResponse registration(@Valid UserRegistrationRequest userRegistrationRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        String jwtToken = usersService.register(userRegistrationRequest);
        return new TokenResponse(jwtToken);
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid UserLoginRequest userLoginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        String jwtToken = usersService.login(userLoginRequest);
        return new TokenResponse(jwtToken);
    }

    @GetMapping("/validate")
    public TokenResponse validate(@RequestHeader("Authorization") String jwtToken){
        String validatedToken = usersService.validateToken(jwtToken);
        return new TokenResponse(validatedToken);
    }

    @PatchMapping("/update-user")
    public TokenResponse updateUser(@RequestHeader("Authorization") String jwtToken, @Valid UserUpdateRequest userUpdateRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
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

    @PatchMapping("/change-state")
    public StringResponse changeUserState(@RequestHeader("Authorization") String jwtToken, String stateName) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        String result = usersService.changeUserState(userID, stateName);
        return new StringResponse(result);
    }

    @GetMapping("/get-confirmation-code")
    public StringResponse getConfirmationCode(String phoneNumber){
        return new StringResponse(confirmationCodeService.setConfirmationCode(phoneNumber));
    }

    @GetMapping("/check-confirmation-code")
    public StringResponse checkConfirmationCode(String phoneNumber, Integer confirmationCode){
        if(confirmationCodeService.checkConfirmationCode(phoneNumber, confirmationCode)){
            return new StringResponse("Код правильный");
        }
        else {
            return new StringResponse("Код неправильный");
        }
    }

}
