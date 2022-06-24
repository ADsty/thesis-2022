package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceCaseDecisionAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddAdministrativeOffenceCaseDecisionUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        decisionAddRequest: AdministrativeOffenceCaseDecisionAddRequest
    ) = carAccidentDocumentsRepository.addAdministrativeOffenceCaseDecision(
        jwtToken,
        decisionAddRequest
    )
}