package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentWitnessDeleteRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class DeleteCarAccidentWitnessUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(
        jwtToken: String,
        carAccidentWitnessDeleteRequest: CarAccidentWitnessDeleteRequest
    ) = carAccidentRepository.deleteCarAccidentWitness(jwtToken, carAccidentWitnessDeleteRequest)
}