package com.petrov.vitaliy.caraccidentapp.presentation.registration.di

import com.petrov.vitaliy.caraccidentapp.data.repository.*
import com.petrov.vitaliy.caraccidentapp.domain.repository.*
import com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense.CreateDriverLicenseUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.ConfirmationCodeCheckUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.ConfirmationCodeGetUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.RegisterNewUserUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile.CreateUserProfileUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance.CreateVehicleInsurancePolicyUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile.CreateVehicleProfileUseCase

class RegistrationModule {

    private val usersRepository: UsersRepository = UsersRepositoryImpl()
    private val driverLicenseRepository: DriverLicenseRepository = DriverLicenseRepositoryImpl()
    private val userProfileRepository: UserProfileRepository = UserProfileRepositoryImpl()
    private val vehicleInsurancePolicyRepository: VehicleInsurancePolicyRepository =
        VehicleInsurancePolicyRepositoryImpl()
    private val vehicleProfileRepository: VehicleProfileRepository = VehicleProfileRepositoryImpl()

    val confirmationCodeGetUseCase = ConfirmationCodeGetUseCase(usersRepository)
    val confirmationCodeCheckUseCase = ConfirmationCodeCheckUseCase(usersRepository)
    val registerNewUserUseCase = RegisterNewUserUseCase(usersRepository)
    val createDriverLicenseUseCase = CreateDriverLicenseUseCase(driverLicenseRepository)
    val createUserProfileUseCase = CreateUserProfileUseCase(userProfileRepository)
    val createVehicleInsurancePolicyUseCase =
        CreateVehicleInsurancePolicyUseCase(vehicleInsurancePolicyRepository)
    val createVehicleProfileUseCase = CreateVehicleProfileUseCase(vehicleProfileRepository)
}