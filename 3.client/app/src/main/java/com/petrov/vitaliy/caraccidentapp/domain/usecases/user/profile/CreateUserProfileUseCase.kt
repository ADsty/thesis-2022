package com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.UserProfileRepository

class CreateUserProfileUseCase(private val userProfileRepository: UserProfileRepository) {
    operator fun invoke(jwtToken: String, userProfileCreationRequest: UserProfileCreationRequest) =
        userProfileRepository.createUserProfile(jwtToken, userProfileCreationRequest)
}