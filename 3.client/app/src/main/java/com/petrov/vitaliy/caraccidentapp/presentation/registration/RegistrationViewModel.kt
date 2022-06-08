package com.petrov.vitaliy.caraccidentapp.presentation.registration

import androidx.lifecycle.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.*
import com.petrov.vitaliy.caraccidentapp.domain.usecases.driverlicense.CreateDriverLicenseUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.ConfirmationCodeCheckUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.ConfirmationCodeGetUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.RegisterNewUserUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.user.profile.CreateUserProfileUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance.CreateVehicleInsurancePolicyUseCase
import com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile.CreateVehicleProfileUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val confirmationCodeGetUseCase: ConfirmationCodeGetUseCase,
    private val confirmationCodeCheckUseCase: ConfirmationCodeCheckUseCase,
    private val registerNewUserUseCase: RegisterNewUserUseCase,
    private val createDriverLicenseUseCase: CreateDriverLicenseUseCase,
    private val createUserProfileUseCase: CreateUserProfileUseCase,
    private val createVehicleInsurancePolicyUseCase: CreateVehicleInsurancePolicyUseCase,
    private val createVehicleProfileUseCase: CreateVehicleProfileUseCase
) : ViewModel() {

    private val _codeState = MutableLiveData<UserConfirmationCodeStateRegister>()
    private val _creationState = MutableLiveData<CreationStateRegister>()
    private val _registerState = MutableLiveData<RegisterUserState>()
    val codeState: LiveData<UserConfirmationCodeStateRegister>
        get() = _codeState
    val creationState: LiveData<CreationStateRegister>
        get() = _creationState
    val registerState: LiveData<RegisterUserState>
        get() = _registerState


    fun getConfirmationCode(phoneNumber: String) {
        val result = confirmationCodeGetUseCase.invoke(phoneNumber)

        viewModelScope.launch {

            _codeState.value = UserConfirmationCodeStateRegister.Loading

            _codeState.value = when {
                result.isFailure -> UserConfirmationCodeStateRegister.Error
                else -> UserConfirmationCodeStateRegister.Success(result.getOrThrow().response)
            }
        }
    }

    fun checkConfirmationCode(phoneNumber: String, confirmationCode: Int) {
        val result = confirmationCodeCheckUseCase.invoke(phoneNumber, confirmationCode)

        viewModelScope.launch {
            _codeState.value = UserConfirmationCodeStateRegister.Loading

            _codeState.value = when {
                result.isFailure -> UserConfirmationCodeStateRegister.Error
                else -> UserConfirmationCodeStateRegister.CodeChecked(result.getOrThrow().response)
            }
        }
    }

    fun createDriverLicense(
        jwtToken: String,
        driverLicenseCreationRequest: DriverLicenseCreationRequest
    ) {
        val result = createDriverLicenseUseCase.invoke(jwtToken, driverLicenseCreationRequest)

        viewModelScope.launch {
            _creationState.value = CreationStateRegister.Loading

            _creationState.value = when {
                result.isFailure -> CreationStateRegister.Error
                else -> CreationStateRegister.Success(
                    result.getOrThrow().createdEntityName,
                    result.getOrThrow().createdEntityID
                )
            }
        }
    }

    fun createUserProfile(
        jwtToken: String,
        userProfileCreationRequest: UserProfileCreationRequest
    ) {
        val result = createUserProfileUseCase.invoke(jwtToken, userProfileCreationRequest)

        viewModelScope.launch {
            _creationState.value = CreationStateRegister.Loading

            _creationState.value = when {
                result.isFailure -> CreationStateRegister.Error
                else -> CreationStateRegister.Success(
                    result.getOrThrow().createdEntityName,
                    result.getOrThrow().createdEntityID
                )
            }
        }
    }

    fun createVehicleInsurancePolicy(
        jwtToken: String,
        vehicleInsurancePolicyCreationRequest: VehicleInsurancePolicyCreationRequest
    ) {
        val result = createVehicleInsurancePolicyUseCase.invoke(
            jwtToken,
            vehicleInsurancePolicyCreationRequest
        )

        viewModelScope.launch {
            _creationState.value = CreationStateRegister.Loading

            _creationState.value = when {
                result.isFailure -> CreationStateRegister.Error
                else -> CreationStateRegister.Success(
                    result.getOrThrow().createdEntityName,
                    result.getOrThrow().createdEntityID
                )
            }
        }
    }

    fun createVehicleProfile(
        jwtToken: String,
        vehicleProfileCreationRequest: VehicleProfileCreationRequest
    ) {
        val result = createVehicleProfileUseCase.invoke(jwtToken, vehicleProfileCreationRequest)

        viewModelScope.launch {
            _creationState.value = CreationStateRegister.Loading

            _creationState.value = when {
                result.isFailure -> CreationStateRegister.Error
                else -> CreationStateRegister.Success(
                    result.getOrThrow().createdEntityName,
                    result.getOrThrow().createdEntityID
                )
            }
        }

    }

    fun registerNewUser(userRegistrationRequest: UserRegistrationRequest) {
        val result = registerNewUserUseCase.invoke(userRegistrationRequest)

        viewModelScope.launch {
            _registerState.value = RegisterUserState.Loading

            _registerState.value = when {
                result.isFailure -> RegisterUserState.Error
                else -> RegisterUserState.Success(result.getOrThrow().jwt)
            }
        }
    }

}

sealed class CreationStateRegister {
    object Loading : CreationStateRegister()
    data class Success(val entityName: String, val id: Long) : CreationStateRegister()
    object Error : CreationStateRegister()
}

sealed class RegisterUserState {
    object Loading : RegisterUserState()
    data class Success(val jwt: String) : RegisterUserState()
    object Error : RegisterUserState()
}

sealed class UserConfirmationCodeStateRegister {
    object Loading : UserConfirmationCodeStateRegister()
    data class Success(val data: String) : UserConfirmationCodeStateRegister()
    data class CodeChecked(val data: String) : UserConfirmationCodeStateRegister()
    object Error : UserConfirmationCodeStateRegister()
}

class RegistrationViewModelFactory(
    private val confirmationCodeGetUseCase: ConfirmationCodeGetUseCase,
    private val confirmationCodeCheckUseCase: ConfirmationCodeCheckUseCase,
    private val registerNewUserUseCase: RegisterNewUserUseCase,
    private val createDriverLicenseUseCase: CreateDriverLicenseUseCase,
    private val createUserProfileUseCase: CreateUserProfileUseCase,
    private val createVehicleInsurancePolicyUseCase: CreateVehicleInsurancePolicyUseCase,
    private val createVehicleProfileUseCase: CreateVehicleProfileUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegistrationViewModel(
            confirmationCodeGetUseCase,
            confirmationCodeCheckUseCase,
            registerNewUserUseCase,
            createDriverLicenseUseCase,
            createUserProfileUseCase,
            createVehicleInsurancePolicyUseCase,
            createVehicleProfileUseCase
        ) as T
    }
}