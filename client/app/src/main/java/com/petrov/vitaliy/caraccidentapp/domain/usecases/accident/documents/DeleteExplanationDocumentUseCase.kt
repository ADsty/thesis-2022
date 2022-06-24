package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentInfoRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class DeleteExplanationDocumentUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest,
        userID: Long
    ) = carAccidentDocumentsRepository.deleteExplanationDocument(
        jwtToken,
        carAccidentInfoRequest,
        userID
    )
}