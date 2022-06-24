package com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.DriverLicenseRepository

class UpdateDriverLicenseUseCase(private val driverLicenseRepository: DriverLicenseRepository) {
    operator fun invoke(jwtToken: String, driverLicenseUpdateRequest: DriverLicenseUpdateRequest) =
        driverLicenseRepository.updateDriverLicense(jwtToken, driverLicenseUpdateRequest)
}