package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.DriverLicenseGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.DriverLicenseRepository
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class DriverLicenseRepositoryImpl : DriverLicenseRepository {

    override fun createDriverLicense(
        jwtToken: String,
        driverLicenseCreationRequest: DriverLicenseCreationRequest
    ): Result<CreationResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("driver-license")
            .addPathSegment("create")
            .addQueryParameter(
                "driverLicenseNumber",
                driverLicenseCreationRequest.driverLicenseNumber
            )
            .addQueryParameter(
                "driverLicenseCategory",
                driverLicenseCreationRequest.driverLicenseCategory
            )
            .addQueryParameter(
                "driverLicenseDateOfIssue",
                driverLicenseCreationRequest.driverLicenseDateOfIssue.toString()
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)

    }

    override fun getDriverLicense(jwtToken: String): Result<DriverLicenseGetResponse> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("driver-license")
            .addPathSegment("get")
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeDriverLicenseRequest(request)

    }

    override fun updateDriverLicense(
        jwtToken: String,
        driverLicenseUpdateRequest: DriverLicenseUpdateRequest
    ): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("driver-license")
            .addPathSegment("update")
            .addQueryParameter(
                "updatedDriverLicenseNumber",
                driverLicenseUpdateRequest.updatedDriverLicenseNumber
            )
            .addQueryParameter(
                "updatedDriverLicenseCategory",
                driverLicenseUpdateRequest.updatedDriverLicenseCategory
            )
            .addQueryParameter(
                "updatedDriverLicenseDateOfIssue",
                driverLicenseUpdateRequest.updatedDriverLicenseDateOfIssue.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    private fun executeDriverLicenseRequest(request: Request): Result<DriverLicenseGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result = gson.fromJson(response.body?.string(), DriverLicenseGetResponse::class.java)
        return Result.success(result)
    }

}