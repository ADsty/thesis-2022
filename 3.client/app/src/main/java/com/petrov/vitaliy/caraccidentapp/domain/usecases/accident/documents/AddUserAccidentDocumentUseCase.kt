package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.UserAccidentDocumentAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddUserAccidentDocumentUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        userAccidentDocumentAddRequest: UserAccidentDocumentAddRequest
    ) = carAccidentDocumentsRepository.addUserAccidentDocument(
        jwtToken,
        userAccidentDocumentAddRequest
    )
}