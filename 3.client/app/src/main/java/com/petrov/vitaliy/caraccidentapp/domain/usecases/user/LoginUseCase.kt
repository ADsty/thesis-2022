package com.petrov.vitaliy.caraccidentapp.domain.usecases.user

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserLoginRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository

class LoginUseCase(private val usersRepository: UsersRepository) {
    operator fun invoke(userLoginRequest: UserLoginRequest) =
        usersRepository.login(userLoginRequest)
}