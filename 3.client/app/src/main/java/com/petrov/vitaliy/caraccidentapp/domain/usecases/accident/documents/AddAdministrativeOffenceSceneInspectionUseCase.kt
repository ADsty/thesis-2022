package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceSceneInspectionAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class AddAdministrativeOffenceSceneInspectionUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        inspectionAddRequest: AdministrativeOffenceSceneInspectionAddRequest
    ) = carAccidentDocumentsRepository.addAdministrativeOffenceSceneInspection(
        jwtToken,
        inspectionAddRequest
    )
}