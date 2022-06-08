package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

data class UserDocumentsFileAddRequest(
    val userAccidentDocument: Long,
    val fileLink: String
)
