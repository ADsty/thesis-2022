package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceSceneSchemeAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddAdministrativeOffenceSceneSchemeUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        schemeAddRequest: AdministrativeOffenceSceneSchemeAddRequest
    ) = carAccidentDocumentsRepository.addAdministrativeOffenceSceneScheme(
        jwtToken,
        schemeAddRequest
    )
}