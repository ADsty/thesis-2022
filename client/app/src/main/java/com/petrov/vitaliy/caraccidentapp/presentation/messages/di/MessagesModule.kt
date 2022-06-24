package com.petrov.vitaliy.caraccidentapp.presentation.messages.di

import com.petrov.vitaliy.caraccidentapp.data.repository.ChatRepositoryImpl
import com.petrov.vitaliy.caraccidentapp.domain.repository.ChatRepository
import com.petrov.vitaliy.caraccidentapp.domain.usecases.chat.*

class MessagesModule {
    val chatRepository: ChatRepository = ChatRepositoryImpl()
    val getOfficerChatsUseCase = GetOfficerChatsUseCase(chatRepository)
    val getUserChatsUseCase = GetUserChatsUseCase(chatRepository)
    val getAllChatMessagesUseCase = GetAllChatMessagesUseCase(chatRepository)
    val sendMessageUseCase = SendMessageUseCase(chatRepository)
    val updateMessageUseCase = UpdateMessageUseCase(chatRepository)
}