package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

import java.sql.Date

data class UserProfileCreationRequest(
    val fullName: String,
    val dateOfBirth: Date,
    val residentialAddress: String,
    val placeOfWork: String,
    val positionAtWork: String,
    val workPhoneNumber: String,
    val driverLicenseNumber: String
)