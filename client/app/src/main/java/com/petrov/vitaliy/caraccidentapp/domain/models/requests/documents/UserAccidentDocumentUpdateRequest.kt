package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

data class UserAccidentDocumentUpdateRequest(
    val carAccidentEntityID: Long,
    val updatedExplanationText: String
)
