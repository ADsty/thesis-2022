package com.petrov.vitaliy.caraccidentapp.presentation.accident.statement

import androidx.lifecycle.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.DriverLicenseGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.UserProfileGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleInsurancePolicyGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleProfileGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events.CreateCarAccidentUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense.GetDriverLicenseUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense.UpdateDriverLicenseUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile.GetUserProfileUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile.UpdateUserProfileUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance.GetVehicleInsurancePolicyUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance.UpdateVehicleInsurancePolicyUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile.GetVehicleProfilesUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile.UpdateVehicleProfileUseCase
import com.petrov.vitaliy.caraccidentapp.presentation.profile.ProfileUpdateState
import kotlinx.coroutines.launch

class CarAccidentStatementViewModel(
    private val createCarAccidentUseCase: CreateCarAccidentUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val getDriverLicenseUseCase: GetDriverLicenseUseCase,
    private val updateDriverLicenseUseCase: UpdateDriverLicenseUseCase,
    private val getVehicleInsurancePolicyUseCase: GetVehicleInsurancePolicyUseCase,
    private val updateVehicleInsurancePolicyUseCase: UpdateVehicleInsurancePolicyUseCase,
    private val getVehicleProfilesUseCase: GetVehicleProfilesUseCase,
    private val updateVehicleProfileUseCase: UpdateVehicleProfileUseCase
) : ViewModel() {

    private val _getUserProfileState = MutableLiveData<StatementGetUserProfileState>()
    private val _getDriverLicenseState = MutableLiveData<StatementGetDriverLicenseState>()
    private val _getVehicleInsuranceState = MutableLiveData<StatementGetVehicleInsuranceState>()
    private val _getVehicleProfileState = MutableLiveData<StatementGetVehicleProfileState>()
    private val _updateCreateState = MutableLiveData<StatementUpdateCreateState>()
    val getUserProfileState: LiveData<StatementGetUserProfileState>
        get() = _getUserProfileState
    val getDriverLicenseState: LiveData<StatementGetDriverLicenseState>
        get() = _getDriverLicenseState
    val getVehicleInsuranceState: LiveData<StatementGetVehicleInsuranceState>
        get() = _getVehicleInsuranceState
    val getVehicleProfileState: LiveData<StatementGetVehicleProfileState>
        get() = _getVehicleProfileState
    val updateCreateState: LiveData<StatementUpdateCreateState>
        get() = _updateCreateState

    fun getUserProfile(jwtToken: String) {
        val result = getUserProfileUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _getUserProfileState.value = StatementGetUserProfileState.Loading

            _getUserProfileState.value = when {
                result.isFailure -> StatementGetUserProfileState.Error
                else -> StatementGetUserProfileState.Success(result.getOrThrow())
            }
        }
    }

    fun updateUserProfile(jwtToken: String, userProfileUpdateRequest: UserProfileUpdateRequest) {
        val result = updateUserProfileUseCase.invoke(jwtToken, userProfileUpdateRequest)

        viewModelScope.launch {
            _updateCreateState.value = StatementUpdateCreateState.Loading

            _updateCreateState.value = when {
                result.isFailure -> StatementUpdateCreateState.Error
                else -> StatementUpdateCreateState.Update
            }
        }
    }

    fun getDriverLicense(jwtToken: String) {
        val result = getDriverLicenseUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _getDriverLicenseState.value = StatementGetDriverLicenseState.Loading

            _getDriverLicenseState.value = when {
                result.isFailure -> StatementGetDriverLicenseState.Error
                else -> StatementGetDriverLicenseState.Success(result.getOrThrow())
            }
        }
    }

    fun updateDriverLicense(
        jwtToken: String,
        driverLicenseUpdateRequest: DriverLicenseUpdateRequest
    ) {
        val result = updateDriverLicenseUseCase.invoke(jwtToken, driverLicenseUpdateRequest)

        viewModelScope.launch {
            _updateCreateState.value = StatementUpdateCreateState.Loading

            _updateCreateState.value = when {
                result.isFailure -> StatementUpdateCreateState.Error
                else -> StatementUpdateCreateState.Update
            }
        }
    }

    fun getVehicleInsurancePolicy(jwtToken: String, policyID: Long) {
        val result = getVehicleInsurancePolicyUseCase.invoke(jwtToken, policyID)

        viewModelScope.launch {
            _getVehicleInsuranceState.value = StatementGetVehicleInsuranceState.Loading

            _getVehicleInsuranceState.value = when {
                result.isFailure -> StatementGetVehicleInsuranceState.Error
                else -> StatementGetVehicleInsuranceState.Success(result.getOrThrow())
            }
        }
    }

    fun updateVehicleInsurancePolicy(
        jwtToken: String,
        vehicleInsurancePolicyUpdateRequest: VehicleInsurancePolicyUpdateRequest
    ) {
        val result = updateVehicleInsurancePolicyUseCase.invoke(
            jwtToken,
            vehicleInsurancePolicyUpdateRequest
        )

        viewModelScope.launch {
            _updateCreateState.value = StatementUpdateCreateState.Loading

            _updateCreateState.value = when {
                result.isFailure -> StatementUpdateCreateState.Error
                else -> StatementUpdateCreateState.Update
            }
        }
    }

    fun getVehicleProfile(jwtToken: String) {
        val result = getVehicleProfilesUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _getVehicleProfileState.value = StatementGetVehicleProfileState.Loading

            _getVehicleProfileState.value = when {
                result.isFailure -> StatementGetVehicleProfileState.Error
                else -> StatementGetVehicleProfileState.Success(result.getOrThrow()[0])
            }
        }
    }

    fun updateVehicleProfile(
        jwtToken: String,
        vehicleProfileUpdateRequest: VehicleProfileUpdateRequest,
        vehicleID: Long
    ) {
        val result =
            updateVehicleProfileUseCase.invoke(jwtToken, vehicleProfileUpdateRequest, vehicleID)

        viewModelScope.launch {
            _updateCreateState.value = StatementUpdateCreateState.Loading

            _updateCreateState.value = when {
                result.isFailure -> StatementUpdateCreateState.Error
                else -> StatementUpdateCreateState.Update
            }
        }
    }

    fun createCarAccident(
        jwtToken: String,
        carAccidentCreationRequest: CarAccidentCreationRequest
    ) {
        val result = createCarAccidentUseCase.invoke(jwtToken, carAccidentCreationRequest)

        viewModelScope.launch {
            _updateCreateState.value = StatementUpdateCreateState.Loading

            _updateCreateState.value = when {
                result.isFailure -> StatementUpdateCreateState.Error
                else -> StatementUpdateCreateState.Create
            }
        }
    }

    fun changeUpdateState() {
        viewModelScope.launch {
            _updateCreateState.value = StatementUpdateCreateState.Loading
        }
    }


}

