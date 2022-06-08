package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceCaseProtocolAddRequest(
    val dateOfFill: Date,
    val timeOfFill: Time,
    val placeOfFill: String,
    val policeOfficerID: Long,
    val culpritID: Long,
    val culpritActualPlaceOfResidence: String,
    val carAccidentEntityID: Long,
    val lawViolationInfo: String,
    val caseAdditionalInfo: String,
    val placeAndTimeOfCaseExamination: String,
    val changedPlaceOfCaseExamination: String,
    val explanationsAndRemarksOfProtocol: String
)
