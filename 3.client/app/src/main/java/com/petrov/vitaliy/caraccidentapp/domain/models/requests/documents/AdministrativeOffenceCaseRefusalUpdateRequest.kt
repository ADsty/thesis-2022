package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceCaseRefusalUpdateRequest(
    val documentID: Long,
    val updatedDateOfFill: Date,
    val updatedTimeOfFill: Time,
    val updatedPlaceOfFill: String,
    val updatedCarAccidentEstablishedInfo: String,
    val updatedRefusalReason: String,
    val updatedRefusalLawInfo: String,
    val carAccidentEntityID: Long
)
