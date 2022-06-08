package com.petrov.vitaliy.caraccidentapp.domain.usecases.user

import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository

class ConfirmationCodeCheckUseCase(private val usersRepository: UsersRepository) {
    operator fun invoke(phoneNumber: String, confirmationCode: Int) =
        usersRepository.checkConfirmationCode(phoneNumber, confirmationCode)
}