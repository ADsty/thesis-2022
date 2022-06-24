package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

data class VehicleProfileCreationRequest(
    val vehicleBrand: String,
    val vehicleVIN: String,
    val vehicleRegistrationSign: String,
    val vehicleRegistrationCertificate: String,
    val vehicleOwnerFullName: String,
    val vehicleOwnerResidentialAddress: String,
    val vehicleInsurancePolicyNumber: String
)
