package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class ConfiscationOfDocumentsProtocolUpdateRequest(
    val documentID: Long,
    val updatedDateOfFill: Date,
    val updatedTimeOfFill: Time,
    val updatedPlaceOfFill: String,
    val updatedCarAccidentParticipant: Long,
    val updatedConfiscationReason: String,
    val updatedConfiscatedDocumentsInfo: String,
    val updatedConfiscationProcessFixationMethod: String,
    val updatedFirstWitnessID: Long,
    val updatedSecondWitnessID: Long,
    val carAccidentEntityID: Long
)
