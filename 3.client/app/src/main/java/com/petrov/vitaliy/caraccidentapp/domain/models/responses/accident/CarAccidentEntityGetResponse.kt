package com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident

data class CarAccidentEntityGetResponse(
    val carAccidentEntityID: Long,
    val entityState: String,
    val carAccidentID: Long,
    val trafficPoliceOfficerID: Long
)
