package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class ConfiscationOfDocumentsProtocolAddRequest(
    val dateOfFill: Date,
    val timeOfFill: Time,
    val placeOfFill: String,
    val policeOfficerID: Long,
    val carAccidentParticipant: Long,
    val confiscationReason: String,
    val confiscatedDocumentsInfo: String,
    val confiscationProcessFixationMethod: String,
    val firstWitnessID: Long,
    val secondWitnessID: Long,
    val carAccidentEntityID: Long
)
