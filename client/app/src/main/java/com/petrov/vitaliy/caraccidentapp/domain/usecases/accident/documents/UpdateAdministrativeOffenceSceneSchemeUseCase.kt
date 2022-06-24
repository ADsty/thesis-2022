package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceSceneSchemeUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateAdministrativeOffenceSceneSchemeUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        schemeUpdateRequest: AdministrativeOffenceSceneSchemeUpdateRequest
    ) = carAccidentDocumentsRepository.updateAdministrativeOffenceSceneScheme(
        jwtToken,
        schemeUpdateRequest
    )
}