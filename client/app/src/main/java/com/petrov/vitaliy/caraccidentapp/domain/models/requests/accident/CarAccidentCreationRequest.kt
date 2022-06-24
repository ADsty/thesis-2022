package com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident

import java.sql.Date
import java.sql.Time

data class CarAccidentCreationRequest(
    val carAccidentScene: String,
    val carAccidentDate: Date,
    val carAccidentTime: Time,
    val carAccidentSynchronizedParticipants: List<Long>,
    val participantsVehicles: List<Long>
)
