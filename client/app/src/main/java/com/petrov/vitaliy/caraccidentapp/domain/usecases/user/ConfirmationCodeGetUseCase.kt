package com.petrov.vitaliy.caraccidentapp.domain.usecases.user

import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository

class ConfirmationCodeGetUseCase(private val usersRepository: UsersRepository) {
    operator fun invoke(phoneNumber: String) = usersRepository.getConfirmationCode(phoneNumber)
}