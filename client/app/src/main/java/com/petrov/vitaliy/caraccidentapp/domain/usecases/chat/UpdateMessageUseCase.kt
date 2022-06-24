package com.petrov.vitaliy.caraccidentapp.domain.usecases.chat

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.ChatRepository

class UpdateMessageUseCase(private val chatRepository: ChatRepository) {
    operator fun invoke(jwtToken: String, messageUpdateRequest: MessageUpdateRequest) =
        chatRepository.updateMessage(jwtToken, messageUpdateRequest)
}