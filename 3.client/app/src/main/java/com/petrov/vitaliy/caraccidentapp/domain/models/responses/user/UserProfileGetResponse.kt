package com.petrov.vitaliy.caraccidentapp.domain.models.responses.user

data class UserProfileGetResponse(
    val userProfileID: Long,
    val userID: Long,
    val userFullName: String,
    val userDateOfBirth: String,
    val userResidentialAddress: String,
    val userPlaceOfWork: String,
    val userPositionAtWork: String,
    val userWorkPhoneNumber: String,
    val driverLicenseID: Long
)
