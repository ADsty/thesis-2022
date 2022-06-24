package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class GetCarAccidentsParticipantsUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(jwtToken: String, entityID: Long) =
        carAccidentRepository.getCarAccidentParticipants(jwtToken, entityID)
}