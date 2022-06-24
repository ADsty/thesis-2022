package com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident

data class CarAccidentWitnessAddRequest(
    val entityID: Long,
    val witnessFullName: String,
    val witnessResidentialAddress: String,
    val witnessPhoneNumber: String
)
