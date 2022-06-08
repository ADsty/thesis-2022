package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceCaseRefusalAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddAdministrativeOffenceCaseRefusalUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        refusalAddRequest: AdministrativeOffenceCaseRefusalAddRequest
    ) = carAccidentDocumentsRepository.addAdministrativeOffenceCaseRefusal(
        jwtToken,
        refusalAddRequest
    )
}