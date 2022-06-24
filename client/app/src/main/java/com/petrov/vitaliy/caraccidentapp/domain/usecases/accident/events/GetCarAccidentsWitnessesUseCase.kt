package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class GetCarAccidentsWitnessesUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(jwtToken: String, entityID: Long) =
        carAccidentRepository.getCarAccidentWitnesses(jwtToken, entityID)
}