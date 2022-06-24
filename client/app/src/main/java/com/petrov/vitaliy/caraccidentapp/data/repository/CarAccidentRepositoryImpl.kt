package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentRepository
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.sql.Date

class CarAccidentRepositoryImpl : CarAccidentRepository {

    override fun createCarAccident(
        jwtToken: String,
        carAccidentCreationRequest: CarAccidentCreationRequest
    ): Result<CreationResponse> {

        var participantsList = "["

        for (participantID: Long in carAccidentCreationRequest.carAccidentSynchronizedParticipants) {
            participantsList += "$participantID, "
        }
        participantsList = participantsList.substring(0, participantsList.length - 3)
        participantsList += "]"

        var vehiclesList = "["

        for (vehicleID: Long in carAccidentCreationRequest.participantsVehicles) {
            vehiclesList += "$vehicleID, "
        }

        vehiclesList = vehiclesList.substring(0, vehiclesList.length - 3)
        vehiclesList += "]"

        val requestBody = FormBody.Builder()
            .add("carAccidentScene", carAccidentCreationRequest.carAccidentScene)
            .add("carAccidentDate", carAccidentCreationRequest.carAccidentDate.toString())
            .add("carAccidentTime", carAccidentCreationRequest.carAccidentTime.toString())
            .add("carAccidentSynchronizedParticipants", participantsList)
            .add("carAccidentParticipantsVehicles", vehiclesList)
            .build()


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("create")
            .build()

        val request = Request.Builder().post(requestBody).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun getAllUsersCarAccidents(jwtToken: String): Result<Array<CarAccidentEntityGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("info")
            .addPathSegment("get-all-user")
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeArrayCarAccidentEntityRequest(request)

    }

