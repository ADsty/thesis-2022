package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleProfileGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleProfileRepository
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class VehicleProfileRepositoryImpl : VehicleProfileRepository {

    override fun createVehicleProfile(
        jwtToken: String,
        vehicleProfileCreationRequest: VehicleProfileCreationRequest
    ): Result<CreationResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("vehicle-profile")
            .addPathSegment("create")
            .addQueryParameter("vehicleBrand", vehicleProfileCreationRequest.vehicleBrand)
            .addQueryParameter("vehicleVIN", vehicleProfileCreationRequest.vehicleVIN)
            .addQueryParameter(
                "vehicleRegistrationSign",
                vehicleProfileCreationRequest.vehicleRegistrationSign
            )
            .addQueryParameter(
                "vehicleRegistrationCertificate",
                vehicleProfileCreationRequest.vehicleRegistrationCertificate
            )
            .addQueryParameter(
                "vehicleOwnerFullName",
                vehicleProfileCreationRequest.vehicleOwnerFullName
            )
            .addQueryParameter(
                "vehicleOwnerResidentialAddress",
                vehicleProfileCreationRequest.vehicleOwnerResidentialAddress
            )
            .addQueryParameter(
                "vehicleInsurancePolicyNumber",
                vehicleProfileCreationRequest.vehicleInsurancePolicyNumber
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)

    }

    override fun getUsersVehicleProfiles(jwtToken: String): Result<Array<VehicleProfileGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme("http")
            .host("10.0.2.2")
            .port(8832)
            .addPathSegment("vehicle-profile")
            .addPathSegment("get")
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeVehicleProfileRequest(request)

    }

    override fun updateVehicleProfile(
        jwtToken: String,
        vehicleProfileUpdateRequest: VehicleProfileUpdateRequest,
        vehicleID: Long
    ): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("vehicle-profile")
            .addPathSegment("update")
            .addQueryParameter(
                "updatedVehicleBrand",
                vehicleProfileUpdateRequest.updatedVehicleBrand
            )
            .addQueryParameter("updatedVehicleVIN", vehicleProfileUpdateRequest.updatedVehicleVIN)
            .addQueryParameter(
                "updatedVehicleRegistrationSign",
                vehicleProfileUpdateRequest.updatedVehicleRegistrationSign
            )
            .addQueryParameter(
                "updatedVehicleRegistrationCertificate",
                vehicleProfileUpdateRequest.updatedVehicleRegistrationCertificate
            )
            .addQueryParameter(
                "updatedVehicleOwnerFullName",
                vehicleProfileUpdateRequest.updatedVehicleOwnerFullName
            )
            .addQueryParameter(
                "updatedVehicleOwnerResidentialAddress",
                vehicleProfileUpdateRequest.updatedVehicleOwnerResidentialAddress
            )
            .addQueryParameter(
                "updatedVehicleInsurancePolicyNumber",
                vehicleProfileUpdateRequest.updatedVehicleInsurancePolicyNumber
            )
            .addQueryParameter("vehicleID", vehicleID.toString())
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    override fun deleteVehicleProfile(jwtToken: String, vehicleID: Long): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("vehicle-profile")
            .addPathSegment("delete")
            .addQueryParameter("vehicleID", vehicleID.toString())
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    private fun executeVehicleProfileRequest(request: Request): Result<Array<VehicleProfileGetResponse>> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(response.body?.string(), Array<VehicleProfileGetResponse>::class.java)
        return Result.success(result)
    }

}