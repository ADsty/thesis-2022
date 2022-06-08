package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

import java.sql.Date

data class DriverLicenseUpdateRequest(
    val updatedDriverLicenseNumber: String,
    val updatedDriverLicenseCategory: String,
    val updatedDriverLicenseDateOfIssue: Date
)
