package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceCaseInvestigationAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddAdministrativeOffenceCaseInvestigationUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        investigationAddRequest: AdministrativeOffenceCaseInvestigationAddRequest
    ) = carAccidentDocumentsRepository.addAdministrativeOffenceCaseInvestigation(
        jwtToken,
        investigationAddRequest
    )
}