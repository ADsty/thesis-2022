package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.ExplanationDocumentUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateExplanationDocumentUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        explanationDocumentUpdateRequest: ExplanationDocumentUpdateRequest
    ) = carAccidentDocumentsRepository.updateExplanationDocument(
        jwtToken,
        explanationDocumentUpdateRequest
    )
}