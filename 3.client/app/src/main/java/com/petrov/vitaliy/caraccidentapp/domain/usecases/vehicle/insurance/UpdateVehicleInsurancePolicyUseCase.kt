package com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleInsurancePolicyRepository

class UpdateVehicleInsurancePolicyUseCase(private val vehicleInsurancePolicyRepository: VehicleInsurancePolicyRepository) {
    operator fun invoke(
        jwtToken: String,
        vehicleInsurancePolicyUpdateRequest: VehicleInsurancePolicyUpdateRequest
    ) = vehicleInsurancePolicyRepository.updateVehicleInsurancePolicy(
        jwtToken,
        vehicleInsurancePolicyUpdateRequest
    )
}