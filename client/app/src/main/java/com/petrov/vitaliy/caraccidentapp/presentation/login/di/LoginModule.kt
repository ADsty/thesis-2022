package com.petrov.vitaliy.caraccidentapp.presentation.login.di

import com.petrov.vitaliy.caraccidentapp.data.repository.UsersRepositoryImpl
import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.ConfirmationCodeCheckUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.ConfirmationCodeGetUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.LoginUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.UpdateUserUseCase

class LoginModule {
    private val usersRepository: UsersRepository = UsersRepositoryImpl()
    val loginUseCase = LoginUseCase(usersRepository)
    val confirmationCodeGetUseCase = ConfirmationCodeGetUseCase(usersRepository)
    val confirmationCodeCheckUseCase = ConfirmationCodeCheckUseCase(usersRepository)
    val updateUserUseCase = UpdateUserUseCase(usersRepository)
}