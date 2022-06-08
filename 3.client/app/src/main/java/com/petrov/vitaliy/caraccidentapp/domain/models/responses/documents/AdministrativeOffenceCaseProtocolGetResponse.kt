package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class AdministrativeOffenceCaseProtocolGetResponse(
    val administrativeOffenceCaseProtocolID: Long,
    val dateOfFill: String,
    val timeOfFill: String,
    val placeOfFill: String,
    val trafficPoliceOfficerID: Long,
    val carAccidentCulpritID: Long,
    val culpritActualPlaceOfResidence: String,
    val carAccidentEntityID: Long,
    val lawViolationInfo: String,
    val caseAdditionalInfo: String,
    val placeAndTimeOfCaseExamination: String,
    val changedPlaceOfCaseExamination: String,
    val explanationsAndRemarksOfProtocol: String
)
