package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

import java.sql.Date

data class VehicleInsurancePolicyUpdateRequest(
    val updatedVehicleInsuranceCompany: String,
    val updatedVehicleInsurancePolicyNumber: String,
    val updatedVehicleInsurancePolicyExpirationDate: Date
)
