package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.forms.requests.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.CarAccidentsDocumentsService;

@RestController
public class CarAccidentsDocumentsController {

    @Autowired
    private CarAccidentsDocumentsService carAccidentsDocumentsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/car-accident-documents/get")
    public CarAccidentEntityDocuments getCarAccidentDocuments(CarAccidentInfoRequest carAccidentInfoRequest) {
        return carAccidentsDocumentsService.getCarAccidentDocuments(carAccidentInfoRequest);
    }

    @GetMapping("/car-accident-documents/case-decision/get")
    public AdministrativeOffenceCaseDecision getAdministrativeOffenceCaseDecision(CarAccidentInfoRequest carAccidentInfoRequest) {
        return carAccidentsDocumentsService.getAdministrativeOffenceCaseDecision(carAccidentInfoRequest);
    }

    @GetMapping("/car-accident-documents/case-investigation/get")
    public AdministrativeOffenceCaseInvestigation getAdministrativeOffenceCaseInvestigation(CarAccidentInfoRequest carAccidentInfoRequest) {
        return carAccidentsDocumentsService.getAdministrativeOffenceCaseInvestigation(carAccidentInfoRequest);
    }

    @GetMapping("/car-accident-documents/case-protocol/get")
    public AdministrativeOffenceCaseProtocol getAdministrativeOffenceCaseProtocol(CarAccidentInfoRequest carAccidentInfoRequest) {
        return carAccidentsDocumentsService.getAdministrativeOffenceCaseProtocol(carAccidentInfoRequest);
    }

    @GetMapping("/car-accident-documents/case-refusal/get")
    public AdministrativeOffenceCaseRefusal getAdministrativeOffenceCaseRefusal(CarAccidentInfoRequest carAccidentInfoRequest) {
        return carAccidentsDocumentsService.getAdministrativeOffenceCaseRefusal(carAccidentInfoRequest);
    }

    @GetMapping("/car-accident-documents/scene-inspection/get")
    public AdministrativeOffenceSceneInspection getAdministrativeOffenceSceneInspection(CarAccidentInfoRequest carAccidentInfoRequest) {
        return carAccidentsDocumentsService.getAdministrativeOffenceSceneInspection(carAccidentInfoRequest);
    }

    @GetMapping("/car-accident-documents/scene-scheme/get")
    public AdministrativeOffenceSceneScheme getAdministrativeOffenceSceneScheme(CarAccidentInfoRequest carAccidentInfoRequest) {
        return carAccidentsDocumentsService.getAdministrativeOffenceSceneScheme(carAccidentInfoRequest);
    }

    @GetMapping("/car-accident-documents/confiscation-protocol/get")
    public ConfiscationOfDocumentsProtocol getConfiscationOfDocumentsProtocol(CarAccidentInfoRequest carAccidentInfoRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return carAccidentsDocumentsService.getConfiscationOfDocumentsProtocol(carAccidentInfoRequest, userID);
    }

    @GetMapping("/car-accident-documents/explanation-document/get")
    public ExplanationDocument getExplanationDocument(CarAccidentInfoRequest carAccidentInfoRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return carAccidentsDocumentsService.getExplanationDocument(carAccidentInfoRequest, userID);
    }

    @PostMapping("/car-accident-documents/case-decision/create")
    public CreationResponse addAdministrativeOffenceCaseDecision(AdministrativeOffenceCaseDecisionAddRequest administrativeOffenceCaseDecisionAddRequest) {
        return carAccidentsDocumentsService.addAdministrativeOffenceCaseDecision(administrativeOffenceCaseDecisionAddRequest);
    }

    @PostMapping("/car-accident-documents/case-investigation/create")
    public CreationResponse addAdministrativeOffenceCaseInvestigation(AdministrativeOffenceCaseInvestigationAddRequest administrativeOffenceCaseInvestigationAddRequest) {
        return carAccidentsDocumentsService.addAdministrativeOffenceCaseInvestigation(administrativeOffenceCaseInvestigationAddRequest);
    }

    @PostMapping("/car-accident-documents/case-protocol/create")
    public CreationResponse addAdministrativeOffenceCaseProtocol(AdministrativeOffenceCaseProtocolAddRequest administrativeOffenceCaseProtocolAddRequest) {
        return carAccidentsDocumentsService.addAdministrativeOffenceCaseProtocol(administrativeOffenceCaseProtocolAddRequest);
    }

    @PostMapping("/car-accident-documents/case-refusal/create")
    public CreationResponse addAdministrativeOffenceCaseRefusal(AdministrativeOffenceCaseRefusalAddRequest administrativeOffenceCaseRefusalAddRequest) {
        return carAccidentsDocumentsService.addAdministrativeOffenceCaseRefusal(administrativeOffenceCaseRefusalAddRequest);
    }

    @PostMapping("/car-accident-documents/scene-inspection/create")
    public CreationResponse addAdministrativeOffenceSceneInspection(AdministrativeOffenceSceneInspectionAddRequest administrativeOffenceSceneInspectionAddRequest) {
        return carAccidentsDocumentsService.addAdministrativeOffenceSceneInspection(administrativeOffenceSceneInspectionAddRequest);
    }

    @PostMapping("/car-accident-documents/scene-scheme/create")
    public CreationResponse addAdministrativeOffenceSceneScheme(AdministrativeOffenceSceneSchemeAddRequest administrativeOffenceSceneSchemeAddRequest) {
        return carAccidentsDocumentsService.addAdministrativeOffenceSceneScheme(administrativeOffenceSceneSchemeAddRequest);
    }

    @PostMapping("/car-accident-documents/confiscation-protocol/create")
    public CreationResponse addConfiscationOfDocumentsProtocol(ConfiscationOfDocumentsProtocolAddRequest confiscationOfDocumentsProtocolAddRequest) {
        return carAccidentsDocumentsService.addConfiscationOfDocumentsProtocol(confiscationOfDocumentsProtocolAddRequest);
    }

    @PostMapping("/car-accident-documents/explanation-document/create")
    public CreationResponse addExplanationDocument(ExplanationDocumentAddRequest explanationDocumentAddRequest) {
        return carAccidentsDocumentsService.addExplanationDocument(explanationDocumentAddRequest);
    }

}
