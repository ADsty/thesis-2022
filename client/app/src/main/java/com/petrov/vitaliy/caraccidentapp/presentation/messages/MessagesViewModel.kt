package com.petrov.vitaliy.caraccidentapp.presentation.messages

import androidx.lifecycle.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats.ChatGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats.MessageGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.usecases.chat.*
import kotlinx.coroutines.launch

class MessagesViewModel(
    private val getOfficerChatsUseCase: GetOfficerChatsUseCase,
    private val getUserChatsUseCase: GetUserChatsUseCase,
    private val getAllChatMessagesUseCase: GetAllChatMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val updateMessageUseCase: UpdateMessageUseCase
) : ViewModel() {

    private val _allChatGetState = MutableLiveData<AllChatsGetState>()
    private val _allMessageGetState = MutableLiveData<AllMessageGetState>()
    private val _messageState = MutableLiveData<MessageState>()
    val allChatGetState: LiveData<AllChatsGetState>
        get() = _allChatGetState
    val allMessageGetState: LiveData<AllMessageGetState>
        get() = _allMessageGetState
    val messageState: LiveData<MessageState>
        get() = _messageState

    fun getOfficerChats(jwtToken: String) {
        val result = getOfficerChatsUseCase.invoke(jwtToken)

        viewModelScope.launch {

            _allChatGetState.value = AllChatsGetState.Loading

            _allChatGetState.value = when {
                result.isFailure -> AllChatsGetState.Error
                else -> AllChatsGetState.Success(result.getOrThrow())
            }
        }
    }

    fun getUserChats(jwtToken: String) {
        val result = getUserChatsUseCase.invoke(jwtToken)

        viewModelScope.launch {

            _allChatGetState.value = AllChatsGetState.Loading

            _allChatGetState.value = when {
                result.isFailure -> AllChatsGetState.Error
                else -> AllChatsGetState.Success(result.getOrThrow())
            }
        }
    }

    fun getAllChatMessages(jwtToken: String, chatID: Long) {
        val result = getAllChatMessagesUseCase.invoke(jwtToken, chatID)

        viewModelScope.launch {

            _allMessageGetState.value = AllMessageGetState.Loading

            _allMessageGetState.value = when {
                result.isFailure -> AllMessageGetState.Error
                else -> AllMessageGetState.Success(result.getOrThrow())
            }
        }
    }

    fun sendMessage(jwtToken: String, messageCreationRequest: MessageCreationRequest) {
        val result = sendMessageUseCase.invoke(jwtToken, messageCreationRequest)

        viewModelScope.launch {

            _messageState.value = MessageState.Loading

            _messageState.value = when {
                result.isFailure -> MessageState.Error
                else -> MessageState.Sent
            }
        }
    }

    fun updateMessage(jwtToken: String, messageUpdateRequest: MessageUpdateRequest) {
        val result = updateMessageUseCase.invoke(jwtToken, messageUpdateRequest)

        viewModelScope.launch {

            _messageState.value = MessageState.Loading

            _messageState.value = when {
                result.isFailure -> MessageState.Error
                else -> MessageState.Updated
            }
        }
    }

    fun changeMessageState() {
        viewModelScope.launch {
            _messageState.value = MessageState.Loading
        }
    }

}

sealed class AllChatsGetState {
    object Loading : AllChatsGetState()
    data class Success(val data: Array<ChatGetResponse>) : AllChatsGetState()
    object Error : AllChatsGetState()
}

sealed class AllMessageGetState {
    object Loading : AllMessageGetState()
    data class Success(val data: Array<MessageGetResponse>) : AllMessageGetState()
    object Error : AllMessageGetState()
}

sealed class MessageState {
    object Loading : MessageState()
    object Sent : MessageState()
    object Updated : MessageState()
    object Error : MessageState()
}

class MessageViewModelFactory(
    private val getOfficerChatsUseCase: GetOfficerChatsUseCase,
    private val getUserChatsUseCase: GetUserChatsUseCase,
    private val getAllChatMessagesUseCase: GetAllChatMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val updateMessageUseCase: UpdateMessageUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesViewModel(
            getOfficerChatsUseCase,
            getUserChatsUseCase,
            getAllChatMessagesUseCase,
            sendMessageUseCase,
            updateMessageUseCase
        ) as T
    }
}