package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentInfoRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident.CarAccidentEntityDocumentsGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.CarAccidentDocumentsRepository
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class CarAccidentDocumentsRepositoryImpl : CarAccidentDocumentsRepository {

    override fun addAdministrativeOffenceCaseDecision(
        jwtToken: String,
        decisionAddRequest: AdministrativeOffenceCaseDecisionAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-decision")
            .addPathSegment("create")
            .addQueryParameter("dateOfFill", decisionAddRequest.dateOfFill.toString())
            .addQueryParameter("timeOfFill", decisionAddRequest.timeOfFill.toString())
            .addQueryParameter("placeOfFill", decisionAddRequest.placeOfFill)
            .addQueryParameter("policeOfficerID", decisionAddRequest.policeOfficerID.toString())
            .addQueryParameter("culpritID", decisionAddRequest.culpritID.toString())
            .addQueryParameter(
                "culpritActualPlaceOfResidence",
                decisionAddRequest.culpritActualPlaceOfResidence
            )
            .addQueryParameter(
                "carAccidentEntityID",
                decisionAddRequest.carAccidentEntityID.toString()
            )
            .addQueryParameter("caseCircumstances", decisionAddRequest.caseCircumstances)
            .addQueryParameter("caseDecision", decisionAddRequest.caseDecision)
            .addQueryParameter("dateOfReceiving", decisionAddRequest.dateOfReceiving.toString())
            .addQueryParameter("effectiveDate", decisionAddRequest.effectiveDate.toString())
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addAdministrativeOffenceCaseInvestigation(
        jwtToken: String,
        investigationAddRequest: AdministrativeOffenceCaseInvestigationAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-investigation")
            .addPathSegment("create")
            .addQueryParameter("dateOfFill", investigationAddRequest.dateOfFill.toString())
            .addQueryParameter("timeOfFill", investigationAddRequest.timeOfFill.toString())
            .addQueryParameter("placeOfFill", investigationAddRequest.placeOfFill)
            .addQueryParameter(
                "policeOfficerID",
                investigationAddRequest.policeOfficerID.toString()
            )
            .addQueryParameter(
                "carAccidentEntityID",
                investigationAddRequest.carAccidentEntityID.toString()
            )
            .addQueryParameter("investigationReason", investigationAddRequest.investigationReason)
            .addQueryParameter("lawViolationInfo", investigationAddRequest.lawViolationInfo)
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addAdministrativeOffenceCaseProtocol(
        jwtToken: String,
        protocolAddRequest: AdministrativeOffenceCaseProtocolAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-protocol")
            .addPathSegment("create")
            .addQueryParameter("dateOfFill", protocolAddRequest.dateOfFill.toString())
            .addQueryParameter("timeOfFill", protocolAddRequest.timeOfFill.toString())
            .addQueryParameter("placeOfFill", protocolAddRequest.placeOfFill)
            .addQueryParameter("policeOfficerID", protocolAddRequest.policeOfficerID.toString())
            .addQueryParameter("culpritID", protocolAddRequest.culpritID.toString())
            .addQueryParameter(
                "culpritActualPlaceOfResidence",
                protocolAddRequest.culpritActualPlaceOfResidence
            )
            .addQueryParameter(
                "carAccidentEntityID",
                protocolAddRequest.carAccidentEntityID.toString()
            )
            .addQueryParameter("lawViolationInfo", protocolAddRequest.lawViolationInfo)
            .addQueryParameter("caseAdditionalInfo", protocolAddRequest.caseAdditionalInfo)
            .addQueryParameter(
                "placeAndTimeOfCaseExamination",
                protocolAddRequest.placeAndTimeOfCaseExamination
            )
            .addQueryParameter(
                "changedPlaceOfCaseExamination",
                protocolAddRequest.changedPlaceOfCaseExamination
            )
            .addQueryParameter(
                "explanationsAndRemarksOfProtocol",
                protocolAddRequest.explanationsAndRemarksOfProtocol
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addAdministrativeOffenceCaseRefusal(
        jwtToken: String,
        refusalAddRequest: AdministrativeOffenceCaseRefusalAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-refusal")
            .addPathSegment("create")
            .addQueryParameter("dateOfFill", refusalAddRequest.dateOfFill.toString())
            .addQueryParameter("timeOfFill", refusalAddRequest.timeOfFill.toString())
            .addQueryParameter("placeOfFill", refusalAddRequest.placeOfFill)
            .addQueryParameter("policeOfficerID", refusalAddRequest.policeOfficerID.toString())
            .addQueryParameter(
                "carAccidentInfoSender",
                refusalAddRequest.carAccidentInfoSender.toString()
            )
            .addQueryParameter(
                "carAccidentEstablishedInfo",
                refusalAddRequest.carAccidentEstablishedInfo
            )
            .addQueryParameter("refusalReason", refusalAddRequest.refusalReason)
            .addQueryParameter("refusalLawInfo", refusalAddRequest.refusalLawInfo)
            .addQueryParameter(
                "carAccidentEntityID",
                refusalAddRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addAdministrativeOffenceSceneInspection(
        jwtToken: String,
        inspectionAddRequest: AdministrativeOffenceSceneInspectionAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("scene-inspection")
            .addPathSegment("create")
            .addQueryParameter("dateOfFill", inspectionAddRequest.dateOfFill.toString())
            .addQueryParameter("timeOfFill", inspectionAddRequest.timeOfFill.toString())
            .addQueryParameter("placeOfFill", inspectionAddRequest.placeOfFill)
            .addQueryParameter("policeOfficerID", inspectionAddRequest.policeOfficerID.toString())
            .addQueryParameter("cameraUsage", inspectionAddRequest.cameraUsage.toString())
            .addQueryParameter("firstWitnessID", inspectionAddRequest.firstWitnessID.toString())
            .addQueryParameter("secondWitnessID", inspectionAddRequest.secondWitnessID.toString())
            .addQueryParameter("sceneInspectionInfo", inspectionAddRequest.sceneInspectionInfo)
            .addQueryParameter(
                "carAccidentEntityID",
                inspectionAddRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addAdministrativeOffenceSceneScheme(
        jwtToken: String,
        schemeAddRequest: AdministrativeOffenceSceneSchemeAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("scene-scheme")
            .addPathSegment("create")
            .addQueryParameter("dateOfFill", schemeAddRequest.dateOfFill.toString())
            .addQueryParameter("timeOfFill", schemeAddRequest.timeOfFill.toString())
            .addQueryParameter("placeOfFill", schemeAddRequest.placeOfFill)
            .addQueryParameter("policeOfficerID", schemeAddRequest.policeOfficerID.toString())
            .addQueryParameter("fileLink", schemeAddRequest.fileLink)
            .addQueryParameter("schemeConventions", schemeAddRequest.schemeConventions)
            .addQueryParameter("firstWitnessID", schemeAddRequest.firstWitnessID.toString())
            .addQueryParameter("secondWitnessID", schemeAddRequest.secondWitnessID.toString())
            .addQueryParameter(
                "carAccidentEntityID",
                schemeAddRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addConfiscationOfDocumentsProtocol(
        jwtToken: String,
        confiscationOfDocumentsProtocolAddRequest: ConfiscationOfDocumentsProtocolAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("confiscation-protocol")
            .addPathSegment("create")
            .addQueryParameter(
                "dateOfFill",
                confiscationOfDocumentsProtocolAddRequest.dateOfFill.toString()
            )
            .addQueryParameter(
                "timeOfFill",
                confiscationOfDocumentsProtocolAddRequest.timeOfFill.toString()
            )
            .addQueryParameter("placeOfFill", confiscationOfDocumentsProtocolAddRequest.placeOfFill)
            .addQueryParameter(
                "policeOfficerID",
                confiscationOfDocumentsProtocolAddRequest.policeOfficerID.toString()
            )
            .addQueryParameter(
                "carAccidentParticipant",
                confiscationOfDocumentsProtocolAddRequest.carAccidentParticipant.toString()
            )
            .addQueryParameter(
                "confiscationReason",
                confiscationOfDocumentsProtocolAddRequest.confiscationReason
            )
            .addQueryParameter(
                "confiscatedDocumentsInfo",
                confiscationOfDocumentsProtocolAddRequest.confiscatedDocumentsInfo
            )
            .addQueryParameter(
                "confiscationProcessFixationMethod",
                confiscationOfDocumentsProtocolAddRequest.confiscationProcessFixationMethod
            )
            .addQueryParameter(
                "firstWitnessID",
                confiscationOfDocumentsProtocolAddRequest.firstWitnessID.toString()
            )
            .addQueryParameter(
                "secondWitnessID",
                confiscationOfDocumentsProtocolAddRequest.secondWitnessID.toString()
            )
            .addQueryParameter(
                "carAccidentEntityID",
                confiscationOfDocumentsProtocolAddRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addExplanationDocument(
        jwtToken: String,
        explanationDocumentAddRequest: ExplanationDocumentAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("explanation-document")
            .addPathSegment("create")
            .addQueryParameter("dateOfFill", explanationDocumentAddRequest.dateOfFill.toString())
            .addQueryParameter("timeOfFill", explanationDocumentAddRequest.timeOfFill.toString())
            .addQueryParameter("placeOfFill", explanationDocumentAddRequest.placeOfFill)
            .addQueryParameter(
                "policeOfficerID",
                explanationDocumentAddRequest.policeOfficerID.toString()
            )
            .addQueryParameter(
                "carAccidentParticipant",
                explanationDocumentAddRequest.carAccidentParticipant.toString()
            )
            .addQueryParameter(
                "carAccidentWitness",
                explanationDocumentAddRequest.carAccidentWitness.toString()
            )
            .addQueryParameter(
                "interviewedPersonType",
                explanationDocumentAddRequest.interviewedPersonType
            )
            .addQueryParameter(
                "carAccidentEntityID",
                explanationDocumentAddRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addUserAccidentDocument(
        jwtToken: String,
        userAccidentDocumentAddRequest: UserAccidentDocumentAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("user-document")
            .addPathSegment("create")
            .addQueryParameter(
                "carAccidentEntityID",
                userAccidentDocumentAddRequest.carAccidentEntityID.toString()
            )
            .addQueryParameter("sendDate", userAccidentDocumentAddRequest.sendDate.toString())
            .addQueryParameter("sendTime", userAccidentDocumentAddRequest.sendTime.toString())
            .addQueryParameter("explanationText", userAccidentDocumentAddRequest.explanationText)
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun addUserDocumentFile(
        jwtToken: String,
        userDocumentsFileAddRequest: UserDocumentsFileAddRequest
    ): Result<CreationResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("user-document")
            .addPathSegment("files")
            .addPathSegment("add")
            .addQueryParameter(
                "userAccidentDocument",
                userDocumentsFileAddRequest.userAccidentDocument.toString()
            )
            .addQueryParameter("fileLink", userDocumentsFileAddRequest.fileLink)
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)
    }

    override fun deleteAdministrativeOffenceCaseDecision(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-decision")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteAdministrativeOffenceCaseInvestigation(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-investigation")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteAdministrativeOffenceCaseProtocol(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-protocol")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteAdministrativeOffenceCaseRefusal(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-refusal")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteAdministrativeOffenceSceneInspection(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("scene-inspection")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteAdministrativeOffenceSceneScheme(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("scene-scheme")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteConfiscationOfDocumentsProtocol(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest,
        userID: Long
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("confiscation-protocol")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .addQueryParameter("userID", userID.toString())
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteExplanationDocument(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest,
        userID: Long
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("explanation-document")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .addQueryParameter("userID", userID.toString())
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteUserAccidentDocument(
        jwtToken: String,
        userAccidentDocumentDeleteRequest: UserAccidentDocumentDeleteRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("user-document")
            .addPathSegment("delete")
            .addQueryParameter(
                "carAccidentEntityID",
                userAccidentDocumentDeleteRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun deleteUserDocumentsFile(
        jwtToken: String,
        userDocumentsFileDeleteRequest: UserDocumentsFileDeleteRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("user-document")
            .addPathSegment("files")
            .addPathSegment("delete")
            .addQueryParameter("fileLink", userDocumentsFileDeleteRequest.fileLink)
            .build()

        val request = Request.Builder().delete(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun getCarAccidentsDocumentsInfo(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<CarAccidentEntityDocumentsGetResponse> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCarDocumentsRequest(request)
    }

    private fun executeCarDocumentsRequest(request: Request): Result<CarAccidentEntityDocumentsGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(
                response.body?.string(),
                CarAccidentEntityDocumentsGetResponse::class.java
            )
        return Result.success(result)
    }

    override fun getAdministrativeOffenceCaseDecision(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceCaseDecisionGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-decision")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCaseDecisionRequest(request)
    }

    private fun executeCaseDecisionRequest(request: Request): Result<AdministrativeOffenceCaseDecisionGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(
                response.body?.string(),
                AdministrativeOffenceCaseDecisionGetResponse::class.java
            )
        return Result.success(result)
    }

    override fun getAdministrativeOffenceCaseInvestigation(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceCaseInvestigationGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-investigation")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCaseInvestigationRequest(request)
    }

    private fun executeCaseInvestigationRequest(request: Request): Result<AdministrativeOffenceCaseInvestigationGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(
                response.body?.string(),
                AdministrativeOffenceCaseInvestigationGetResponse::class.java
            )
        return Result.success(result)
    }

    override fun getAdministrativeOffenceCaseProtocol(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceCaseProtocolGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-protocol")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCaseProtocolRequest(request)
    }

    private fun executeCaseProtocolRequest(request: Request): Result<AdministrativeOffenceCaseProtocolGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(
                response.body?.string(),
                AdministrativeOffenceCaseProtocolGetResponse::class.java
            )
        return Result.success(result)
    }

    override fun getAdministrativeOffenceCaseRefusal(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceCaseRefusalGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-refusal")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCaseRefusalRequest(request)
    }

    private fun executeCaseRefusalRequest(request: Request): Result<AdministrativeOffenceCaseRefusalGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(
                response.body?.string(),
                AdministrativeOffenceCaseRefusalGetResponse::class.java
            )
        return Result.success(result)
    }

    override fun getAdministrativeOffenceSceneInspection(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceSceneInspectionGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("scene-inspection")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeSceneInspectionRequest(request)
    }

    private fun executeSceneInspectionRequest(request: Request): Result<AdministrativeOffenceSceneInspectionGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(
                response.body?.string(),
                AdministrativeOffenceSceneInspectionGetResponse::class.java
            )
        return Result.success(result)
    }

    override fun getAdministrativeOffenceSceneScheme(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceSceneSchemeGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("scene-scheme")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeSceneSchemeRequest(request)
    }

    private fun executeSceneSchemeRequest(request: Request): Result<AdministrativeOffenceSceneSchemeGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(
                response.body?.string(),
                AdministrativeOffenceSceneSchemeGetResponse::class.java
            )
        return Result.success(result)
    }

    override fun getConfiscationOfDocumentsProtocol(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<ConfiscationOfDocumentsProtocolGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("confiscation-protocol")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeConfiscationProtocolRequest(request)
    }

    private fun executeConfiscationProtocolRequest(request: Request): Result<ConfiscationOfDocumentsProtocolGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(
                response.body?.string(),
                ConfiscationOfDocumentsProtocolGetResponse::class.java
            )
        return Result.success(result)
    }

    override fun getExplanationDocument(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<ExplanationDocumentGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("explanation-document")
            .addPathSegment("get")
            .addQueryParameter(
                "carAccidentEntityID",
                carAccidentInfoRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeExplanationDocumentRequest(request)
    }

    private fun executeExplanationDocumentRequest(request: Request): Result<ExplanationDocumentGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(response.body?.string(), ExplanationDocumentGetResponse::class.java)
        return Result.success(result)
    }

    override fun getUserAccidentDocument(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<UserAccidentDocumentGetResponse> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("user-document")
            .addPathSegment("get")
            .addQueryParameter("entityID", carAccidentInfoRequest.carAccidentEntityID.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeUserAccidentDocumentRequest(request)
    }

    private fun executeUserAccidentDocumentRequest(request: Request): Result<UserAccidentDocumentGetResponse> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(response.body?.string(), UserAccidentDocumentGetResponse::class.java)
        return Result.success(result)
    }

    override fun getAllUserDocumentFiles(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<Array<UserDocumentFileGetResponse>> {
        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("user-document")
            .addPathSegment("files")
            .addPathSegment("get")
            .addQueryParameter("entityID", carAccidentInfoRequest.carAccidentEntityID.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeAllUserDocumentFilesRequest(request)
    }

    private fun executeAllUserDocumentFilesRequest(request: Request): Result<Array<UserDocumentFileGetResponse>> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result =
            gson.fromJson(response.body?.string(), Array<UserDocumentFileGetResponse>::class.java)
        return Result.success(result)
    }

    override fun updateAdministrativeOffenceCaseDecision(
        jwtToken: String,
        decisionUpdateRequest: AdministrativeOffenceCaseDecisionUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-decision")
            .addPathSegment("update")
            .addQueryParameter("documentID", decisionUpdateRequest.documentID.toString())
            .addQueryParameter(
                "updatedDateOfFill",
                decisionUpdateRequest.updatedDateOfFill.toString()
            )
            .addQueryParameter(
                "updatedTimeOfFill",
                decisionUpdateRequest.updatedTimeOfFill.toString()
            )
            .addQueryParameter("updatedPlaceOfFill", decisionUpdateRequest.updatedPlaceOfFill)
            .addQueryParameter(
                "updatedCulpritID",
                decisionUpdateRequest.updatedCulpritID.toString()
            )
            .addQueryParameter(
                "updatedCulpritActualPlaceOfResidence",
                decisionUpdateRequest.updatedCulpritActualPlaceOfResidence
            )
            .addQueryParameter(
                "updatedCaseCircumstances",
                decisionUpdateRequest.updatedCaseCircumstances
            )
            .addQueryParameter("updatedCaseDecision", decisionUpdateRequest.updatedCaseDecision)
            .addQueryParameter(
                "updatedDateOfReceiving",
                decisionUpdateRequest.updatedDateOfReceiving.toString()
            )
            .addQueryParameter(
                "updatedEffectiveDate",
                decisionUpdateRequest.updatedEffectiveDate.toString()
            )
            .addQueryParameter(
                "carAccidentEntityID",
                decisionUpdateRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun updateAdministrativeOffenceCaseInvestigation(
        jwtToken: String,
        investigationUpdateRequest: AdministrativeOffenceCaseInvestigationUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-investigation")
            .addPathSegment("update")
            .addQueryParameter("documentID", investigationUpdateRequest.documentID.toString())
            .addQueryParameter(
                "updatedDateOfFill",
                investigationUpdateRequest.updatedDateOfFill.toString()
            )
            .addQueryParameter(
                "updatedTimeOfFill",
                investigationUpdateRequest.updatedTimeOfFill.toString()
            )
            .addQueryParameter("updatedPlaceOfFill", investigationUpdateRequest.updatedPlaceOfFill)
            .addQueryParameter(
                "updatedInvestigationReason",
                investigationUpdateRequest.updatedInvestigationReason
            )
            .addQueryParameter(
                "updatedLawViolationInfo",
                investigationUpdateRequest.updatedLawViolationInfo
            )
            .addQueryParameter(
                "carAccidentEntityID",
                investigationUpdateRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun updateAdministrativeOffenceCaseProtocol(
        jwtToken: String,
        protocolUpdateRequest: AdministrativeOffenceCaseProtocolUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-protocol")
            .addPathSegment("update")
            .addQueryParameter("documentID", protocolUpdateRequest.documentID.toString())
            .addQueryParameter(
                "updatedDateOfFill",
                protocolUpdateRequest.updatedDateOfFill.toString()
            )
            .addQueryParameter(
                "updatedTimeOfFill",
                protocolUpdateRequest.updatedTimeOfFill.toString()
            )
            .addQueryParameter("updatedPlaceOfFill", protocolUpdateRequest.updatedPlaceOfFill)
            .addQueryParameter(
                "updatedCulpritActualPlaceOfResidence",
                protocolUpdateRequest.updatedCulpritActualPlaceOfResidence
            )
            .addQueryParameter(
                "updatedLawViolationInfo",
                protocolUpdateRequest.updatedLawViolationInfo
            )
            .addQueryParameter(
                "updatedCaseAdditionalInfo",
                protocolUpdateRequest.updatedCaseAdditionalInfo
            )
            .addQueryParameter(
                "updatedPlaceAndTimeOfCaseExamination",
                protocolUpdateRequest.updatedPlaceAndTimeOfCaseExamination
            )
            .addQueryParameter(
                "updatedChangedPlaceOfCaseExamination",
                protocolUpdateRequest.updatedChangedPlaceOfCaseExamination
            )
            .addQueryParameter(
                "updatedExplanationsAndRemarksOfProtocol",
                protocolUpdateRequest.updatedExplanationsAndRemarksOfProtocol
            )
            .addQueryParameter(
                "carAccidentEntityID",
                protocolUpdateRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun updateAdministrativeOffenceCaseRefusal(
        jwtToken: String,
        refusalUpdateRequest: AdministrativeOffenceCaseRefusalUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("case-refusal")
            .addPathSegment("update")
            .addQueryParameter("documentID", refusalUpdateRequest.documentID.toString())
            .addQueryParameter(
                "updatedDateOfFill",
                refusalUpdateRequest.updatedDateOfFill.toString()
            )
            .addQueryParameter(
                "updatedTimeOfFill",
                refusalUpdateRequest.updatedTimeOfFill.toString()
            )
            .addQueryParameter("updatedPlaceOfFill", refusalUpdateRequest.updatedPlaceOfFill)
            .addQueryParameter(
                "updatedCarAccidentEstablishedInfo",
                refusalUpdateRequest.updatedCarAccidentEstablishedInfo
            )
            .addQueryParameter("updatedRefusalReason", refusalUpdateRequest.updatedRefusalReason)
            .addQueryParameter("updatedRefusalLawInfo", refusalUpdateRequest.updatedRefusalLawInfo)
            .addQueryParameter(
                "carAccidentEntityID",
                refusalUpdateRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun updateAdministrativeOffenceSceneInspection(
        jwtToken: String,
        inspectionUpdateRequest: AdministrativeOffenceSceneInspectionUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("scene-inspection")
            .addPathSegment("update")
            .addQueryParameter("documentID", inspectionUpdateRequest.documentID.toString())
            .addQueryParameter(
                "updatedDateOfFill",
                inspectionUpdateRequest.updatedDateOfFill.toString()
            )
            .addQueryParameter(
                "updatedTimeOfFill",
                inspectionUpdateRequest.updatedTimeOfFill.toString()
            )
            .addQueryParameter("updatedPlaceOfFill", inspectionUpdateRequest.updatedPlaceOfFill)
            .addQueryParameter(
                "updatedCameraUsage",
                inspectionUpdateRequest.updatedCameraUsage.toString()
            )
            .addQueryParameter(
                "updatedFirstWitnessID",
                inspectionUpdateRequest.updatedFirstWitnessID.toString()
            )
            .addQueryParameter(
                "updatedSecondWitnessID",
                inspectionUpdateRequest.updatedSecondWitnessID.toString()
            )
            .addQueryParameter(
                "updatedSceneInspectionInfo",
                inspectionUpdateRequest.updatedSceneInspectionInfo
            )
            .addQueryParameter(
                "carAccidentEntityID",
                inspectionUpdateRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun updateAdministrativeOffenceSceneScheme(
        jwtToken: String,
        schemeUpdateRequest: AdministrativeOffenceSceneSchemeUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("scene-scheme")
            .addPathSegment("update")
            .addQueryParameter("documentID", schemeUpdateRequest.documentID.toString())
            .addQueryParameter(
                "updatedDateOfFill",
                schemeUpdateRequest.updatedDateOfFill.toString()
            )
            .addQueryParameter(
                "updatedTimeOfFill",
                schemeUpdateRequest.updatedTimeOfFill.toString()
            )
            .addQueryParameter("updatedPlaceOfFill", schemeUpdateRequest.updatedPlaceOfFill)
            .addQueryParameter("fileLink", schemeUpdateRequest.fileLink)
            .addQueryParameter(
                "updatedSchemeConventions",
                schemeUpdateRequest.updatedSchemeConventions
            )
            .addQueryParameter(
                "updatedFirstWitnessID",
                schemeUpdateRequest.updatedFirstWitnessID.toString()
            )
            .addQueryParameter(
                "updatedSecondWitnessID",
                schemeUpdateRequest.updatedSecondWitnessID.toString()
            )
            .addQueryParameter(
                "carAccidentEntityID",
                schemeUpdateRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun updateConfiscationOfDocumentsProtocol(
        jwtToken: String,
        confiscationOfDocumentsProtocolUpdateRequest: ConfiscationOfDocumentsProtocolUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("confiscation-protocol")
            .addPathSegment("update")
            .addQueryParameter(
                "documentID",
                confiscationOfDocumentsProtocolUpdateRequest.documentID.toString()
            )
            .addQueryParameter(
                "updatedDateOfFill",
                confiscationOfDocumentsProtocolUpdateRequest.updatedDateOfFill.toString()
            )
            .addQueryParameter(
                "updatedTimeOfFill",
                confiscationOfDocumentsProtocolUpdateRequest.updatedTimeOfFill.toString()
            )
            .addQueryParameter(
                "updatedPlaceOfFill",
                confiscationOfDocumentsProtocolUpdateRequest.updatedPlaceOfFill
            )
            .addQueryParameter(
                "updatedCarAccidentParticipant",
                confiscationOfDocumentsProtocolUpdateRequest.updatedCarAccidentParticipant.toString()
            )
            .addQueryParameter(
                "updatedConfiscationReason",
                confiscationOfDocumentsProtocolUpdateRequest.updatedConfiscationReason
            )
            .addQueryParameter(
                "updatedConfiscatedDocumentsInfo",
                confiscationOfDocumentsProtocolUpdateRequest.updatedConfiscatedDocumentsInfo
            )
            .addQueryParameter(
                "updatedConfiscationProcessFixationMethod",
                confiscationOfDocumentsProtocolUpdateRequest.updatedConfiscationProcessFixationMethod
            )
            .addQueryParameter(
                "updatedFirstWitnessID",
                confiscationOfDocumentsProtocolUpdateRequest.updatedFirstWitnessID.toString()
            )
            .addQueryParameter(
                "updatedSecondWitnessID",
                confiscationOfDocumentsProtocolUpdateRequest.updatedSecondWitnessID.toString()
            )
            .addQueryParameter(
                "carAccidentEntityID",
                confiscationOfDocumentsProtocolUpdateRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun updateExplanationDocument(
        jwtToken: String,
        explanationDocumentUpdateRequest: ExplanationDocumentUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("explanation-document")
            .addPathSegment("update")
            .addQueryParameter("documentID", explanationDocumentUpdateRequest.documentID.toString())
            .addQueryParameter(
                "updatedDateOfFill",
                explanationDocumentUpdateRequest.updatedDateOfFill.toString()
            )
            .addQueryParameter(
                "updatedTimeOfFill",
                explanationDocumentUpdateRequest.updatedTimeOfFill.toString()
            )
            .addQueryParameter(
                "updatedPlaceOfFill",
                explanationDocumentUpdateRequest.updatedPlaceOfFill
            )
            .addQueryParameter(
                "updatedInterviewedPersonType",
                explanationDocumentUpdateRequest.updatedInterviewedPersonType
            )
            .addQueryParameter(
                "carAccidentEntityID",
                explanationDocumentUpdateRequest.carAccidentEntityID.toString()
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

    override fun updateUserAccidentDocument(
        jwtToken: String,
        userAccidentDocumentUpdateRequest: UserAccidentDocumentUpdateRequest
    ): Result<StringResponse> {
        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("car-accident-documents")
            .addPathSegment("user-document")
            .addPathSegment("update")
            .addQueryParameter(
                "carAccidentEntityID",
                userAccidentDocumentUpdateRequest.carAccidentEntityID.toString()
            )
            .addQueryParameter(
                "updatedExplanationText",
                userAccidentDocumentUpdateRequest.updatedExplanationText
            )
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)
    }

}