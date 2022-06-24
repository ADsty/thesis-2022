package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentParticipantDeleteRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class DeleteCarAccidentParticipantUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(
        jwtToken: String,
        carAccidentParticipantDeleteRequest: CarAccidentParticipantDeleteRequest
    ) = carAccidentRepository.deleteCarAccidentParticipant(
        jwtToken,
        carAccidentParticipantDeleteRequest
    )
}