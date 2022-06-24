package com.petrov.vitaliy.caraccidentapp.domain.usecases.chat

import com.petrov.vitaliy.caraccidentapp.domain.repository.ChatRepository

class GetUserChatsUseCase(private val chatRepository: ChatRepository) {
    operator fun invoke(jwtToken: String) = chatRepository.getAllUserChats(jwtToken)
}