package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository

class GetAllUserCarAccidentsUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(jwtToken: String) = carAccidentRepository.getAllUsersCarAccidents(jwtToken)
}