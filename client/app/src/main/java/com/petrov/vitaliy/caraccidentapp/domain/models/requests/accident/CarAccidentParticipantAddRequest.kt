package com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident

data class CarAccidentParticipantAddRequest(
    val entityID: Long,
    val participantID: Long,
    val vehicleID: Long
)
