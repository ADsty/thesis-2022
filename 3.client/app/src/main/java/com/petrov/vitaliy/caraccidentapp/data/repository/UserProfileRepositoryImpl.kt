package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.UserProfileGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.UserProfileRepository
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class UserProfileRepositoryImpl : UserProfileRepository {

    override fun createUserProfile(
        jwtToken: String,
        userProfileCreationRequest: UserProfileCreationRequest
    ): Result<CreationResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("user-profile")
            .addPathSegment("create")
            .addQueryParameter("fullName", userProfileCreationRequest.fullName)
            .addQueryParameter("dateOfBirth", userProfileCreationRequest.dateOfBirth.toString())
            .addQueryParameter("residentialAddress", userProfileCreationRequest.residentialAddress)
            .addQueryParameter("placeOfWork", userProfileCreationRequest.placeOfWork)
            .addQueryParameter("positionAtWork", userProfileCreationRequest.positionAtWork)
            .addQueryParameter("workPhoneNumber", userProfileCreationRequest.workPhoneNumber)
            .addQueryParameter(
                "driverLicenseNumber",
                userProfileCreationRequest.driverLicenseNumber
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)

    }

    override fun getUserProfile(jwtToken: String): Result<UserProfileGetResponse> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("user-profile")
            .addPathSegment("get")
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeUserProfileRequest(request)

    }

    override fun updateUserProfile(
        jwtToken: String,
        userProfileUpdateRequest: UserProfileUpdateRequest
    ): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("user-profile")
            .addPathSegment("update")
            .addQueryParameter("updatedFullName", userProfileUpdateRequest.updatedFullName)
            .addQueryParameter(
                "updatedDateOfBirth",
                userProfileUpdateRequest.updatedDateOfBirth.toString()
            )
            .addQueryParameter(
                "updatedResidentialAddress",
                userProfileUpdateRequest.updatedResidentialAddress
            )
            .addQueryParameter("updatedPlaceOfWork", userProfileUpdateRequest.updatedPlaceOfWork)
            .addQueryParameter(
                "updatedPositionAtWork",
                userProfileUpdateRequest.updatedPositionAtWork
            )
            .addQueryParameter(
                "updatedWorkPhoneNumber",
                userProfileUpdateRequest.updatedWorkPhoneNumber
            )
            .addQueryParameter(
                "updatedDriverLicenseNumber",
                userProfileUpdateRequest.updatedDriverLicenseNumber
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    private fun executeUserProfileRequest(request: Request): Result<UserProfileGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result = gson.fromJson(response.body?.string(), UserProfileGetResponse::class.java)
        return Result.success(result)
    }


}