package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceSceneSchemeUpdateRequest(
    val documentID: Long,
    val updatedDateOfFill: Date,
    val updatedTimeOfFill: Time,
    val updatedPlaceOfFill: String,
    val fileLink: String,
    val updatedSchemeConventions: String,
    val updatedFirstWitnessID: Long,
    val updatedSecondWitnessID: Long,
    val carAccidentEntityID: Long
)
