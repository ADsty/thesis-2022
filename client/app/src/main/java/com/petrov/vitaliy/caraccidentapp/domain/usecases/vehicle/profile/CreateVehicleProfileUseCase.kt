package com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleProfileRepository

class CreateVehicleProfileUseCase(private val vehicleProfileRepository: VehicleProfileRepository) {
    operator fun invoke(
        jwtToken: String,
        vehicleProfileCreationRequest: VehicleProfileCreationRequest
    ) = vehicleProfileRepository.createVehicleProfile(jwtToken, vehicleProfileCreationRequest)
}