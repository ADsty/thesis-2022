package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceCaseProtocolAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddAdministrativeOffenceCaseProtocolUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        protocolAddRequest: AdministrativeOffenceCaseProtocolAddRequest
    ) = carAccidentDocumentsRepository.addAdministrativeOffenceCaseProtocol(
        jwtToken,
        protocolAddRequest
    )
}