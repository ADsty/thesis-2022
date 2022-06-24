package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceCaseProtocolUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateAdministrativeOffenceCaseProtocolUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        protocolUpdateRequest: AdministrativeOffenceCaseProtocolUpdateRequest
    ) = carAccidentDocumentsRepository.updateAdministrativeOffenceCaseProtocol(
        jwtToken,
        protocolUpdateRequest
    )
}