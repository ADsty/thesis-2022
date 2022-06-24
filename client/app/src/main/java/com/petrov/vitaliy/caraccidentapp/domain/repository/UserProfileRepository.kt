package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.UserProfileGetResponse

interface UserProfileRepository {

    fun createUserProfile(
        jwtToken: String,
        userProfileCreationRequest: UserProfileCreationRequest
    ): Result<CreationResponse>

    fun getUserProfile(jwtToken: String): Result<UserProfileGetResponse>

    fun updateUserProfile(
        jwtToken: String,
        userProfileUpdateRequest: UserProfileUpdateRequest
    ): Result<StringResponse>

}