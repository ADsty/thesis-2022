package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.AdministrativeOffenceSceneInspectionUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class UpdateAdministrativeOffenceSceneInspectionUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        inspectionUpdateRequest: AdministrativeOffenceSceneInspectionUpdateRequest
    ) = carAccidentDocumentsRepository.updateAdministrativeOffenceSceneInspection(
        jwtToken,
        inspectionUpdateRequest
    )
}