package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class AdministrativeOffenceCaseDecisionGetResponse(
    val documentID: Long,
    val dateOfFill: String,
    val timeOfFill: String,
    val placeOfFill: String,
    val trafficPoliceOfficerID: Long,
    val carAccidentCulpritID: Long,
    val culpritActualPlaceOfResidence: String,
    val carAccidentEntityID: Long,
    val caseCircumstances: String,
    val caseDecision: String,
    val dateOfReceiving: String,
    val effectiveDate: String
)