    override fun getAllOfficerCarAccidents(jwtToken: String): Result<Array<CarAccidentEntityGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("info")
            .addPathSegment("get-all-officer")
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeArrayCarAccidentEntityRequest(request)

    }

    override fun getCarAccident(
        jwtToken: String,
        carAccidentID: Long
    ): Result<CarAccidentGetResponse> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("get")
            .addQueryParameter("carAccidentID", carAccidentID.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCarAccidentRequest(request)

    }

    override fun getCarAccidentChangelog(
        jwtToken: String,
        carAccidentID: Long,
        changeDate: Date
    ): Result<Array<CarAccidentEntityChangelogGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("get-changelog")
            .addQueryParameter("entityID", carAccidentID.toString())
            .addQueryParameter("changeDate", changeDate.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCarAccidentChangelogRequest(request)

    }

    override fun updateCarAccident(
        jwtToken: String,
        carAccidentUpdateRequest: CarAccidentUpdateRequest,
        carAccidentID: Long
    ): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("update")
            .addQueryParameter(
                "updatedCarAccidentScene",
                carAccidentUpdateRequest.updatedCarAccidentScene
            )
            .addQueryParameter(
                "updatedCarAccidentDate",
                carAccidentUpdateRequest.updatedCarAccidentDate.toString()
            )
            .addQueryParameter(
                "updatedCarAccidentTime",
                carAccidentUpdateRequest.updatedCarAccidentTime.toString()
            )
            .addQueryParameter("carAccidentID", carAccidentID.toString())
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    override fun addCarAccidentParticipant(
        jwtToken: String,
        carAccidentParticipantAddRequest: CarAccidentParticipantAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("participants")
            .addPathSegment("add")
            .addQueryParameter("entityID", carAccidentParticipantAddRequest.entityID.toString())
            .addQueryParameter(
                "participantID",
                carAccidentParticipantAddRequest.participantID.toString()
            )
            .addQueryParameter("vehicleID", carAccidentParticipantAddRequest.vehicleID.toString())
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun getCarAccidentParticipants(
        jwtToken: String,
        entityID: Long
    ): Result<Array<CarAccidentParticipantGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("participants")
            .addPathSegment("get")
            .addQueryParameter("entityID", entityID.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCarAccidentParticipantRequest(request)
    }

    override fun deleteCarAccidentParticipant(
        jwtToken: String,
        carAccidentParticipantDeleteRequest: CarAccidentParticipantDeleteRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("participants")
            .addPathSegment("delete")
            .addQueryParameter(
                "participantID",
                carAccidentParticipantDeleteRequest.participantID.toString()
            )
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun addCarAccidentWitness(
        jwtToken: String,
        carAccidentWitnessAddRequest: CarAccidentWitnessAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("witnesses")
            .addPathSegment("add")
            .addQueryParameter("entityID", carAccidentWitnessAddRequest.entityID.toString())
            .addQueryParameter("witnessFullName", carAccidentWitnessAddRequest.witnessFullName)
            .addQueryParameter(
                "witnessResidentialAddress",
                carAccidentWitnessAddRequest.witnessResidentialAddress
            )
            .addQueryParameter(
                "witnessPhoneNumber",
                carAccidentWitnessAddRequest.witnessPhoneNumber
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun getCarAccidentWitnesses(
        jwtToken: String,
        entityID: Long
    ): Result<Array<CarAccidentWitnessGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("witnesses")
            .addPathSegment("get")
            .addQueryParameter("entityID", entityID.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCarAccidentWitnessRequest(request)
    }

    override fun updateCarAccidentWitness(
        jwtToken: String,
        carAccidentWitnessUpdateRequest: CarAccidentWitnessUpdateRequest
    ): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("witnesses")
            .addPathSegment("update")
            .addQueryParameter(
                "updatedWitnessFullName",
                carAccidentWitnessUpdateRequest.updatedWitnessFullName
            )
            .addQueryParameter(
                "updatedWitnessResidentialAddress",
                carAccidentWitnessUpdateRequest.updatedWitnessResidentialAddress
            )
            .addQueryParameter(
                "updatedWitnessPhoneNumber",
                carAccidentWitnessUpdateRequest.updatedWitnessPhoneNumber
            )
            .addQueryParameter("witnessID", carAccidentWitnessUpdateRequest.witnessID.toString())
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteCarAccidentWitness(
        jwtToken: String,
        carAccidentWitnessDeleteRequest: CarAccidentWitnessDeleteRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("witnesses")
            .addPathSegment("delete")
            .addQueryParameter("witnessID", carAccidentWitnessDeleteRequest.witnessID.toString())
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun changeCarAccidentState(
        jwtToken: String,
        entityID: Long,
        entityState: String
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident")
            .addPathSegment("change-state")
            .addQueryParameter("entityID", entityID.toString())
            .addQueryParameter("entityState", entityState)
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    private fun executeCarAccidentRequest(request: Request): Result<CarAccidentGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result = gson.fromJson(response.body?.string(), CarAccidentGetResponse::class.java)
        return Result.success(result)
    }

    private fun executeArrayCarAccidentEntityRequest(request: Request): Result<Array<CarAccidentEntityGetResponse>> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(response.body?.string(), Array<CarAccidentEntityGetResponse>::class.java)
        return Result.success(result)
    }

    private fun executeCarAccidentChangelogRequest(request: Request): Result<Array<CarAccidentEntityChangelogGetResponse>> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result = gson.fromJson(
            response.body?.string(),
            Array<CarAccidentEntityChangelogGetResponse>::class.java
        )
        return Result.success(result)
    }

    private fun executeCarAccidentParticipantRequest(request: Request): Result<Array<CarAccidentParticipantGetResponse>> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result = gson.fromJson(
            response.body?.string(),
            Array<CarAccidentParticipantGetResponse>::class.java
        )
        return Result.success(result)
    }

    private fun executeCarAccidentWitnessRequest(request: Request): Result<Array<CarAccidentWitnessGetResponse>> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(response.body?.string(), Array<CarAccidentWitnessGetResponse>::class.java)
        return Result.success(result)
    }

}