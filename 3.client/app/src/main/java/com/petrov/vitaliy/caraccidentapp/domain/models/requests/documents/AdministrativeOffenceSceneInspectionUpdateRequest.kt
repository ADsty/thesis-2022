package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceSceneInspectionUpdateRequest(
    val documentID: Long,
    val updatedDateOfFill: Date,
    val updatedTimeOfFill: Time,
    val updatedPlaceOfFill: String,
    val updatedCameraUsage: Boolean,
    val updatedFirstWitnessID: Long,
    val updatedSecondWitnessID: Long,
    val updatedSceneInspectionInfo: String,
    val carAccidentEntityID: Long
)
