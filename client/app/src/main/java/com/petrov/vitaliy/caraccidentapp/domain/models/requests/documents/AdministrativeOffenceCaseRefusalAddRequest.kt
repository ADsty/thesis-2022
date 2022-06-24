package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceCaseRefusalAddRequest(
    val dateOfFill: Date,
    val timeOfFill: Time,
    val placeOfFill: String,
    val policeOfficerID: Long,
    val carAccidentInfoSender: Long,
    val carAccidentEstablishedInfo: String,
    val refusalReason: String,
    val refusalLawInfo: String,
    val carAccidentEntityID: Long
)
