package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class AdministrativeOffenceSceneInspectionAddRequest(
    val dateOfFill: Date,
    val timeOfFill: Time,
    val placeOfFill: String,
    val policeOfficerID: Long,
    val cameraUsage: Boolean,
    val firstWitnessID: Long,
    val secondWitnessID: Long,
    val sceneInspectionInfo: String,
    val carAccidentEntityID: Long
)
