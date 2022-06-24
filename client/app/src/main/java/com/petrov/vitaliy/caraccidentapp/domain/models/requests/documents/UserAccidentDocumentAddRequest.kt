package com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents

import java.sql.Date
import java.sql.Time

data class UserAccidentDocumentAddRequest(
    val carAccidentEntityID: Long,
    val sendDate: Date,
    val sendTime: Time,
    val explanationText: String
)
