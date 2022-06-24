package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class UserAccidentDocumentGetResponse(
    val userAccidentDocumentID: Long,
    val carAccidentEntityID: Long,
    val sendDate: String,
    val sendTime: String,
    val senderParticipantID: Long,
    val explanationText: String
)
