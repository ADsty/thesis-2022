package com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident

data class CarAccidentGetResponse(
    val carAccidentID: Long,
    val carAccidentScene: String,
    val carAccidentTime: String,
    val carAccidentDate: String,
    val carAccidentParticipantsNumber: Int,
    val carAccidentWitnessesNumber: Int
)
