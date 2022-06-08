package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.repository.FileRepository

class GetFileUseCase(private val fileRepository: FileRepository) {
    operator fun invoke(jwtToken: String, fileName: String) =
        fileRepository.downloadFile(jwtToken, fileName)
}