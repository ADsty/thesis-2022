package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import java.io.File

interface FileRepository {

    fun uploadFile(jwtToken: String, file: File, fileName: String): Result<StringResponse>

    fun downloadFile(jwtToken: String, fileName: String): Result<File>

}