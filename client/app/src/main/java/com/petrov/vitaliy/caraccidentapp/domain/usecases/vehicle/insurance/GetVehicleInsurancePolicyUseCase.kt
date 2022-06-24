package com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.insurance

import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleInsurancePolicyRepository

class GetVehicleInsurancePolicyUseCase(private val vehicleInsurancePolicyRepository: VehicleInsurancePolicyRepository) {
    operator fun invoke(jwtToken: String, policyID: Long) =
        vehicleInsurancePolicyRepository.getVehicleInsurancePolicy(jwtToken, policyID)
}