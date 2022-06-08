package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.UserAccidentDocumentUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateUserAccidentDocumentUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        userAccidentDocumentUpdateRequest: UserAccidentDocumentUpdateRequest
    ) = carAccidentDocumentsRepository.updateUserAccidentDocument(
        jwtToken,
        userAccidentDocumentUpdateRequest
    )
}