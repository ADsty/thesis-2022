package com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats

import java.sql.Date
import java.sql.Time

data class MessageCreationRequest(
    val addressee: Long,
    val messageCreationDate: Date,
    val messageCreationTime: Time,
    val messageText: String,
    val chatID: Long
)
