package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.FileRepository
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

class FileRepositoryImpl : FileRepository {

    override fun uploadFile(
        jwtToken: String,
        file: File,
        fileName: String
    ): Result<StringResponse> {

        val mediaType: MediaType = if (fileName.endsWith("png")) "image/png".toMediaTypeOrNull()!!
        else "image/jpeg".toMediaTypeOrNull()!!

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("name", fileName)
            .addFormDataPart("file", fileName, file.asRequestBody(mediaType))
            .build()

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("upload")
            .build()

        val request = Request.Builder().post(requestBody).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    override fun downloadFile(jwtToken: String, fileName: String): Result<File> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("download")
            .addQueryParameter("fileName", fileName)
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result = gson.fromJson(response.body?.string(), File::class.java)
        return Result.success(result)
    }

}