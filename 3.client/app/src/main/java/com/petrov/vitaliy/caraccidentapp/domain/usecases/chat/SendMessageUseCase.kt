package com.petrov.vitaliy.caraccidentapp.domain.usecases.chat

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.ChatRepository

class SendMessageUseCase(private val chatRepository: ChatRepository) {
    operator fun invoke(jwtToken: String, messageCreationRequest: MessageCreationRequest) =
        chatRepository.sendMessage(jwtToken, messageCreationRequest)
}