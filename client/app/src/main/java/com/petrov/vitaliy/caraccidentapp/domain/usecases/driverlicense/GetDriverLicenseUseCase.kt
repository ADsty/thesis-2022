package com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense

import com.petrov.vitaliy.caraccidentapp.domain.repository.DriverLicenseRepository

class GetDriverLicenseUseCase(private val driverLicenseRepository: DriverLicenseRepository) {
    operator fun invoke(jwtToken: String) = driverLicenseRepository.getDriverLicense(jwtToken)
}