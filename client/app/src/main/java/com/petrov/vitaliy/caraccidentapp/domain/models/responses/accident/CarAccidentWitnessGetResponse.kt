package com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident

data class CarAccidentWitnessGetResponse(
    val entityID: Long,
    val witnessFullName: String,
    val witnessResidentialAddress: String,
    val witnessPhoneNumber: String
)