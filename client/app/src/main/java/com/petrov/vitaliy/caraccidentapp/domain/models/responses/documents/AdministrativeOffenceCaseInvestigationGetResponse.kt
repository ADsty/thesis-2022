package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class AdministrativeOffenceCaseInvestigationGetResponse(
    val administrativeOffenceCaseInvestigationID: Long,
    val dateOfFill: String,
    val timeOfFill: String,
    val placeOfFill: String,
    val trafficPoliceOfficerID: Long,
    val carAccidentEntityID: Long,
    val investigationReason: String,
    val lawViolationInfo: String
)
