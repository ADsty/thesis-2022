package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class AdministrativeOffenceCaseRefusalGetResponse(
    val administrativeOffenceCaseRefusalID: Long,
    val dateOfFill: String,
    val timeOfFill: String,
    val placeOfFill: String,
    val trafficPoliceOfficerID: Long,
    val carAccidentInfoSenderID: Long,
    val carAccidentEstablishedInfo: String,
    val refusalInfo: String,
    val refusalLawInfo: String
)
