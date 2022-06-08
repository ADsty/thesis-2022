package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentInfoRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class GetAdministrativeOffenceCaseRefusalUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(jwtToken: String, carAccidentInfoRequest: CarAccidentInfoRequest) =
        carAccidentDocumentsRepository.getAdministrativeOffenceCaseRefusal(
            jwtToken,
            carAccidentInfoRequest
        )
}