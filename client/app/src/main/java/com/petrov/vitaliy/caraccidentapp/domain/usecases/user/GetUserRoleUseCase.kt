package com.petrov.vitaliy.caraccidentapp.domain.usecases.user

import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository

class GetUserRoleUseCase(private val usersRepository: UsersRepository) {
    operator fun invoke(jwtToken: String) = usersRepository.getUserRole(jwtToken)
}