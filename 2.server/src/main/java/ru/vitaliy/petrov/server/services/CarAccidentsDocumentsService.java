package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.requests.caraccident.CarAccidentInfoRequest;
import ru.vitaliy.petrov.server.forms.requests.documents.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.repositories.*;

import java.util.Optional;

@Service
public class CarAccidentsDocumentsService implements ICarAccidentsDocumentsService{

    @Autowired
    private CarAccidentEntityDocumentsRepository carAccidentEntityDocumentsRepository;

    @Autowired
    private AdministrativeOffenceCaseDecisionRepository administrativeOffenceCaseDecisionRepository;

    @Autowired
    private AdministrativeOffenceCaseInvestigationRepository administrativeOffenceCaseInvestigationRepository;

    @Autowired
    private AdministrativeOffenceCaseProtocolRepository administrativeOffenceCaseProtocolRepository;

    @Autowired
    private AdministrativeOffenceCaseRefusalRepository administrativeOffenceCaseRefusalRepository;

    @Autowired
    private AdministrativeOffenceSceneInspectionRepository administrativeOffenceSceneInspectionRepository;

    @Autowired
    private AdministrativeOffenceSceneSchemeRepository administrativeOffenceSceneSchemeRepository;

    @Autowired
    private ConfiscationOfDocumentsProtocolRepository confiscationOfDocumentsProtocolRepository;

    @Autowired
    private ExplanationDocumentRepository explanationDocumentRepository;

    @Autowired
    private CarAccidentEntityRepository carAccidentEntityRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public CarAccidentEntityDocuments getCarAccidentDocuments(CarAccidentInfoRequest carAccidentInfoRequest) {
        Long entityID = carAccidentInfoRequest.getCarAccidentEntityID();

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);

        if(carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        CarAccidentEntity carAccidentEntity = carAccidentEntityCandidate.get();

        Optional<CarAccidentEntityDocuments> carAccidentEntityDocumentsCandidate = carAccidentEntityDocumentsRepository.findByCarAccidentEntity(carAccidentEntity);

        if(carAccidentEntityDocumentsCandidate.isEmpty()) {
            throw new ApiRequestException("Набор документов не найден");
        }

        return carAccidentEntityDocumentsCandidate.get();
    }

    @Override
    public AdministrativeOffenceCaseDecision getAdministrativeOffenceCaseDecision(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        return carAccidentEntityDocuments.getAdministrativeOffenceCaseDecision();
    }

    @Override
    public AdministrativeOffenceCaseInvestigation getAdministrativeOffenceCaseInvestigation(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        return carAccidentEntityDocuments.getAdministrativeOffenceCaseInvestigation();
    }

    @Override
    public AdministrativeOffenceCaseProtocol getAdministrativeOffenceCaseProtocol(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        return carAccidentEntityDocuments.getAdministrativeOffenceCaseProtocol();
    }

    @Override
    public AdministrativeOffenceCaseRefusal getAdministrativeOffenceCaseRefusal(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        return carAccidentEntityDocuments.getAdministrativeOffenceCaseRefusal();
    }

    @Override
    public AdministrativeOffenceSceneInspection getAdministrativeOffenceSceneInspection(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        return carAccidentEntityDocuments.getAdministrativeOffenceSceneInspection();
    }

    @Override
    public AdministrativeOffenceSceneScheme getAdministrativeOffenceSceneScheme(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        return carAccidentEntityDocuments.getAdministrativeOffenceSceneScheme();
    }

    @Override
    public ConfiscationOfDocumentsProtocol getConfiscationOfDocumentsProtocol(CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if(userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не найден");
        }

        Users user = userCandidate.get();

        Optional<ConfiscationOfDocumentsProtocol> confiscationOfDocumentsProtocolCandidate = confiscationOfDocumentsProtocolRepository.findByCarAccidentEntityDocumentsAndCarAccidentParticipant(carAccidentEntityDocuments, user);

        if(confiscationOfDocumentsProtocolCandidate.isEmpty()) {
            throw new ApiRequestException("Данный документ не существует");
        }

        return confiscationOfDocumentsProtocolCandidate.get();
    }

    @Override
    public ExplanationDocument getExplanationDocument(CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if(userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не найден");
        }

        Users user = userCandidate.get();

        Optional<ExplanationDocument> explanationDocumentCandidate = explanationDocumentRepository.findByCarAccidentEntityDocumentsAndCarAccidentParticipant(carAccidentEntityDocuments, user);

        if(explanationDocumentCandidate.isEmpty()) {
            throw new ApiRequestException("Данный документ не существует");
        }

        return explanationDocumentCandidate.get();
    }

