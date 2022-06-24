package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserLoginRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserRegistrationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.TokenResponse

interface UsersRepository {

    fun register(userRegistrationRequest: UserRegistrationRequest): Result<TokenResponse>

    fun login(userLoginRequest: UserLoginRequest): Result<TokenResponse>

    fun getConfirmationCode(phoneNumber: String): Result<StringResponse>

    fun checkConfirmationCode(phoneNumber: String, confirmationCode: Int): Result<StringResponse>

    fun updateUser(phoneNumber: String, password: String): Result<TokenResponse>

    fun getUserRole(jwtToken: String): Result<StringResponse>

    fun changeUserState(jwtToken: String, stateName: String): Result<StringResponse>
}