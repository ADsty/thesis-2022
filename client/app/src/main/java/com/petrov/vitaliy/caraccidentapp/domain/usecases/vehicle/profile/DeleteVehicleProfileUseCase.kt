package com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile

import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleInsurancePolicyRepository
import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleProfileRepository

class DeleteVehicleProfileUseCase(
    private val vehicleProfileRepository: VehicleProfileRepository,
    private val vehicleInsurancePolicyRepository: VehicleInsurancePolicyRepository
) {
    operator fun invoke(jwtToken: String, vehicleID: Long, policyID: Long) = Pair(
        vehicleInsurancePolicyRepository.deleteVehicleInsurancePolicy(jwtToken, policyID),
        vehicleProfileRepository.deleteVehicleProfile(jwtToken, vehicleID)
    )
}