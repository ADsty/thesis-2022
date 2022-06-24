package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class ExplanationDocumentGetResponse(
    val explanationDocumentID: Long,
    val carAccidentEntityID: Long,
    val dateOfFill: String,
    val timeOfFill: String,
    val placeOfFill: String,
    val trafficPoliceOfficerID: Long,
    val carAccidentParticipantID: Long,
    val carAccidentWitnessID: Long,
    val interviewedPersonType: String
)