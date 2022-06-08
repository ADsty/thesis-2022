package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class ConfiscationOfDocumentsProtocolGetResponse(
    val confiscationOfDocumentsProtocolID: Long,
    val carAccidentEntityID: Long,
    val dateOfFill: String,
    val timeOfFill: String,
    val placeOfFill: String,
    val trafficPoliceOfficerID: Long,
    val carAccidentParticipantID: Long,
    val confiscationReason: String,
    val confiscatedDocumentsInfo: String,
    val confiscationProcessFixationMethod: String,
    val firstWitnessFullName: String,
    val firstWitnessResidentialAddress: String,
    val firstWitnessPhoneNumber: String,
    val secondWitnessFullName: String,
    val secondWitnessResidentialAddress: String,
    val secondWitnessPhoneNumber: String
)
