package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

import java.sql.Date

data class UserProfileUpdateRequest(
    val updatedFullName: String,
    val updatedDateOfBirth: Date,
    val updatedResidentialAddress: String,
    val updatedPlaceOfWork: String,
    val updatedPositionAtWork: String,
    val updatedWorkPhoneNumber: String,
    val updatedDriverLicenseNumber: String
)
