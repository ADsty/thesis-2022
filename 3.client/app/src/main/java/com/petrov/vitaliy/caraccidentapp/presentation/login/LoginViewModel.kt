package com.petrov.vitaliy.caraccidentapp.presentation.login

import androidx.lifecycle.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserLoginRequest
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.ConfirmationCodeCheckUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.ConfirmationCodeGetUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.LoginUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.UpdateUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val confirmationCodeGetUseCase: ConfirmationCodeGetUseCase,
    private val confirmationCodeCheckUseCase: ConfirmationCodeCheckUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UserLoginState>()
    private val _codeState = MutableLiveData<UserConfirmationCodeState>()
    private val _updateUserState = MutableLiveData<UserUpdateState>()
    val eventsState: LiveData<UserLoginState>
        get() = _state
    val codeState: LiveData<UserConfirmationCodeState>
        get() = _codeState
    val userUpdateState: LiveData<UserUpdateState>
        get() = _updateUserState


    fun login(phoneNumber: String, password: String) {

        val result = loginUseCase.invoke(UserLoginRequest(phoneNumber, password))

        viewModelScope.launch {
            _state.value = UserLoginState.Loading

            _state.value = when {
                result.isFailure -> UserLoginState.Error
                else -> UserLoginState.Success(result.getOrThrow().jwt)
            }
        }
    }

    fun getConfirmationCode(phoneNumber: String) {
        val result = confirmationCodeGetUseCase.invoke(phoneNumber)

        viewModelScope.launch {

            _codeState.value = UserConfirmationCodeState.Loading

            _codeState.value = when {
                result.isFailure -> UserConfirmationCodeState.Error
                else -> UserConfirmationCodeState.Success(result.getOrThrow().response)
            }
        }
    }

    fun checkConfirmationCode(phoneNumber: String, confirmationCode: Int) {
        val result = confirmationCodeCheckUseCase.invoke(phoneNumber, confirmationCode)

        viewModelScope.launch {
            _codeState.value = UserConfirmationCodeState.Loading

            _codeState.value = when {
                result.isFailure -> UserConfirmationCodeState.Error
                else -> UserConfirmationCodeState.CodeChecked(result.getOrThrow().response)
            }
        }
    }

    fun updateUser(phoneNumber: String, password: String) {
        val result = updateUserUseCase.invoke(phoneNumber, password)

        viewModelScope.launch {
            _updateUserState.value = UserUpdateState.Loading

            _updateUserState.value = when {
                result.isFailure -> UserUpdateState.Error
                else -> UserUpdateState.Success(result.getOrThrow().jwt)
            }
        }
    }


}

sealed class UserLoginState {
    object Loading : UserLoginState()
    data class Success(val jwt: String) : UserLoginState()
    object Error : UserLoginState()
}

sealed class UserConfirmationCodeState {
    object Loading : UserConfirmationCodeState()
    data class Success(val data: String) : UserConfirmationCodeState()
    data class CodeChecked(val data: String) : UserConfirmationCodeState()
    object Error : UserConfirmationCodeState()
}

sealed class UserUpdateState {
    object Loading : UserUpdateState()
    data class Success(val jwt: String) : UserUpdateState()
    object Error : UserUpdateState()
}

class LoginViewModelFactory(
    private val loginUseCase: LoginUseCase,
    private val confirmationCodeGetUseCase: ConfirmationCodeGetUseCase,
    private val confirmationCodeCheckUseCase: ConfirmationCodeCheckUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(
            loginUseCase,
            confirmationCodeGetUseCase,
            confirmationCodeCheckUseCase,
            updateUserUseCase
        ) as T
    }
}
