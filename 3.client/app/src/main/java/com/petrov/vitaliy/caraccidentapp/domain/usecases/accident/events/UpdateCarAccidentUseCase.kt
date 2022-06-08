package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class UpdateCarAccidentUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(
        jwtToken: String,
        carAccidentUpdateRequest: CarAccidentUpdateRequest,
        carAccidentID: Long
    ) = carAccidentRepository.updateCarAccident(jwtToken, carAccidentUpdateRequest, carAccidentID)
}