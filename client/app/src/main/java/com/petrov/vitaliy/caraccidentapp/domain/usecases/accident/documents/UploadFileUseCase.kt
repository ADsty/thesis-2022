package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.repository.FileRepository
import java.io.File

class UploadFileUseCase(private val fileRepository: FileRepository) {
    operator fun invoke(jwtToken: String, file: File, fileName: String) =
        fileRepository.uploadFile(jwtToken, file, fileName)
}