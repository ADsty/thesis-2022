package com.petrov.vitaliy.caraccidentapp.presentation.mainpage

import androidx.lifecycle.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident.CarAccidentEntityChangelogGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident.CarAccidentEntityGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events.GetAllCarAccidentsChangelogsUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events.GetAllOfficerCarAccidentsUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events.GetAllUserCarAccidentsUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.GetUserRoleUseCase
import kotlinx.coroutines.launch
import java.sql.Date

class MainPageViewModel(
    private val getUserRoleUseCase: GetUserRoleUseCase,
    private val getAllUserCarAccidentsUseCase: GetAllUserCarAccidentsUseCase,
    private val getAllOfficerCarAccidentsUseCase: GetAllOfficerCarAccidentsUseCase,
    private val getAllCarAccidentsChangelogsUseCase: GetAllCarAccidentsChangelogsUseCase
): ViewModel(){

    private val _mainPageState = MutableLiveData<MainPageState>()
    private val _changelogState = MutableLiveData<ChangelogState>()
    val mainPageState: LiveData<MainPageState>
        get() = _mainPageState
    val changelogState: LiveData<ChangelogState>
        get() = _changelogState

    fun getUserRole(jwtToken: String) {

        val result = getUserRoleUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _mainPageState.value = MainPageState.Loading

            _mainPageState.value = when {
                result.isFailure -> MainPageState.Error
                else -> MainPageState.UserRoleGot(result.getOrThrow().response)
            }
        }
    }

    fun getAllUserCarAccidents(jwtToken: String) {

        val result = getAllUserCarAccidentsUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _mainPageState.value = MainPageState.Loading

            val list: Array<CarAccidentEntityGetResponse> = result.getOrThrow()

            _mainPageState.value = when {
                result.isFailure -> MainPageState.Error
                list.isEmpty() -> MainPageState.Empty
                else -> MainPageState.CarAccidentData(list)
            }
        }
    }

    fun getAllOfficerCarAccidents(jwtToken: String) {
        val result = getAllOfficerCarAccidentsUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _mainPageState.value = MainPageState.Loading

            val list: Array<CarAccidentEntityGetResponse> = result.getOrThrow()

            _mainPageState.value = when {
                result.isFailure -> MainPageState.Error
                list.isEmpty() -> MainPageState.Empty
                else -> MainPageState.CarAccidentData(list)
            }
        }
    }

    fun getAllCarAccidentsChangelogs(jwtToken: String, entityID: Long, changeDate: Date) {
        val result = getAllCarAccidentsChangelogsUseCase.invoke(jwtToken, entityID, changeDate)

        viewModelScope.launch {
            _changelogState.value = ChangelogState.Loading
            val list: Array<CarAccidentEntityChangelogGetResponse> = result.getOrThrow()

            _changelogState.value = when {
                result.isFailure -> ChangelogState.Error
                list.isEmpty() -> ChangelogState.Empty
                else -> ChangelogState.ChangelogData(list)
            }
        }
    }

}

sealed class MainPageState {
    object Loading : MainPageState()
    object Empty : MainPageState()
    data class UserRoleGot(val userRole: String) : MainPageState()
    data class CarAccidentData(val data: Array<CarAccidentEntityGetResponse>): MainPageState()
    object Error: MainPageState()
}

sealed class ChangelogState {
    object Loading : ChangelogState()
    object Empty : ChangelogState()
    data class ChangelogData(val data: Array<CarAccidentEntityChangelogGetResponse>): ChangelogState()
    object Error: ChangelogState()
}

class MainPageViewModelFactory(
    private val getUserRoleUseCase: GetUserRoleUseCase,
    private val getAllUserCarAccidentsUseCase: GetAllUserCarAccidentsUseCase,
    private val getAllOfficerCarAccidentsUseCase: GetAllOfficerCarAccidentsUseCase,
    private val getAllCarAccidentsChangelogsUseCase: GetAllCarAccidentsChangelogsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainPageViewModel(getUserRoleUseCase, getAllUserCarAccidentsUseCase, getAllOfficerCarAccidentsUseCase, getAllCarAccidentsChangelogsUseCase) as T
    }
}