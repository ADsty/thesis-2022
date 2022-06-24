package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentWitnessUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class UpdateCarAccidentWitnessUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(
        jwtToken: String,
        carAccidentWitnessUpdateRequest: CarAccidentWitnessUpdateRequest
    ) = carAccidentRepository.updateCarAccidentWitness(jwtToken, carAccidentWitnessUpdateRequest)
}