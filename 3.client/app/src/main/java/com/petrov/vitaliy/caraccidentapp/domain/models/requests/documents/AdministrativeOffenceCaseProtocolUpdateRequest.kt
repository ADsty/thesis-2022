package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceCaseProtocolUpdateRequest(
    val documentID: Long,
    val updatedDateOfFill: Date,
    val updatedTimeOfFill: Time,
    val updatedPlaceOfFill: String,
    val updatedCulpritActualPlaceOfResidence: String,
    val updatedLawViolationInfo: String,
    val updatedCaseAdditionalInfo: String,
    val updatedPlaceAndTimeOfCaseExamination: String,
    val updatedChangedPlaceOfCaseExamination: String,
    val updatedExplanationsAndRemarksOfProtocol: String,
    val carAccidentEntityID: Long
)