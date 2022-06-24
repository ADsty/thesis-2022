package com.petrov.vitaliy.caraccidentapp.domain.usecases.user

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserRegistrationRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository

class RegisterNewUserUseCase(private val usersRepository: UsersRepository) {
    operator fun invoke(userRegistrationRequest: UserRegistrationRequest) =
        usersRepository.register(userRegistrationRequest)
}