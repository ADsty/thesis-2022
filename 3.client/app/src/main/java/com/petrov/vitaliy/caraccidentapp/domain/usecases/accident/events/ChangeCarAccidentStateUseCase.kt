package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class ChangeCarAccidentStateUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(jwtToken: String, entityID: Long, entityState: String) =
        carAccidentRepository.changeCarAccidentState(jwtToken, entityID, entityState)
}