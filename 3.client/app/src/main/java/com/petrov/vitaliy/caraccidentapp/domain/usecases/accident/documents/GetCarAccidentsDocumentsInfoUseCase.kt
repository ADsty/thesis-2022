package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentInfoRequest
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository

class GetCarAccidentsDocumentsInfoUseCase(private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository) {
    operator fun invoke(jwtToken: String, carAccidentInfoRequest: CarAccidentInfoRequest) =
        carAccidentDocumentsRepository.getCarAccidentsDocumentsInfo(
            jwtToken,
            carAccidentInfoRequest
        )
}