package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.UserAccidentDocumentDeleteRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class DeleteUserAccidentDocumentUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        userAccidentDocumentDeleteRequest: UserAccidentDocumentDeleteRequest
    ) = carAccidentDocumentsRepository.deleteUserAccidentDocument(
        jwtToken,
        userAccidentDocumentDeleteRequest
    )
}