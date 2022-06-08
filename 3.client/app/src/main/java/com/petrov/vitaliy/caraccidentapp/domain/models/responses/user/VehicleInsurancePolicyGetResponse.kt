package com.petrov.vitaliy.caraccidentapp.domain.models.responses.user

data class VehicleInsurancePolicyGetResponse(
    val vehicleInsurancePolicyID: Long,
    val vehicleInsurancePolicyUserID: Long,
    val vehicleInsuranceCompany: String,
    val vehicleInsurancePolicyNumber: String,
    val vehicleInsurancePolicyExpirationDate: String
)