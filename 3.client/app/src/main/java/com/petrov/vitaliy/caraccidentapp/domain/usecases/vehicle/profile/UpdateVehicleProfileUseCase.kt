package com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleProfileRepository

class UpdateVehicleProfileUseCase(private val vehicleProfileRepository: VehicleProfileRepository) {
    operator fun invoke(
        jwtToken: String,
        vehicleProfileUpdateRequest: VehicleProfileUpdateRequest,
        vehicleID: Long
    ) = vehicleProfileRepository.updateVehicleProfile(
        jwtToken,
        vehicleProfileUpdateRequest,
        vehicleID
    )
}