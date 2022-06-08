package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class ExplanationDocumentAddRequest(
    val dateOfFill: Date,
    val timeOfFill: Time,
    val placeOfFill: String,
    val policeOfficerID: Long,
    val carAccidentParticipant: Long?,
    val carAccidentWitness: Long?,
    val interviewedPersonType: String,
    val carAccidentEntityID: Long
)
