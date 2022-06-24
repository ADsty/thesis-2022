package com.petrov.vitaliy.caraccidentapp.domain.usecases.user

import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository

class UpdateUserUseCase(private val usersRepository: UsersRepository) {
    operator fun invoke(phoneNumber: String, password: String) =
        usersRepository.updateUser(phoneNumber, password)
}