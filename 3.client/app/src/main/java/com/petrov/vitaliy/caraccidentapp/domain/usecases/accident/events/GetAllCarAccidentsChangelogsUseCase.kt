package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.events

import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository
import java.sql.*

class GetAllCarAccidentsChangelogsUseCase(private val carAccidentRepository: CarAccidentRepository) {
    operator fun invoke(jwtToken: String, entityID: Long, changeDate: Date) =
        carAccidentRepository.getCarAccidentChangelog(jwtToken, entityID, changeDate)
}