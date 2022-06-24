package com.petrov.vitaliy.caraccidentapp.domain.usecases.user

import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository

class ChangeUserStateUseCase(private val usersRepository: UsersRepository) {
    operator fun invoke(jwtToken: String, stateName: String) =
        usersRepository.changeUserState(jwtToken, stateName)
}