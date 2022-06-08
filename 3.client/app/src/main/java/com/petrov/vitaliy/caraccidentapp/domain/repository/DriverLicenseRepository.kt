package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.DriverLicenseGetResponse

interface DriverLicenseRepository {

    fun createDriverLicense(
        jwtToken: String,
        driverLicenseCreationRequest: DriverLicenseCreationRequest
    ): Result<CreationResponse>

    fun getDriverLicense(jwtToken: String): Result<DriverLicenseGetResponse>

    fun updateDriverLicense(
        jwtToken: String,
        driverLicenseUpdateRequest: DriverLicenseUpdateRequest
    ): Result<StringResponse>

}