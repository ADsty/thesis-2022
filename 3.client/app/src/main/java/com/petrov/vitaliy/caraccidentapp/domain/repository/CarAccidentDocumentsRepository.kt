package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentInfoRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.documents.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident.CarAccidentEntityDocumentsGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.documents.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse

interface CarAccidentDocumentsRepository {

    fun addAdministrativeOffenceCaseDecision(
        jwtToken: String,
        decisionAddRequest: AdministrativeOffenceCaseDecisionAddRequest
    ): Result<CreationResponse>

    fun addAdministrativeOffenceCaseInvestigation(
        jwtToken: String,
        investigationAddRequest: AdministrativeOffenceCaseInvestigationAddRequest
    ): Result<CreationResponse>

    fun addAdministrativeOffenceCaseProtocol(
        jwtToken: String,
        protocolAddRequest: AdministrativeOffenceCaseProtocolAddRequest
    ): Result<CreationResponse>

    fun addAdministrativeOffenceCaseRefusal(
        jwtToken: String,
        refusalAddRequest: AdministrativeOffenceCaseRefusalAddRequest
    ): Result<CreationResponse>

    fun addAdministrativeOffenceSceneInspection(
        jwtToken: String,
        inspectionAddRequest: AdministrativeOffenceSceneInspectionAddRequest
    ): Result<CreationResponse>

    fun addAdministrativeOffenceSceneScheme(
        jwtToken: String,
        schemeAddRequest: AdministrativeOffenceSceneSchemeAddRequest
    ): Result<CreationResponse>

    fun addConfiscationOfDocumentsProtocol(
        jwtToken: String,
        confiscationOfDocumentsProtocolAddRequest: ConfiscationOfDocumentsProtocolAddRequest
    ): Result<CreationResponse>

    fun addExplanationDocument(
        jwtToken: String,
        explanationDocumentAddRequest: ExplanationDocumentAddRequest
    ): Result<CreationResponse>

    fun addUserAccidentDocument(
        jwtToken: String,
        userAccidentDocumentAddRequest: UserAccidentDocumentAddRequest
    ): Result<CreationResponse>

    fun addUserDocumentFile(
        jwtToken: String,
        userDocumentsFileAddRequest: UserDocumentsFileAddRequest
    ): Result<CreationResponse>

    fun deleteAdministrativeOffenceCaseDecision(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse>

    fun deleteAdministrativeOffenceCaseInvestigation(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse>

    fun deleteAdministrativeOffenceCaseProtocol(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse>

    fun deleteAdministrativeOffenceCaseRefusal(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse>

    fun deleteAdministrativeOffenceSceneInspection(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse>

    fun deleteAdministrativeOffenceSceneScheme(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<StringResponse>

    fun deleteConfiscationOfDocumentsProtocol(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest,
        userID: Long
    ): Result<StringResponse>

    fun deleteExplanationDocument(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest,
        userID: Long
    ): Result<StringResponse>

    fun deleteUserAccidentDocument(
        jwtToken: String,
        userAccidentDocumentDeleteRequest: UserAccidentDocumentDeleteRequest
    ): Result<StringResponse>

    fun deleteUserDocumentsFile(
        jwtToken: String,
        userDocumentsFileDeleteRequest: UserDocumentsFileDeleteRequest
    ): Result<StringResponse>

    fun getCarAccidentsDocumentsInfo(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<CarAccidentEntityDocumentsGetResponse>

    fun getAdministrativeOffenceCaseDecision(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceCaseDecisionGetResponse>

    fun getAdministrativeOffenceCaseInvestigation(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceCaseInvestigationGetResponse>

    fun getAdministrativeOffenceCaseProtocol(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceCaseProtocolGetResponse>

    fun getAdministrativeOffenceCaseRefusal(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceCaseRefusalGetResponse>

    fun getAdministrativeOffenceSceneInspection(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceSceneInspectionGetResponse>

    fun getAdministrativeOffenceSceneScheme(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<AdministrativeOffenceSceneSchemeGetResponse>

    fun getConfiscationOfDocumentsProtocol(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<ConfiscationOfDocumentsProtocolGetResponse>

    fun getExplanationDocument(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<ExplanationDocumentGetResponse>

    fun getUserAccidentDocument(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<UserAccidentDocumentGetResponse>

    fun getAllUserDocumentFiles(
        jwtToken: String,
        carAccidentInfoRequest: CarAccidentInfoRequest
    ): Result<Array<UserDocumentFileGetResponse>>

    fun updateAdministrativeOffenceCaseDecision(
        jwtToken: String,
        decisionUpdateRequest: AdministrativeOffenceCaseDecisionUpdateRequest
    ): Result<StringResponse>

    fun updateAdministrativeOffenceCaseInvestigation(
        jwtToken: String,
        investigationUpdateRequest: AdministrativeOffenceCaseInvestigationUpdateRequest
    ): Result<StringResponse>

    fun updateAdministrativeOffenceCaseProtocol(
        jwtToken: String,
        protocolUpdateRequest: AdministrativeOffenceCaseProtocolUpdateRequest
    ): Result<StringResponse>

    fun updateAdministrativeOffenceCaseRefusal(
        jwtToken: String,
        refusalUpdateRequest: AdministrativeOffenceCaseRefusalUpdateRequest
    ): Result<StringResponse>

    fun updateAdministrativeOffenceSceneInspection(
        jwtToken: String,
        inspectionUpdateRequest: AdministrativeOffenceSceneInspectionUpdateRequest
    ): Result<StringResponse>

    fun updateAdministrativeOffenceSceneScheme(
        jwtToken: String,
        schemeUpdateRequest: AdministrativeOffenceSceneSchemeUpdateRequest
    ): Result<StringResponse>

    fun updateConfiscationOfDocumentsProtocol(
        jwtToken: String,
        confiscationOfDocumentsProtocolUpdateRequest: ConfiscationOfDocumentsProtocolUpdateRequest
    ): Result<StringResponse>

    fun updateExplanationDocument(
        jwtToken: String,
        explanationDocumentUpdateRequest: ExplanationDocumentUpdateRequest
    ): Result<StringResponse>

    fun updateUserAccidentDocument(
        jwtToken: String,
        userAccidentDocumentUpdateRequest: UserAccidentDocumentUpdateRequest
    ): Result<StringResponse>

}