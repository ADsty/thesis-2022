package com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats

data class ChatGetResponse(
    val chatID: Long,
    val carAccidentParticipantID: Long,
    val trafficPoliceOfficerID: Long,
    val carAccidentEntityID: Long,
    val lastMessage: MessageGetResponse
)
