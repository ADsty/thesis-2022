package com.petrov.vitaliy.caraccidentapp.domain.models.responses.user

data class VehicleProfileGetResponse(
    val vehicleProfileID: Long,
    val vehicleProfileUserID: Long,
    val vehicleBrand: String,
    val vehicleVin: String,
    val vehicleRegistrationSign: String,
    val vehicleRegistrationCertificate: String,
    val vehicleOwnerFullName: String,
    val vehicleOwnerResidentialAddress: String,
    val vehicleDriverID: Long,
    val vehicleInsurancePolicyID: Long
)