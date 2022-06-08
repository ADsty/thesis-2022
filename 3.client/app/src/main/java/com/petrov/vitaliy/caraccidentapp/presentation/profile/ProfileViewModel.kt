package com.petrov.vitaliy.caraccidentapp.presentation.profile

import androidx.lifecycle.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.DriverLicenseGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.UserProfileGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleInsurancePolicyGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleProfileGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense.GetDriverLicenseUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense.UpdateDriverLicenseUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile.GetUserProfileUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile.UpdateUserProfileUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance.GetVehicleInsurancePolicyUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance.UpdateVehicleInsurancePolicyUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile.GetVehicleProfilesUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile.UpdateVehicleProfileUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val getDriverLicenseUseCase: GetDriverLicenseUseCase,
    private val updateDriverLicenseUseCase: UpdateDriverLicenseUseCase,
    private val getVehicleInsurancePolicyUseCase: GetVehicleInsurancePolicyUseCase,
    private val updateVehicleInsurancePolicyUseCase: UpdateVehicleInsurancePolicyUseCase,
    private val getVehicleProfilesUseCase: GetVehicleProfilesUseCase,
    private val updateVehicleProfileUseCase: UpdateVehicleProfileUseCase
) : ViewModel() {

    private val _getUserProfileState = MutableLiveData<ProfileGetUserProfileState>()
    private val _getDriverLicenseState = MutableLiveData<ProfileGetDriverLicenseState>()
    private val _getVehicleInsuranceState = MutableLiveData<ProfileGetVehicleInsuranceState>()
    private val _getVehicleProfileState = MutableLiveData<ProfileGetVehicleProfileState>()
    private val _updateState = MutableLiveData<ProfileUpdateState>()
    val getUserProfileState: LiveData<ProfileGetUserProfileState>
        get() = _getUserProfileState
    val getDriverLicenseState: LiveData<ProfileGetDriverLicenseState>
        get() = _getDriverLicenseState
    val getVehicleInsuranceState: LiveData<ProfileGetVehicleInsuranceState>
        get() = _getVehicleInsuranceState
    val getVehicleProfileState: LiveData<ProfileGetVehicleProfileState>
        get() = _getVehicleProfileState
    val updateState: LiveData<ProfileUpdateState>
        get() = _updateState

    fun getUserProfile(jwtToken: String) {
        val result = getUserProfileUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _getUserProfileState.value = ProfileGetUserProfileState.Loading

            _getUserProfileState.value = when {
                result.isFailure -> ProfileGetUserProfileState.Error
                else -> ProfileGetUserProfileState.Success(result.getOrThrow())
            }
        }
    }

    fun updateUserProfile(jwtToken: String, userProfileUpdateRequest: UserProfileUpdateRequest) {
        val result = updateUserProfileUseCase.invoke(jwtToken, userProfileUpdateRequest)

        viewModelScope.launch {
            _updateState.value = ProfileUpdateState.Loading

            _updateState.value = when {
                result.isFailure -> ProfileUpdateState.Error
                else -> ProfileUpdateState.Success
            }
        }
    }

    fun getDriverLicense(jwtToken: String) {
        val result = getDriverLicenseUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _getDriverLicenseState.value = ProfileGetDriverLicenseState.Loading

            _getDriverLicenseState.value = when {
                result.isFailure -> ProfileGetDriverLicenseState.Error
                else -> ProfileGetDriverLicenseState.Success(result.getOrThrow())
            }
        }
    }

    fun updateDriverLicense(
        jwtToken: String,
        driverLicenseUpdateRequest: DriverLicenseUpdateRequest
    ) {
        val result = updateDriverLicenseUseCase.invoke(jwtToken, driverLicenseUpdateRequest)

        viewModelScope.launch {
            _updateState.value = ProfileUpdateState.Loading

            _updateState.value = when {
                result.isFailure -> ProfileUpdateState.Error
                else -> ProfileUpdateState.Success
            }
        }
    }

    fun getVehicleInsurancePolicy(jwtToken: String, policyID: Long) {
        val result = getVehicleInsurancePolicyUseCase.invoke(jwtToken, policyID)

        viewModelScope.launch {
            _getVehicleInsuranceState.value = ProfileGetVehicleInsuranceState.Loading

            _getVehicleInsuranceState.value = when {
                result.isFailure -> ProfileGetVehicleInsuranceState.Error
                else -> ProfileGetVehicleInsuranceState.Success(result.getOrThrow())
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
            _updateState.value = ProfileUpdateState.Loading

            _updateState.value = when {
                result.isFailure -> ProfileUpdateState.Error
                else -> ProfileUpdateState.Success
            }
        }
    }

    fun getVehicleProfile(jwtToken: String) {
        val result = getVehicleProfilesUseCase.invoke(jwtToken)

        viewModelScope.launch {
            _getVehicleProfileState.value = ProfileGetVehicleProfileState.Loading

            _getVehicleProfileState.value = when {
                result.isFailure -> ProfileGetVehicleProfileState.Error
                else -> ProfileGetVehicleProfileState.Success(result.getOrThrow()[0])
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
            _updateState.value = ProfileUpdateState.Loading

            _updateState.value = when {
                result.isFailure -> ProfileUpdateState.Error
                else -> ProfileUpdateState.Success
            }
        }
    }

    fun changeUpdateState() {
        viewModelScope.launch {
            _updateState.value = ProfileUpdateState.Loading
        }
    }

}

sealed class ProfileGetUserProfileState {
    object Loading : ProfileGetUserProfileState()
    data class Success(val data: UserProfileGetResponse) : ProfileGetUserProfileState()
    object Error : ProfileGetUserProfileState()
}

sealed class ProfileGetDriverLicenseState {
    object Loading : ProfileGetDriverLicenseState()
    data class Success(val data: DriverLicenseGetResponse) : ProfileGetDriverLicenseState()
    object Error : ProfileGetDriverLicenseState()
}

sealed class ProfileGetVehicleInsuranceState {
    object Loading : ProfileGetVehicleInsuranceState()
    data class Success(val data: VehicleInsurancePolicyGetResponse) :
        ProfileGetVehicleInsuranceState()

    object Error : ProfileGetVehicleInsuranceState()
}

sealed class ProfileGetVehicleProfileState {
    object Loading : ProfileGetVehicleProfileState()
    data class Success(val data: VehicleProfileGetResponse) : ProfileGetVehicleProfileState()
    object Error : ProfileGetVehicleProfileState()
}

sealed class ProfileUpdateState {
    object Loading : ProfileUpdateState()
    object Success : ProfileUpdateState()
    object Error : ProfileUpdateState()
}

class ProfileViewModelFactory(
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
        return ProfileViewModel(
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