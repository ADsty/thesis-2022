package com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile

import com.petrov.vitaliy.caraccidentapp.domain.repository.UserProfileRepository

class GetUserProfileUseCase(private val userProfileRepository: UserProfileRepository) {
    operator fun invoke(jwtToken: String) = userProfileRepository.getUserProfile(jwtToken)
}