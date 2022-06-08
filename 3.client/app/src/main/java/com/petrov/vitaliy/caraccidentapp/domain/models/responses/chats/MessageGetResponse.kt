package com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats

data class MessageGetResponse(
    val messageID: Long,
    val chatID: Long,
    val senderFullName: String,
    val senderID: Long,
    val addresseeFullName: String,
    val addresseeID: Long,
    val messageCreationDate: String,
    val messageCreationTime: String,
    val messageUpdateDate: String,
    val messageUpdateTime: String,
    val messageText: String
)
