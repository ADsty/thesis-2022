package com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleInsurancePolicyRepository

class CreateVehicleInsurancePolicyUseCase(private val vehicleInsurancePolicyRepository: VehicleInsurancePolicyRepository) {
    operator fun invoke(
        jwtToken: String,
        vehicleInsurancePolicyCreationRequest: VehicleInsurancePolicyCreationRequest
    ) = vehicleInsurancePolicyRepository.createVehicleInsurancePolicy(
        jwtToken,
        vehicleInsurancePolicyCreationRequest
    )
}