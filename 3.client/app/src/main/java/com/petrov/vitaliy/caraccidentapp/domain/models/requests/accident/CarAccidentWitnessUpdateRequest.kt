package com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident

data class CarAccidentWitnessUpdateRequest(
    val updatedWitnessFullName: String,
    val updatedWitnessResidentialAddress: String,
    val updatedWitnessPhoneNumber: String,
    val witnessID: Long
)
