package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceCaseRefusalUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateAdministrativeOffenceCaseRefusalUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        refusalUpdateRequest: AdministrativeOffenceCaseRefusalUpdateRequest
    ) = carAccidentDocumentsRepository.updateAdministrativeOffenceCaseRefusal(
        jwtToken,
        refusalUpdateRequest
    )
}