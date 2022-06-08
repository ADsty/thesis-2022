package com.petrov.vitaliy.caraccidentapp.domain.usecases.chat

import com.petrov.vitaliy.caraccidentapp.domain.repository.ChatRepository

class GetAllChatMessagesUseCase(private val chatRepository: ChatRepository) {
    operator fun invoke(jwtToken: String, chatID: Long) =
        chatRepository.getAllChatMessages(jwtToken, chatID)
}