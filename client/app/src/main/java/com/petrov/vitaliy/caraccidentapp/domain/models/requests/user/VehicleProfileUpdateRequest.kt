package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

data class VehicleProfileUpdateRequest(
    val updatedVehicleBrand: String,
    val updatedVehicleVIN: String,
    val updatedVehicleRegistrationSign: String,
    val updatedVehicleRegistrationCertificate: String,
    val updatedVehicleOwnerFullName: String,
    val updatedVehicleOwnerResidentialAddress: String,
    val updatedVehicleInsurancePolicyNumber: String
)
