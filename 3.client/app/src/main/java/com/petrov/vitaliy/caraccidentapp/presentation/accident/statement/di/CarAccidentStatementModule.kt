package com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.di

import com.petrov.vitaliy.caraccidentapp.data.repository.*
import com.petrov.vitaliy.caraccidentapp.domain.repository.*
import com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events.CreateCarAccidentUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense.GetDriverLicenseUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense.UpdateDriverLicenseUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile.GetUserProfileUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile.UpdateUserProfileUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance.GetVehicleInsurancePolicyUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance.UpdateVehicleInsurancePolicyUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile.GetVehicleProfilesUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile.UpdateVehicleProfileUseCase

class CarAccidentStatementModule {
    private val carAccidentRepository: CarAccidentRepository = CarAccidentRepositoryImpl()
    private val driverLicenseRepository: DriverLicenseRepository = DriverLicenseRepositoryImpl()
    private val userProfileRepository: UserProfileRepository = UserProfileRepositoryImpl()
    private val vehicleInsurancePolicyRepository: VehicleInsurancePolicyRepository = VehicleInsurancePolicyRepositoryImpl()
    private val vehicleProfileRepository: VehicleProfileRepository = VehicleProfileRepositoryImpl()

    val createCarAccidentUseCase = CreateCarAccidentUseCase(carAccidentRepository)
    val getUserProfileUseCase = GetUserProfileUseCase(userProfileRepository)
    val updateUserProfileUseCase = UpdateUserProfileUseCase(userProfileRepository)
    val getDriverLicenseUseCase = GetDriverLicenseUseCase(driverLicenseRepository)
    val updateDriverLicenseUseCase = UpdateDriverLicenseUseCase(driverLicenseRepository)
    val getVehicleInsurancePolicyUseCase = GetVehicleInsurancePolicyUseCase(vehicleInsurancePolicyRepository)
    val updateVehicleInsurancePolicyUseCase = UpdateVehicleInsurancePolicyUseCase(vehicleInsurancePolicyRepository)
    val getVehicleProfilesUseCase = GetVehicleProfilesUseCase(vehicleProfileRepository)
    val updateVehicleProfileUseCase = UpdateVehicleProfileUseCase(vehicleProfileRepository)
}