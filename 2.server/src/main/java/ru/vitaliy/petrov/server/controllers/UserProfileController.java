package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.forms.requests.UserProfileCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.UserProfileUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.UserProfile;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.UserProfileService;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/user-profile/create")
    public CreationResponse createNewUserProfile(UserProfileCreationRequest userProfileCreationRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return userProfileService.createNewUserProfile(userProfileCreationRequest, userID);
    }

    @GetMapping("/user-profile/get")
    public UserProfile getUserProfile(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return userProfileService.getUserProfile(userID);
    }

    @PatchMapping("/user-profile/update")
    public StringResponse updateUserProfile(UserProfileUpdateRequest userProfileUpdateRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(userProfileService.updateUserProfile(userProfileUpdateRequest, userID));
    }

    @DeleteMapping("/user-profile/delete")
    public StringResponse deleteUserProfile(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(userProfileService.deleteUserProfile(userID));
    }

}
