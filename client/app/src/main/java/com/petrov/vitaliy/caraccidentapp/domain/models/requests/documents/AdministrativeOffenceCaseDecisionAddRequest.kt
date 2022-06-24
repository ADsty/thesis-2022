package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceCaseDecisionAddRequest(
    val dateOfFill: Date,
    val timeOfFill: Time,
    val placeOfFill: String,
    val policeOfficerID: Long,
    val culpritID: Long,
    val culpritActualPlaceOfResidence: String,
    val carAccidentEntityID: Long,
    val caseCircumstances: String,
    val caseDecision: String,
    val dateOfReceiving: Date,
    val effectiveDate: Date
)
