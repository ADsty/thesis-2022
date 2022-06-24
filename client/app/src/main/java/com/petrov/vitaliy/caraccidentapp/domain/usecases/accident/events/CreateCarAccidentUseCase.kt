package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class CreateCarAccidentUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(jwtToken: String, carAccidentCreationRequest: CarAccidentCreationRequest) =
        carAccidentRepository.createCarAccident(jwtToken, carAccidentCreationRequest)
}