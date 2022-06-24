package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.userprofile.UserProfileCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.UserProfileUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.UserProfile;

public interface IUserProfileService {

    CreationResponse createNewUserProfile(UserProfileCreationRequest userProfileCreationRequest, Long userID);

    UserProfile getUserProfile(Long userID);

    String updateUserProfile(UserProfileUpdateRequest userProfileUpdateRequest, Long userID);

    String deleteUserProfile(Long userID);
}
