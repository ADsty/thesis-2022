package com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.UserProfileRepository

class UpdateUserProfileUseCase(private val userProfileRepository: UserProfileRepository) {
    operator fun invoke(jwtToken: String, userProfileUpdateRequest: UserProfileUpdateRequest) =
        userProfileRepository.updateUserProfile(jwtToken, userProfileUpdateRequest)
}