package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentWitnessAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class AddCarAccidentWitnessUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(
        jwtToken: String,
        carAccidentWitnessAddRequest: CarAccidentWitnessAddRequest
    ) = carAccidentRepository.addCarAccidentWitness(jwtToken, carAccidentWitnessAddRequest)
}