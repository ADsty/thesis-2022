package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class AdministrativeOffenceSceneSchemeGetResponse(
    val administrativeOffenceSceneSchemeID: Long,
    val dateOfFill: String,
    val timeOfFill: String,
    val placeOfFill: String,
    val trafficPoliceOfficerID: Long,
    val schemeFileLink: String,
    val schemeConventions: String,
    val firstWitnessFullName: String,
    val firstWitnessResidentialAddress: String,
    val firstWitnessPhoneNumber: String,
    val secondWitnessFullName: String,
    val secondWitnessResidentialAddress: String,
    val secondWitnessPhoneNumber: String
)