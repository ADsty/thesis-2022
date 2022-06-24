package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats.ChatGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats.MessageGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse

interface ChatRepository {

    fun getAllUserChats(jwtToken: String): Result<Array<ChatGetResponse>>

    fun getAllOfficerChats(jwtToken: String): Result<Array<ChatGetResponse>>

    fun getAllChatMessages(jwtToken: String, chatID: Long): Result<Array<MessageGetResponse>>

    fun sendMessage(
        jwtToken: String,
        messageCreationRequest: MessageCreationRequest
    ): Result<CreationResponse>

    fun updateMessage(
        jwtToken: String,
        messageUpdateRequest: MessageUpdateRequest
    ): Result<StringResponse>

}