sealed class StatementGetUserProfileState {
    object Loading : StatementGetUserProfileState()
    data class Success(val data: UserProfileGetResponse) : StatementGetUserProfileState()
    object Error : StatementGetUserProfileState()
}

sealed class StatementGetDriverLicenseState {
    object Loading : StatementGetDriverLicenseState()
    data class Success(val data: DriverLicenseGetResponse) : StatementGetDriverLicenseState()
    object Error : StatementGetDriverLicenseState()
}

sealed class StatementGetVehicleInsuranceState {
    object Loading : StatementGetVehicleInsuranceState()
    data class Success(val data: VehicleInsurancePolicyGetResponse) :
        StatementGetVehicleInsuranceState()

    object Error : StatementGetVehicleInsuranceState()
}

sealed class StatementGetVehicleProfileState {
    object Loading : StatementGetVehicleProfileState()
    data class Success(val data: VehicleProfileGetResponse) : StatementGetVehicleProfileState()
    object Error : StatementGetVehicleProfileState()
}

sealed class StatementUpdateCreateState {
    object Loading : StatementUpdateCreateState()
    object Create : StatementUpdateCreateState()
    object Update : StatementUpdateCreateState()
    object Error : StatementUpdateCreateState()
}

class CarAccidentStatementViewModelFactory(
    private val createCarAccidentUseCase: CreateCarAccidentUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val getDriverLicenseUseCase: GetDriverLicenseUseCase,
    private val updateDriverLicenseUseCase: UpdateDriverLicenseUseCase,
    private val getVehicleInsurancePolicyUseCase: GetVehicleInsurancePolicyUseCase,
    private val updateVehicleInsurancePolicyUseCase: UpdateVehicleInsurancePolicyUseCase,
    private val getVehicleProfilesUseCase: GetVehicleProfilesUseCase,
    private val updateVehicleProfileUseCase: UpdateVehicleProfileUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarAccidentStatementViewModel(
            createCarAccidentUseCase,
            getUserProfileUseCase,
            updateUserProfileUseCase,
            getDriverLicenseUseCase,
            updateDriverLicenseUseCase,
            getVehicleInsurancePolicyUseCase,
            updateVehicleInsurancePolicyUseCase,
            getVehicleProfilesUseCase,
            updateVehicleProfileUseCase
        ) as T
    }
}