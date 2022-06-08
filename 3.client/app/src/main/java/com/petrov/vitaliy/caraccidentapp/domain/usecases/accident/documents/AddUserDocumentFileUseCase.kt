package com.petrov.vitaliy.caraccidentapp.domain.usecases.accident.documents

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.UserDocumentsFileAddRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository
import com.petrov.vitaliy.caraccidentapp.domain.repository.FileRepository
import java.io.File
import java.io.IOException

class AddUserDocumentFileUseCase(
    private val carAccidentDocumentsRepository: CarAccidentDocumentsRepository,
    private val fileRepository: FileRepository
) {

    operator fun invoke(
        jwtToken: String,
        userDocumentsFileAddRequest: UserDocumentsFileAddRequest,
        file: File
    ): Result<CreationResponse> {
        val downloadResult =
            fileRepository.uploadFile(jwtToken, file, userDocumentsFileAddRequest.fileLink)
        return if (downloadResult.isSuccess && downloadResult.getOrThrow().response == "Вы успешно загрузили файл") {
            carAccidentDocumentsRepository.addUserDocumentFile(
                jwtToken,
                userDocumentsFileAddRequest
            )
        } else {
            Result.failure(IOException())
        }
    }
}