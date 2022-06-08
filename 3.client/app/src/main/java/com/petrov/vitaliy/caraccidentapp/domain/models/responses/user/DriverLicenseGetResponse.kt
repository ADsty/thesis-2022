package com.petrov.vitaliy.caraccidentapp.domain.models.responses.user

data class DriverLicenseGetResponse(
    val driverLicenseID: Long,
    val driverLicenseNumber: String,
    val driverLicenseCategory: String,
    val driverLicenseDateOfIssue: String
)
