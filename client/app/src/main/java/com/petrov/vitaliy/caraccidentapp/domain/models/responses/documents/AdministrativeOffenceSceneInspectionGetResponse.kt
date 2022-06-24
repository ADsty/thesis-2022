package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class AdministrativeOffenceSceneInspectionGetResponse(
    val administrativeOffenceSceneInspectionID: Long,
    val dateOfFill: String,
    val timeOfFill: String,
    val placeOfFill: String,
    val trafficPoliceOfficerID: Long,
    val cameraUsage: Boolean,
    val firstWitnessFullName: String,
    val firstWitnessResidentialAddress: String,
    val firstWitnessPhoneNumber: String,
    val secondWitnessFullName: String,
    val secondWitnessResidentialAddress: String,
    val secondWitnessPhoneNumber: String,
    val sceneInspectionInfo: String
)
