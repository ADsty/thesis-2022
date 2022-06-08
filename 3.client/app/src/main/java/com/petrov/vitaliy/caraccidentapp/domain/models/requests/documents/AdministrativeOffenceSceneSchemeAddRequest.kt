package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceSceneSchemeAddRequest(
    val dateOfFill: Date,
    val timeOfFill: Time,
    val placeOfFill: String,
    val policeOfficerID: Long,
    val fileLink: String,
    val schemeConventions: String,
    val firstWitnessID: Long,
    val secondWitnessID: Long,
    val carAccidentEntityID: Long
)
