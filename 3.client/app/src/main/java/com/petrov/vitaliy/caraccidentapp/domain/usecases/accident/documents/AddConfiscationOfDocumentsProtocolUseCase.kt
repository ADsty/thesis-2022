package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.ConfiscationOfDocumentsProtocolAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddConfiscationOfDocumentsProtocolUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        confiscationOfDocumentsProtocolAddRequest: ConfiscationOfDocumentsProtocolAddRequest
    ) = carAccidentDocumentsRepository.addConfiscationOfDocumentsProtocol(
        jwtToken,
        confiscationOfDocumentsProtocolAddRequest
    )
}