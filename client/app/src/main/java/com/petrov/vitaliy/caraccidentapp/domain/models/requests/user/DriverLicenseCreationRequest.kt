package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

import java.sql.Date

data class DriverLicenseCreationRequest(
    val driverLicenseNumber: String,
    val driverLicenseCategory: String,
    val driverLicenseDateOfIssue: Date
)
