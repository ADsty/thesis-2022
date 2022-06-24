package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.UserDocumentsFileDeleteRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class DeleteUserDocumentsFileUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        userDocumentsFileDeleteRequest: UserDocumentsFileDeleteRequest
    ) = carAccidentDocumentsRepository.deleteUserDocumentsFile(
        jwtToken,
        userDocumentsFileDeleteRequest
    )
}