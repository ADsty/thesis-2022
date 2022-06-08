package com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident

data class CarAccidentEntityDocumentsGetResponse(
    val carAccidentEntityDocumentsID: Long,
    val carAccidentEntityID: Long,
    val administrativeOffenceCaseProtocolID: Long,
    val administrativeOffenceCaseDecisionID: Long,
    val administrativeOffenceCaseInvestigationID: Long,
    val administrativeOffenceCaseRefusalID: Long,
    val administrativeOffenceSceneSchemeID: Long,
    val administrativeOffenceSceneInspectionID: Long
)