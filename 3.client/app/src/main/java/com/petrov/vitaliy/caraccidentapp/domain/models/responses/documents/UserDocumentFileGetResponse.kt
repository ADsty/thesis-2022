package com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents

data class UserDocumentFileGetResponse(
    val userDocumentFileID: Long,
    val userAccidentDocumentID: Long,
    val fileLink: String
)
