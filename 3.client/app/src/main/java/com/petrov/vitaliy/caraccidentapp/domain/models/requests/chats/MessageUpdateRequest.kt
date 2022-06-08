package com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats

import java.sql.Date
import java.sql.Time

data class MessageUpdateRequest(
    val messageUpdateDate: Date,
    val messageUpdateTime: Time,
    val updatedMessageText: String,
    val messageID: Long
)
