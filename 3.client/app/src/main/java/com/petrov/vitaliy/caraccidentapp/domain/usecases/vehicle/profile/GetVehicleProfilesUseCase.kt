package com.petrov.vitaliy.caraccidentapp.domain.usecases.vehicle.profile

import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleProfileRepository

class GetVehicleProfilesUseCase(private val vehicleProfileRepository: VehicleProfileRepository) {
    operator fun invoke(jwtToken: String) =
        vehicleProfileRepository.getUsersVehicleProfiles(jwtToken)
}