    @Override
    public String deleteAdministrativeOffenceCaseDecision(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        administrativeOffenceCaseDecisionRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceCaseDecision());
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceCaseInvestigation(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        administrativeOffenceCaseInvestigationRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceCaseInvestigation());
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceCaseProtocol(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        administrativeOffenceCaseProtocolRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceCaseProtocol());
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceCaseRefusal(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        administrativeOffenceCaseRefusalRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceCaseRefusal());
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceSceneInspection(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        administrativeOffenceSceneInspectionRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceSceneInspection());
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceSceneScheme(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        administrativeOffenceSceneSchemeRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceSceneScheme());
        return "Документ был удален";
    }

    @Override
    public String deleteConfiscationOfDocumentsProtocol(CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        ConfiscationOfDocumentsProtocol confiscationOfDocumentsProtocol = getConfiscationOfDocumentsProtocol(carAccidentInfoRequest, userID);
        confiscationOfDocumentsProtocolRepository.delete(confiscationOfDocumentsProtocol);
        return "Документ был удален";
    }

    @Override
    public String deleteExplanationDocument(CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        ExplanationDocument explanationDocument = getExplanationDocument(carAccidentInfoRequest, userID);
        explanationDocumentRepository.delete(explanationDocument);
        return "Документ был удален";
    }


    @Override
    public CreationResponse addAdministrativeOffenceCaseDecision(AdministrativeOffenceCaseDecisionAddRequest administrativeOffenceCaseDecisionAddRequest) {
        return null;
    }

    @Override
    public CreationResponse addAdministrativeOffenceCaseInvestigation(AdministrativeOffenceCaseInvestigationAddRequest administrativeOffenceCaseInvestigationAddRequest) {
        return null;
    }

    @Override
    public CreationResponse addAdministrativeOffenceCaseProtocol(AdministrativeOffenceCaseProtocolAddRequest administrativeOffenceCaseProtocolAddRequest) {
        return null;
    }

    @Override
    public CreationResponse addAdministrativeOffenceCaseRefusal(AdministrativeOffenceCaseRefusalAddRequest administrativeOffenceCaseRefusalAddRequest) {
        return null;
    }

    @Override
    public CreationResponse addAdministrativeOffenceSceneInspection(AdministrativeOffenceSceneInspectionAddRequest administrativeOffenceSceneInspectionAddRequest) {
        return null;
    }

    @Override
    public CreationResponse addAdministrativeOffenceSceneScheme(AdministrativeOffenceSceneSchemeAddRequest administrativeOffenceSceneSchemeAddRequest) {
        return null;
    }

    @Override
    public CreationResponse addConfiscationOfDocumentsProtocol(ConfiscationOfDocumentsProtocolAddRequest confiscationOfDocumentsProtocolAddRequest) {
        return null;
    }

    @Override
    public CreationResponse addExplanationDocument(ExplanationDocumentAddRequest explanationDocumentAddRequest) {
        return null;
    }

    @Override
    public String updateAdministrativeOffenceCaseDecision(AdministrativeOffenceCaseDecisionUpdateRequest administrativeOffenceCaseDecisionUpdateRequest) {
        return null;
    }

    @Override
    public String updateAdministrativeOffenceCaseInvestigation(AdministrativeOffenceCaseInvestigationUpdateRequest administrativeOffenceCaseInvestigationUpdateRequest) {
        return null;
    }

    @Override
    public String updateAdministrativeOffenceCaseProtocol(AdministrativeOffenceCaseProtocolUpdateRequest administrativeOffenceCaseProtocolUpdateRequest) {
        return null;
    }

    @Override
    public String updateAdministrativeOffenceCaseRefusal(AdministrativeOffenceCaseRefusalUpdateRequest administrativeOffenceCaseRefusalUpdateRequest) {
        return null;
    }

    @Override
    public String updateAdministrativeOffenceSceneInspection(AdministrativeOffenceSceneInspectionUpdateRequest administrativeOffenceSceneInspectionUpdateRequest) {
        return null;
    }

    @Override
    public String updateAdministrativeOffenceSceneScheme(AdministrativeOffenceSceneSchemeUpdateRequest administrativeOffenceSceneSchemeUpdateRequest) {
        return null;
    }

    @Override
    public String updateConfiscationOfDocumentsProtocol(ConfiscationOfDocumentsProtocolUpdateRequest confiscationOfDocumentsProtocolUpdateRequest) {
        return null;
    }

    @Override
    public String updateExplanationDocument(ExplanationDocumentUpdateRequest explanationDocumentUpdateRequest) {
        return null;
    }

    private CarAccidentEntityDocuments getCarAccidentDocuments(Long carAccidentDocumentsID) {

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentDocumentsID);

        if(carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        CarAccidentEntity carAccidentEntity = carAccidentEntityCandidate.get();

        Optional<CarAccidentEntityDocuments> carAccidentEntityDocumentsCandidate = carAccidentEntityDocumentsRepository.findByCarAccidentEntity(carAccidentEntity);

        if(carAccidentEntityDocumentsCandidate.isEmpty()) {
            throw new ApiRequestException("Набор документов не найден");
        }

        return carAccidentEntityDocumentsCandidate.get();
    }
}
