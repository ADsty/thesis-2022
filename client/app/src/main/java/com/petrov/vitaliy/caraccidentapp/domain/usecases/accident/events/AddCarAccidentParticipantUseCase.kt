package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentParticipantAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class AddCarAccidentParticipantUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(
        jwtToken: String,
        carAccidentParticipantAddRequest: CarAccidentParticipantAddRequest
    ) = carAccidentRepository.addCarAccidentParticipant(jwtToken, carAccidentParticipantAddRequest)
}