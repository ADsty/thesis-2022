package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceCaseDecisionUpdateRequest(
    val documentID: Long,
    val updatedDateOfFill: Date,
    val updatedTimeOfFill: Time,
    val updatedPlaceOfFill: String,
    val updatedCulpritID: Long,
    val updatedCulpritActualPlaceOfResidence: String,
    val updatedCaseCircumstances: String,
    val updatedCaseDecision: String,
    val updatedDateOfReceiving: Date,
    val updatedEffectiveDate: Date,
    val carAccidentEntityID: Long
)
