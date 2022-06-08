package com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.DriverLicenseRepository

class CreateDriverLicenseUseCase(private val driverLicenseRepository: DriverLicenseRepository) {
    operator fun invoke(
        jwtToken: String,
        driverLicenseCreationRequest: DriverLicenseCreationRequest
    ) = driverLicenseRepository.createDriverLicense(jwtToken, driverLicenseCreationRequest)
}