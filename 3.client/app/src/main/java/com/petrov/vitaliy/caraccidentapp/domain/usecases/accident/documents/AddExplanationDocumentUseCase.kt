package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.ExplanationDocumentAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddExplanationDocumentUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        explanationDocumentAddRequest: ExplanationDocumentAddRequest
    ) = carAccidentDocumentsRepository.addExplanationDocument(
        jwtToken,
        explanationDocumentAddRequest
    )
}