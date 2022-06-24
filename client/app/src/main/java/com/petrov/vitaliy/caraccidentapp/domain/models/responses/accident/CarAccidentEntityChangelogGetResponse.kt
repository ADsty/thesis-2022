package com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident


data class CarAccidentEntityChangelogGetResponse(
    val carAccidentEntityChangelogID: Long,
    val carAccidentEntityID: Long,
    val changeDate: String,
    val changeTime: String,
    val changeDescription: String,
    val changeLabel: String
)
