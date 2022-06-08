package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceCaseDecisionUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateAdministrativeOffenceCaseDecisionUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        decisionUpdateRequest: AdministrativeOffenceCaseDecisionUpdateRequest
    ) = carAccidentDocumentsRepository.updateAdministrativeOffenceCaseDecision(
        jwtToken,
        decisionUpdateRequest
    )
}