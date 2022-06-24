package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleInsurancePolicyGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.VehicleInsurancePolicyRepository
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class VehicleInsurancePolicyRepositoryImpl : VehicleInsurancePolicyRepository {

    override fun createVehicleInsurancePolicy(
        jwtToken: String,
        vehicleInsurancePolicyCreationRequest: VehicleInsurancePolicyCreationRequest
    ): Result<CreationResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("vehicle-insurance-policy")
            .addPathSegment("create")
            .addQueryParameter(
                "vehicleInsuranceCompany",
                vehicleInsurancePolicyCreationRequest.vehicleInsuranceCompany
            )
            .addQueryParameter(
                "vehicleInsurancePolicyNumber",
                vehicleInsurancePolicyCreationRequest.vehicleInsurancePolicyNumber
            )
            .addQueryParameter(
                "vehicleInsurancePolicyExpirationDate",
                vehicleInsurancePolicyCreationRequest.vehicleInsurancePolicyExpirationDate.toString()
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)

    }

    override fun getVehicleInsurancePolicy(
        jwtToken: String,
        policyID: Long
    ): Result<VehicleInsurancePolicyGetResponse> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("vehicle-insurance-policy")
            .addPathSegment("get")
            .addQueryParameter("policyID", policyID.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeVehicleInsuranceRequest(request)

    }

    override fun updateVehicleInsurancePolicy(
        jwtToken: String,
        vehicleInsurancePolicyUpdateRequest: VehicleInsurancePolicyUpdateRequest
    ): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("vehicle-insurance-policy")
            .addPathSegment("update")
            .addQueryParameter(
                "updatedVehicleInsuranceCompany",
                vehicleInsurancePolicyUpdateRequest.updatedVehicleInsuranceCompany
            )
            .addQueryParameter(
                "updatedVehicleInsurancePolicyNumber",
                vehicleInsurancePolicyUpdateRequest.updatedVehicleInsurancePolicyNumber
            )
            .addQueryParameter(
                "updatedVehicleInsurancePolicyExpirationDate",
                vehicleInsurancePolicyUpdateRequest.updatedVehicleInsurancePolicyExpirationDate.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    override fun deleteVehicleInsurancePolicy(
        jwtToken: String,
        policyID: Long
    ): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("vehicle-insurance-policy")
            .addPathSegment("delete")
            .addQueryParameter("policyID", policyID.toString())
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    private fun executeVehicleInsuranceRequest(request: Request): Result<VehicleInsurancePolicyGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(response.body?.string(), VehicleInsurancePolicyGetResponse::class.java)
        return Result.success(result)
    }

}