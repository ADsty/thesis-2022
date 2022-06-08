package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceCaseInvestigationUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateAdministrativeOffenceCaseInvestigationUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        investigationUpdateRequest: AdministrativeOffenceCaseInvestigationUpdateRequest
    ) = carAccidentDocumentsRepository.updateAdministrativeOffenceCaseInvestigation(
        jwtToken,
        investigationUpdateRequest
    )
}