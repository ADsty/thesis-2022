package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.caraccident.CarAccidentInfoRequest;
import ru.vitaliy.petrov.server.forms.requests.documents.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.*;

public interface ICarAccidentsDocumentsService {

    CarAccidentEntityDocuments getCarAccidentDocuments(CarAccidentInfoRequest carAccidentInfoRequest);

    CreationResponse addAdministrativeOffenceCaseDecision(AdministrativeOffenceCaseDecisionAddRequest administrativeOffenceCaseDecisionAddRequest);

    CreationResponse addAdministrativeOffenceCaseInvestigation(AdministrativeOffenceCaseInvestigationAddRequest administrativeOffenceCaseInvestigationAddRequest);

    CreationResponse addAdministrativeOffenceCaseProtocol(AdministrativeOffenceCaseProtocolAddRequest administrativeOffenceCaseProtocolAddRequest);

    CreationResponse addAdministrativeOffenceCaseRefusal(AdministrativeOffenceCaseRefusalAddRequest administrativeOffenceCaseRefusalAddRequest);

    CreationResponse addAdministrativeOffenceSceneInspection(AdministrativeOffenceSceneInspectionAddRequest administrativeOffenceSceneInspectionAddRequest);

    CreationResponse addAdministrativeOffenceSceneScheme(AdministrativeOffenceSceneSchemeAddRequest administrativeOffenceSceneSchemeAddRequest);

    CreationResponse addConfiscationOfDocumentsProtocol(ConfiscationOfDocumentsProtocolAddRequest confiscationOfDocumentsProtocolAddRequest);

    CreationResponse addExplanationDocument(ExplanationDocumentAddRequest explanationDocumentAddRequest);

    AdministrativeOffenceCaseDecision getAdministrativeOffenceCaseDecision(CarAccidentInfoRequest carAccidentInfoRequest);

    AdministrativeOffenceCaseInvestigation getAdministrativeOffenceCaseInvestigation(CarAccidentInfoRequest carAccidentInfoRequest);

    AdministrativeOffenceCaseProtocol getAdministrativeOffenceCaseProtocol(CarAccidentInfoRequest carAccidentInfoRequest);

    AdministrativeOffenceCaseRefusal getAdministrativeOffenceCaseRefusal(CarAccidentInfoRequest carAccidentInfoRequest);

    AdministrativeOffenceSceneInspection getAdministrativeOffenceSceneInspection(CarAccidentInfoRequest carAccidentInfoRequest);

    AdministrativeOffenceSceneScheme getAdministrativeOffenceSceneScheme(CarAccidentInfoRequest carAccidentInfoRequest);

    ConfiscationOfDocumentsProtocol getConfiscationOfDocumentsProtocol(CarAccidentInfoRequest carAccidentInfoRequest, Long userID);

    ExplanationDocument getExplanationDocument(CarAccidentInfoRequest carAccidentInfoRequest, Long userID);

    String updateAdministrativeOffenceCaseDecision(AdministrativeOffenceCaseDecisionUpdateRequest administrativeOffenceCaseDecisionUpdateRequest);

    String updateAdministrativeOffenceCaseInvestigation(AdministrativeOffenceCaseInvestigationUpdateRequest administrativeOffenceCaseInvestigationUpdateRequest);

    String updateAdministrativeOffenceCaseProtocol(AdministrativeOffenceCaseProtocolUpdateRequest administrativeOffenceCaseProtocolUpdateRequest);

    String updateAdministrativeOffenceCaseRefusal(AdministrativeOffenceCaseRefusalUpdateRequest administrativeOffenceCaseRefusalUpdateRequest);

    String updateAdministrativeOffenceSceneInspection(AdministrativeOffenceSceneInspectionUpdateRequest administrativeOffenceSceneInspectionUpdateRequest);

    String updateAdministrativeOffenceSceneScheme(AdministrativeOffenceSceneSchemeUpdateRequest administrativeOffenceSceneSchemeUpdateRequest);

    String updateConfiscationOfDocumentsProtocol(ConfiscationOfDocumentsProtocolUpdateRequest confiscationOfDocumentsProtocolUpdateRequest);

    String updateExplanationDocument(ExplanationDocumentUpdateRequest explanationDocumentUpdateRequest);

    String deleteAdministrativeOffenceCaseDecision(CarAccidentInfoRequest carAccidentInfoRequest);

    String deleteAdministrativeOffenceCaseInvestigation(CarAccidentInfoRequest carAccidentInfoRequest);

    String deleteAdministrativeOffenceCaseProtocol(CarAccidentInfoRequest carAccidentInfoRequest);

    String deleteAdministrativeOffenceCaseRefusal(CarAccidentInfoRequest carAccidentInfoRequest);

    String deleteAdministrativeOffenceSceneInspection(CarAccidentInfoRequest carAccidentInfoRequest);

    String deleteAdministrativeOffenceSceneScheme(CarAccidentInfoRequest carAccidentInfoRequest);

    String deleteConfiscationOfDocumentsProtocol(CarAccidentInfoRequest carAccidentInfoRequest, Long userID);

    String deleteExplanationDocument(CarAccidentInfoRequest carAccidentInfoRequest, Long userID);
}
