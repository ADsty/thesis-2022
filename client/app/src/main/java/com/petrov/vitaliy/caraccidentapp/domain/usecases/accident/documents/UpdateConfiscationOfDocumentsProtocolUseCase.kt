package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.ConfiscationOfDocumentsProtocolUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateConfiscationOfDocumentsProtocolUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        confiscationOfDocumentsProtocolUpdateRequest: ConfiscationOfDocumentsProtocolUpdateRequest
    ) = carAccidentDocumentsRepository.updateConfiscationOfDocumentsProtocol(
        jwtToken,
        confiscationOfDocumentsProtocolUpdateRequest
    )
}