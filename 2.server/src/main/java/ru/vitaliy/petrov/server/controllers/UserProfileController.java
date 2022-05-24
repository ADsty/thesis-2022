package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.requests.userprofile.UserProfileCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.UserProfileUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.UserProfile;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.UserProfileService;

import javax.validation.Valid;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/user-profile/create")
    public CreationResponse createNewUserProfile(@Valid UserProfileCreationRequest userProfileCreationRequest, BindingResult bindingResult, @RequestHeader("Authorization") String jwtToken) {
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        System.out.println(userID);
        return userProfileService.createNewUserProfile(userProfileCreationRequest, userID);
    }

    @GetMapping("/user-profile/get")
    public UserProfile getUserProfile(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return userProfileService.getUserProfile(userID);
    }

    @PatchMapping("/user-profile/update")
    public StringResponse updateUserProfile(@Valid UserProfileUpdateRequest userProfileUpdateRequest, BindingResult bindingResult, @RequestHeader("Authorization") String jwtToken) {
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(userProfileService.updateUserProfile(userProfileUpdateRequest, userID));
    }

    @DeleteMapping("/user-profile/delete")
    public StringResponse deleteUserProfile(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(userProfileService.deleteUserProfile(userID));
    }

}
