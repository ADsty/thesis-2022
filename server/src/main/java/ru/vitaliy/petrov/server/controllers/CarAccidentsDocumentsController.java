package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.dto.*;
import ru.vitaliy.petrov.server.forms.requests.caraccident.CarAccidentInfoRequest;
import ru.vitaliy.petrov.server.forms.requests.documents.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.CarAccidentsDocumentsService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarAccidentsDocumentsController {

    @Autowired
    private CarAccidentsDocumentsService carAccidentsDocumentsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/car-accident-documents/get")
    public CarAccidentEntityDocumentsDTO getCarAccidentDocuments(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new CarAccidentEntityDocumentsDTO(carAccidentsDocumentsService.getCarAccidentDocuments(carAccidentInfoRequest));
    }

    @GetMapping("/car-accident-documents/case-decision/get")
    public AdministrativeOffenceCaseDecisionDTO getAdministrativeOffenceCaseDecision(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new AdministrativeOffenceCaseDecisionDTO(carAccidentsDocumentsService.getAdministrativeOffenceCaseDecision(carAccidentInfoRequest));
    }

    @GetMapping("/car-accident-documents/case-investigation/get")
    public AdministrativeOffenceCaseInvestigationDTO getAdministrativeOffenceCaseInvestigation(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new AdministrativeOffenceCaseInvestigationDTO(carAccidentsDocumentsService.getAdministrativeOffenceCaseInvestigation(carAccidentInfoRequest));
    }

    @GetMapping("/car-accident-documents/case-protocol/get")
    public AdministrativeOffenceCaseProtocolDTO getAdministrativeOffenceCaseProtocol(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new AdministrativeOffenceCaseProtocolDTO(carAccidentsDocumentsService.getAdministrativeOffenceCaseProtocol(carAccidentInfoRequest));
    }

    @GetMapping("/car-accident-documents/case-refusal/get")
    public AdministrativeOffenceCaseRefusalDTO getAdministrativeOffenceCaseRefusal(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new AdministrativeOffenceCaseRefusalDTO(carAccidentsDocumentsService.getAdministrativeOffenceCaseRefusal(carAccidentInfoRequest));
    }

    @GetMapping("/car-accident-documents/scene-inspection/get")
    public AdministrativeOffenceSceneInspectionDTO getAdministrativeOffenceSceneInspection(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new AdministrativeOffenceSceneInspectionDTO(carAccidentsDocumentsService.getAdministrativeOffenceSceneInspection(carAccidentInfoRequest));
    }

    @GetMapping("/car-accident-documents/scene-scheme/get")
    public AdministrativeOffenceSceneSchemeDTO getAdministrativeOffenceSceneScheme(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new AdministrativeOffenceSceneSchemeDTO(carAccidentsDocumentsService.getAdministrativeOffenceSceneScheme(carAccidentInfoRequest));
    }

    @GetMapping("/car-accident-documents/confiscation-protocol/get")
    public ConfiscationOfDocumentsProtocolDTO getConfiscationOfDocumentsProtocol(CarAccidentInfoRequest carAccidentInfoRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new ConfiscationOfDocumentsProtocolDTO(carAccidentsDocumentsService.getConfiscationOfDocumentsProtocol(carAccidentInfoRequest, userID));
    }

    @GetMapping("/car-accident-documents/explanation-document/get")
    public ExplanationDocumentDTO getExplanationDocument(CarAccidentInfoRequest carAccidentInfoRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new ExplanationDocumentDTO(carAccidentsDocumentsService.getExplanationDocument(carAccidentInfoRequest, userID));
    }

    @PostMapping("/car-accident-documents/case-decision/create")
    public CreationResponse addAdministrativeOffenceCaseDecision(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceCaseDecisionAddRequest administrativeOffenceCaseDecisionAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentsDocumentsService.addAdministrativeOffenceCaseDecision(administrativeOffenceCaseDecisionAddRequest);
    }

    @PostMapping("/car-accident-documents/case-investigation/create")
    public CreationResponse addAdministrativeOffenceCaseInvestigation(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceCaseInvestigationAddRequest administrativeOffenceCaseInvestigationAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentsDocumentsService.addAdministrativeOffenceCaseInvestigation(administrativeOffenceCaseInvestigationAddRequest);
    }

    @PostMapping("/car-accident-documents/case-protocol/create")
    public CreationResponse addAdministrativeOffenceCaseProtocol(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceCaseProtocolAddRequest administrativeOffenceCaseProtocolAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentsDocumentsService.addAdministrativeOffenceCaseProtocol(administrativeOffenceCaseProtocolAddRequest);
    }

    @PostMapping("/car-accident-documents/case-refusal/create")
    public CreationResponse addAdministrativeOffenceCaseRefusal(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceCaseRefusalAddRequest administrativeOffenceCaseRefusalAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentsDocumentsService.addAdministrativeOffenceCaseRefusal(administrativeOffenceCaseRefusalAddRequest);
    }

    @PostMapping("/car-accident-documents/scene-inspection/create")
    public CreationResponse addAdministrativeOffenceSceneInspection(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceSceneInspectionAddRequest administrativeOffenceSceneInspectionAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentsDocumentsService.addAdministrativeOffenceSceneInspection(administrativeOffenceSceneInspectionAddRequest);
    }

    @PostMapping("/car-accident-documents/scene-scheme/create")
    public CreationResponse addAdministrativeOffenceSceneScheme(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceSceneSchemeAddRequest administrativeOffenceSceneSchemeAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentsDocumentsService.addAdministrativeOffenceSceneScheme(administrativeOffenceSceneSchemeAddRequest);
    }

    @PostMapping("/car-accident-documents/confiscation-protocol/create")
    public CreationResponse addConfiscationOfDocumentsProtocol(@RequestHeader("Authorization") String jwtToken, ConfiscationOfDocumentsProtocolAddRequest confiscationOfDocumentsProtocolAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentsDocumentsService.addConfiscationOfDocumentsProtocol(confiscationOfDocumentsProtocolAddRequest);
    }

    @PostMapping("/car-accident-documents/explanation-document/create")
    public CreationResponse addExplanationDocument(@RequestHeader("Authorization") String jwtToken, ExplanationDocumentAddRequest explanationDocumentAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentsDocumentsService.addExplanationDocument(explanationDocumentAddRequest);
    }

    @PatchMapping("/car-accident-documents/case-decision/update")
    public StringResponse updateAdministrativeOffenceCaseDecision(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceCaseDecisionUpdateRequest administrativeOffenceCaseDecisionUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.updateAdministrativeOffenceCaseDecision(administrativeOffenceCaseDecisionUpdateRequest));
    }

    @PatchMapping("/car-accident-documents/case-investigation/update")
    public StringResponse updateAdministrativeOffenceCaseInvestigation(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceCaseInvestigationUpdateRequest administrativeOffenceCaseInvestigationUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.updateAdministrativeOffenceCaseInvestigation(administrativeOffenceCaseInvestigationUpdateRequest));
    }

    @PatchMapping("/car-accident-documents/case-protocol/update")
    public StringResponse updateAdministrativeOffenceCaseProtocol(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceCaseProtocolUpdateRequest administrativeOffenceCaseProtocolUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.updateAdministrativeOffenceCaseProtocol(administrativeOffenceCaseProtocolUpdateRequest));
    }

    @PatchMapping("/car-accident-documents/case-refusal/update")
    public StringResponse updateAdministrativeOffenceCaseRefusal(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceCaseRefusalUpdateRequest administrativeOffenceCaseRefusalUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.updateAdministrativeOffenceCaseRefusal(administrativeOffenceCaseRefusalUpdateRequest));
    }

    @PatchMapping("/car-accident-documents/scene-inspection/update")
    public StringResponse updateAdministrativeOffenceSceneInspection(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceSceneInspectionUpdateRequest administrativeOffenceSceneInspectionUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.updateAdministrativeOffenceSceneInspection(administrativeOffenceSceneInspectionUpdateRequest));
    }

    @PatchMapping("/car-accident-documents/scene-scheme/update")
    public StringResponse updateAdministrativeOffenceSceneScheme(@RequestHeader("Authorization") String jwtToken, AdministrativeOffenceSceneSchemeUpdateRequest administrativeOffenceSceneSchemeUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.updateAdministrativeOffenceSceneScheme(administrativeOffenceSceneSchemeUpdateRequest));
    }

    @PatchMapping("/car-accident-documents/confiscation-protocol/update")
    public StringResponse updateConfiscationOfDocumentsProtocol(@RequestHeader("Authorization") String jwtToken, ConfiscationOfDocumentsProtocolUpdateRequest confiscationOfDocumentsProtocolUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.updateConfiscationOfDocumentsProtocol(confiscationOfDocumentsProtocolUpdateRequest));
    }

    @PatchMapping("/car-accident-documents/explanation-document/update")
    public StringResponse updateExplanationDocument(@RequestHeader("Authorization") String jwtToken, ExplanationDocumentUpdateRequest explanationDocumentUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.updateExplanationDocument(explanationDocumentUpdateRequest));
    }

    @DeleteMapping("/car-accident-documents/case-decision/delete")
    public StringResponse deleteAdministrativeOffenceCaseDecision(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.deleteAdministrativeOffenceCaseDecision(carAccidentInfoRequest));
    }

    @DeleteMapping("/car-accident-documents/case-investigation/delete")
    public StringResponse deleteAdministrativeOffenceCaseInvestigation(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.deleteAdministrativeOffenceCaseInvestigation(carAccidentInfoRequest));
    }

    @DeleteMapping("/car-accident-documents/case-protocol/delete")
    public StringResponse deleteAdministrativeOffenceCaseProtocol(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.deleteAdministrativeOffenceCaseProtocol(carAccidentInfoRequest));
    }

    @DeleteMapping("/car-accident-documents/case-refusal/delete")
    public StringResponse deleteAdministrativeOffenceCaseRefusal(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.deleteAdministrativeOffenceCaseRefusal(carAccidentInfoRequest));
    }

    @DeleteMapping("/car-accident-documents/scene-inspection/delete")
    public StringResponse deleteAdministrativeOffenceSceneInspection(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.deleteAdministrativeOffenceSceneInspection(carAccidentInfoRequest));
    }

    @DeleteMapping("/car-accident-documents/scene-scheme/delete")
    public StringResponse deleteAdministrativeOffenceSceneScheme(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.deleteAdministrativeOffenceSceneScheme(carAccidentInfoRequest));
    }

    @DeleteMapping("/car-accident-documents/confiscation-protocol/delete")
    public StringResponse deleteConfiscationOfDocumentsProtocol(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.deleteConfiscationOfDocumentsProtocol(carAccidentInfoRequest, userID));
    }

    @DeleteMapping("/car-accident-documents/explanation-document/delete")
    public StringResponse deleteExplanationDocument(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentsDocumentsService.deleteExplanationDocument(carAccidentInfoRequest, userID));
    }

    @PostMapping("/car-accident-document/user-document/create")
    public CreationResponse addUserAccidentDocument(@RequestHeader("Authorization") String jwtToken, UserAccidentDocumentAddRequest userAccidentDocumentAddRequest) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return carAccidentsDocumentsService.addUserAccidentDocument(userAccidentDocumentAddRequest, userID);
    }

    @PostMapping("/car-accident-document/user-document/files/add")
    public CreationResponse addUserDocumentsFile(@RequestHeader("Authorization") String jwtToken, UserDocumentsFileAddRequest userDocumentsFileAddRequest) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return carAccidentsDocumentsService.addUserDocumentsFile(userDocumentsFileAddRequest, userID);
    }

    @GetMapping("/car-accident-document/user-document/get")
    public UserAccidentDocumentDTO getUserAccidentDocument(@RequestHeader("Authorization") String jwtToken, Long entityID) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new UserAccidentDocumentDTO(carAccidentsDocumentsService.getUserAccidentDocument(entityID, userID));
    }

    @GetMapping("/car-accident-document/user-document/files/get-all")
    public List<UserDocumentFileDTO> getAllUserDocumentsFiles(@RequestHeader("Authorization") String jwtToken, Long entityID) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        ArrayList<UserDocumentFileDTO> result = new ArrayList<>();
        List<UserDocumentFiles> userDocumentFiles = carAccidentsDocumentsService.getAllUserDocumentsFiles(entityID, userID);
        for (UserDocumentFiles file : userDocumentFiles) {
            result.add(new UserDocumentFileDTO(file));
        }
        return result;
    }

    @PatchMapping("/car-accident-document/user-document/update")
    public StringResponse updateUserAccidentDocument(@RequestHeader("Authorization") String jwtToken, UserAccidentDocumentUpdateRequest userAccidentDocumentUpdateRequest) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(carAccidentsDocumentsService.updateUserAccidentDocument(userAccidentDocumentUpdateRequest, userID));
    }

    @DeleteMapping("/car-accident-document/user-document/delete")
    public StringResponse deleteUserAccidentDocument(@RequestHeader("Authorization") String jwtToken, UserAccidentDocumentDeleteRequest userAccidentDocumentDeleteRequest) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(carAccidentsDocumentsService.deleteUserAccidentDocument(userAccidentDocumentDeleteRequest, userID));
    }

    @DeleteMapping("/car-accident-document/user-document/files/delete")
    public StringResponse deleteUserDocumentsFile(@RequestHeader("Authorization") String jwtToken, UserDocumentsFileDeleteRequest userDocumentsFileDeleteRequest) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(carAccidentsDocumentsService.deleteUserDocumentsFile(userDocumentsFileDeleteRequest, userID));
    }

}
