package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

import java.sql.*

data class VehicleInsurancePolicyCreationRequest(
    val vehicleInsuranceCompany: String,
    val vehicleInsurancePolicyNumber: String,
    val vehicleInsurancePolicyExpirationDate: Date
)
