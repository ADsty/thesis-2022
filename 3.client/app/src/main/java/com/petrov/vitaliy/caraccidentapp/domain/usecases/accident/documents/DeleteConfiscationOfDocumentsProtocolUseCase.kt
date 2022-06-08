package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentInfoRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class DeleteConfiscationOfDocumentsProtocolUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest,
        userID: Long
    ) = carAccidentDocumentsRepository.deleteConfiscationOfDocumentsProtocol(
        jwtToken,
        carAccidentInfoRequest,
        userID
    )
}