package com.petrov.vitaliy.caraccidentapp.presentation.mainpage.di

import com.petrov.vitaliy.caraccidentapp.data.repository.CarAccidentRepositoryImpl
import com.petrov.vitaliy.caraccidentapp.data.repository.UsersRepositoryImpl
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository
import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository
import com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events.GetAllCarAccidentsChangelogsUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events.GetAllOfficerCarAccidentsUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events.GetAllUserCarAccidentsUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.GetUserRoleUseCase

class MainPageModule {
    val usersRepository: UsersRepository = UsersRepositoryImpl()
    val getUserRoleUseCase = GetUserRoleUseCase(usersRepository)
    val carAccidentRepository: CarAccidentRepository = CarAccidentRepositoryImpl()
    val getAllUserCarAccidents = GetAllUserCarAccidentsUseCase(carAccidentRepository)
    val getAllOfficerCarAccidentsUseCase = GetAllOfficerCarAccidentsUseCase(carAccidentRepository)
    val getAllCarAccidentsChangelogsUseCase = GetAllCarAccidentsChangelogsUseCase(carAccidentRepository)
}