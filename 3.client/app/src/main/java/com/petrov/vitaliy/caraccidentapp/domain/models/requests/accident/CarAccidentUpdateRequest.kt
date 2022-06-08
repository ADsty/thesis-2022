package com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident

import java.sql.Date
import java.sql.Time

data class CarAccidentUpdateRequest(
    val updatedCarAccidentScene: String,
    val updatedCarAccidentDate: Date,
    val updatedCarAccidentTime: Time
)
