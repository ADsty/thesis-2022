package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceCaseInvestigationAddRequest(
    val dateOfFill: Date,
    val timeOfFill: Time,
    val placeOfFill: String,
    val policeOfficerID: Long,
    val carAccidentEntityID: Long,
    val investigationReason: String,
    val lawViolationInfo: String
)
