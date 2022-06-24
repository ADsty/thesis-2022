package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.ForbiddenApiException;
import ru.vitaliy.petrov.server.forms.requests.caraccident.CarAccidentInfoRequest;
import ru.vitaliy.petrov.server.forms.requests.documents.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.repositories.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarAccidentsDocumentsService implements ICarAccidentsDocumentsService {

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

    @Autowired
    private ChangelogService changelogService;

    @Autowired
    private WitnessesAdditionalInfoRepository witnessesAdditionalInfoRepository;

    @Autowired
    private InterviewedPersonTypeRepository interviewedPersonTypeRepository;

    @Autowired
    private UserAccidentDocumentRepository userAccidentDocumentRepository;

    @Autowired
    private UserDocumentFilesRepository userDocumentFilesRepository;

    @Autowired
    private CarAccidentWitnessRepository carAccidentWitnessRepository;

    @Override
    public CarAccidentEntityDocuments getCarAccidentDocuments(CarAccidentInfoRequest carAccidentInfoRequest) {
        Long entityID = carAccidentInfoRequest.getCarAccidentEntityID();

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        CarAccidentEntity carAccidentEntity = carAccidentEntityCandidate.get();

        Optional<CarAccidentEntityDocuments> carAccidentEntityDocumentsCandidate = carAccidentEntityDocumentsRepository.findByCarAccidentEntity(carAccidentEntity);

        if (carAccidentEntityDocumentsCandidate.isEmpty()) {
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

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не найден");
        }

        Users user = userCandidate.get();

        Optional<ConfiscationOfDocumentsProtocol> confiscationOfDocumentsProtocolCandidate = confiscationOfDocumentsProtocolRepository.findByCarAccidentEntityDocumentsAndCarAccidentParticipant(carAccidentEntityDocuments, user);

        if (confiscationOfDocumentsProtocolCandidate.isEmpty()) {
            throw new ApiRequestException("Данный документ не существует");
        }

        return confiscationOfDocumentsProtocolCandidate.get();
    }

    @Override
    public ExplanationDocument getExplanationDocument(CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не найден");
        }

        Users user = userCandidate.get();

        Optional<ExplanationDocument> explanationDocumentCandidate = explanationDocumentRepository.findByCarAccidentEntityDocumentsAndCarAccidentParticipant(carAccidentEntityDocuments, user);

        if (explanationDocumentCandidate.isEmpty()) {
            throw new ApiRequestException("Данный документ не существует");
        }

        return explanationDocumentCandidate.get();
    }

    @Override
    public String deleteAdministrativeOffenceCaseDecision(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        if (isCarAccidentClosed(carAccidentEntityDocuments.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        administrativeOffenceCaseDecisionRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceCaseDecision());
        changelogService.addNewRecord(carAccidentEntityDocuments.getCarAccidentEntity().getId(), "Постановление по делу №" + carAccidentEntityDocuments.getCarAccidentEntity().getId() + " было удалено из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceCaseInvestigation(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        if (isCarAccidentClosed(carAccidentEntityDocuments.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        administrativeOffenceCaseInvestigationRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceCaseInvestigation());
        changelogService.addNewRecord(carAccidentEntityDocuments.getCarAccidentEntity().getId(), "Определение о возбуждении дела №" + carAccidentEntityDocuments.getCarAccidentEntity().getId() + " было удалено из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceCaseProtocol(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        if (isCarAccidentClosed(carAccidentEntityDocuments.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        administrativeOffenceCaseProtocolRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceCaseProtocol());
        changelogService.addNewRecord(carAccidentEntityDocuments.getCarAccidentEntity().getId(), "Протокол об административном правонарушении по делу №" + carAccidentEntityDocuments.getCarAccidentEntity().getId() + " был удален из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceCaseRefusal(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        if (isCarAccidentClosed(carAccidentEntityDocuments.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        administrativeOffenceCaseRefusalRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceCaseRefusal());
        changelogService.addNewRecord(carAccidentEntityDocuments.getCarAccidentEntity().getId(), "Определение об отказе в возбуждении дела №" + carAccidentEntityDocuments.getCarAccidentEntity().getId() + " было удалено из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceSceneInspection(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        if (isCarAccidentClosed(carAccidentEntityDocuments.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        witnessesAdditionalInfoRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceSceneInspection().getInspectionWitnessesInfo());
        administrativeOffenceSceneInspectionRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceSceneInspection());
        changelogService.addNewRecord(carAccidentEntityDocuments.getCarAccidentEntity().getId(), "Протокол осмотра места совершения по делу №" + carAccidentEntityDocuments.getCarAccidentEntity().getId() + " был удален из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Документ был удален";
    }

    @Override
    public String deleteAdministrativeOffenceSceneScheme(CarAccidentInfoRequest carAccidentInfoRequest) {
        CarAccidentEntityDocuments carAccidentEntityDocuments = getCarAccidentDocuments(carAccidentInfoRequest);
        if (isCarAccidentClosed(carAccidentEntityDocuments.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        witnessesAdditionalInfoRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceSceneScheme().getSchemeWitnessesInfo());
        administrativeOffenceSceneSchemeRepository.delete(carAccidentEntityDocuments.getAdministrativeOffenceSceneScheme());
        changelogService.addNewRecord(carAccidentEntityDocuments.getCarAccidentEntity().getId(), "Схема места совершения дела №" + carAccidentEntityDocuments.getCarAccidentEntity().getId() + " была удалена из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Документ был удален";
    }

    @Override
    public String deleteConfiscationOfDocumentsProtocol(CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        ConfiscationOfDocumentsProtocol confiscationOfDocumentsProtocol = getConfiscationOfDocumentsProtocol(carAccidentInfoRequest, userID);
        if (isCarAccidentClosed(confiscationOfDocumentsProtocol.getCarAccidentEntityDocuments().getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        witnessesAdditionalInfoRepository.delete(confiscationOfDocumentsProtocol.getConfiscationWitnessesInfo());
        confiscationOfDocumentsProtocolRepository.delete(confiscationOfDocumentsProtocol);
        changelogService.addNewRecord(confiscationOfDocumentsProtocol.getCarAccidentEntityDocuments().getCarAccidentEntity().getId(), "Протокол об изъятии вещей и документов у участника №" + confiscationOfDocumentsProtocol.getCarAccidentParticipant().getId() + " дела №" + confiscationOfDocumentsProtocol.getCarAccidentEntityDocuments().getCarAccidentEntity().getId() + " был удален из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Документ был удален";
    }

    @Override
    public String deleteExplanationDocument(CarAccidentInfoRequest carAccidentInfoRequest, Long userID) {
        ExplanationDocument explanationDocument = getExplanationDocument(carAccidentInfoRequest, userID);
        if (isCarAccidentClosed(explanationDocument.getCarAccidentEntityDocuments().getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        explanationDocumentRepository.delete(explanationDocument);
        changelogService.addNewRecord(explanationDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId(), "Объяснение от участника №" + explanationDocument.getCarAccidentParticipant().getId() + " по делу №" + explanationDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId() + " было удалено из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Документ был удален";
    }


    @Override
    public CreationResponse addAdministrativeOffenceCaseDecision(AdministrativeOffenceCaseDecisionAddRequest administrativeOffenceCaseDecisionAddRequest) {

        final Date dateOfFill = administrativeOffenceCaseDecisionAddRequest.getDateOfFill();
        final Time timeOfFill = administrativeOffenceCaseDecisionAddRequest.getTimeOfFill();
        final String placeOfFill = administrativeOffenceCaseDecisionAddRequest.getPlaceOfFill();
        final Long policeOfficerID = administrativeOffenceCaseDecisionAddRequest.getPoliceOfficerID();
        final Long culpritID = administrativeOffenceCaseDecisionAddRequest.getCulpritID();
        final String culpritActualPlaceOfResidence = administrativeOffenceCaseDecisionAddRequest.getCulpritActualPlaceOfResidence();
        final Long carAccidentEntityID = administrativeOffenceCaseDecisionAddRequest.getCarAccidentEntityID();
        final String caseCircumstances = administrativeOffenceCaseDecisionAddRequest.getCaseCircumstances();
        final String caseDecision = administrativeOffenceCaseDecisionAddRequest.getCaseDecision();
        final Date dateOfReceiving = administrativeOffenceCaseDecisionAddRequest.getDateOfReceiving();
        final Date effectiveDate = administrativeOffenceCaseDecisionAddRequest.getEffectiveDate();

        Optional<Users> policeOfficerCandidate = usersRepository.findById(policeOfficerID);

        if (policeOfficerCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<Users> culpritCandidate = usersRepository.findById(culpritID);

        if (culpritCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        AdministrativeOffenceCaseDecision administrativeOffenceCaseDecision = AdministrativeOffenceCaseDecision
                .builder()
                .dateOfFill(dateOfFill)
                .timeOfFill(timeOfFill)
                .placeOfFill(placeOfFill)
                .trafficPoliceOfficer(policeOfficerCandidate.get())
                .carAccidentCulprit(culpritCandidate.get())
                .culpritActualPlaceOfResidence(culpritActualPlaceOfResidence)
                .carAccidentEntity(carAccidentEntityCandidate.get())
                .caseCircumstances(caseCircumstances)
                .caseDecision(caseDecision)
                .dateOfReceiving(dateOfReceiving)
                .effectiveDate(effectiveDate)
                .build();

        administrativeOffenceCaseDecisionRepository.save(administrativeOffenceCaseDecision);

        CarAccidentEntityDocuments carAccidentDocuments = getCarAccidentDocuments(new CarAccidentInfoRequest(carAccidentEntityCandidate.get().getId()));
        carAccidentDocuments.setAdministrativeOffenceCaseDecision(administrativeOffenceCaseDecision);
        carAccidentEntityDocumentsRepository.save(carAccidentDocuments);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Постановление по делу №" + administrativeOffenceCaseDecision.getId() + " было добавлено в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("AdministrativeOffenceCaseDecision", administrativeOffenceCaseDecision.getId());
    }

    @Override
    public CreationResponse addAdministrativeOffenceCaseInvestigation(AdministrativeOffenceCaseInvestigationAddRequest administrativeOffenceCaseInvestigationAddRequest) {
        final Date dateOfFill = administrativeOffenceCaseInvestigationAddRequest.getDateOfFill();
        final Time timeOfFill = administrativeOffenceCaseInvestigationAddRequest.getTimeOfFill();
        final String placeOfFill = administrativeOffenceCaseInvestigationAddRequest.getPlaceOfFill();
        final Long policeOfficerID = administrativeOffenceCaseInvestigationAddRequest.getPoliceOfficerID();
        final Long carAccidentEntityID = administrativeOffenceCaseInvestigationAddRequest.getCarAccidentEntityID();
        final String investigationReason = administrativeOffenceCaseInvestigationAddRequest.getInvestigationReason();
        final String lawViolationInfo = administrativeOffenceCaseInvestigationAddRequest.getLawViolationInfo();

        Optional<Users> policeOfficerCandidate = usersRepository.findById(policeOfficerID);

        if (policeOfficerCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        AdministrativeOffenceCaseInvestigation administrativeOffenceCaseInvestigation = AdministrativeOffenceCaseInvestigation
                .builder()
                .dateOfFill(dateOfFill)
                .timeOfFill(timeOfFill)
                .placeOfFill(placeOfFill)
                .trafficPoliceOfficer(policeOfficerCandidate.get())
                .carAccidentEntity(carAccidentEntityCandidate.get())
                .investigationReason(investigationReason)
                .lawViolationInfo(lawViolationInfo)
                .build();

        administrativeOffenceCaseInvestigationRepository.save(administrativeOffenceCaseInvestigation);

        CarAccidentEntityDocuments carAccidentDocuments = getCarAccidentDocuments(new CarAccidentInfoRequest(carAccidentEntityCandidate.get().getId()));
        carAccidentDocuments.setAdministrativeOffenceCaseInvestigation(administrativeOffenceCaseInvestigation);
        carAccidentEntityDocumentsRepository.save(carAccidentDocuments);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Определение о возбуждении дела №" + administrativeOffenceCaseInvestigation.getId() + " было добавлено в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("AdministrativeOffenceCaseInvestigation", administrativeOffenceCaseInvestigation.getId());
    }

    @Override
    public CreationResponse addAdministrativeOffenceCaseProtocol(AdministrativeOffenceCaseProtocolAddRequest administrativeOffenceCaseProtocolAddRequest) {

        final Date dateOfFill = administrativeOffenceCaseProtocolAddRequest.getDateOfFill();
        final Time timeOfFill = administrativeOffenceCaseProtocolAddRequest.getTimeOfFill();
        final String placeOfFill = administrativeOffenceCaseProtocolAddRequest.getPlaceOfFill();
        final Long policeOfficerID = administrativeOffenceCaseProtocolAddRequest.getPoliceOfficerID();
        final Long culpritID = administrativeOffenceCaseProtocolAddRequest.getCulpritID();
        final String culpritActualPlaceOfResidence = administrativeOffenceCaseProtocolAddRequest.getCulpritActualPlaceOfResidence();
        final Long carAccidentEntityID = administrativeOffenceCaseProtocolAddRequest.getCarAccidentEntityID();
        final String lawViolationInfo = administrativeOffenceCaseProtocolAddRequest.getLawViolationInfo();
        final String caseAdditionalInfo = administrativeOffenceCaseProtocolAddRequest.getCaseAdditionalInfo();
        final String placeAndTimeOfCaseExamination = administrativeOffenceCaseProtocolAddRequest.getPlaceAndTimeOfCaseExamination();
        final String changedPlaceOfCaseExamination = administrativeOffenceCaseProtocolAddRequest.getChangedPlaceOfCaseExamination();
        final String explanationsAndRemarksOfProtocol = administrativeOffenceCaseProtocolAddRequest.getExplanationsAndRemarksOfProtocol();

        Optional<Users> policeOfficerCandidate = usersRepository.findById(policeOfficerID);

        if (policeOfficerCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<Users> culpritCandidate = usersRepository.findById(culpritID);

        if (culpritCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        AdministrativeOffenceCaseProtocol administrativeOffenceCaseProtocol = AdministrativeOffenceCaseProtocol
                .builder()
                .dateOfFill(dateOfFill)
                .timeOfFill(timeOfFill)
                .placeOfFill(placeOfFill)
                .trafficPoliceOfficer(policeOfficerCandidate.get())
                .carAccidentCulprit(culpritCandidate.get())
                .culpritActualPlaceOfResidence(culpritActualPlaceOfResidence)
                .carAccidentEntity(carAccidentEntityCandidate.get())
                .lawViolationInfo(lawViolationInfo)
                .caseAdditionalInfo(caseAdditionalInfo)
                .placeAndTimeOfCaseExamination(placeAndTimeOfCaseExamination)
                .changedPlaceOfCaseExamination(changedPlaceOfCaseExamination)
                .explanationsAndRemarksOfProtocol(explanationsAndRemarksOfProtocol)
                .build();

        administrativeOffenceCaseProtocolRepository.save(administrativeOffenceCaseProtocol);

        CarAccidentEntityDocuments carAccidentDocuments = getCarAccidentDocuments(new CarAccidentInfoRequest(carAccidentEntityCandidate.get().getId()));
        carAccidentDocuments.setAdministrativeOffenceCaseProtocol(administrativeOffenceCaseProtocol);
        carAccidentEntityDocumentsRepository.save(carAccidentDocuments);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Протокол об административном правонарушении по делу №" + administrativeOffenceCaseProtocol.getId() + " был добавлен в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("AdministrativeOffenceCaseDecision", administrativeOffenceCaseProtocol.getId());
    }

    @Override
    public CreationResponse addAdministrativeOffenceCaseRefusal(AdministrativeOffenceCaseRefusalAddRequest administrativeOffenceCaseRefusalAddRequest) {

        final Date dateOfFill = administrativeOffenceCaseRefusalAddRequest.getDateOfFill();
        final Time timeOfFill = administrativeOffenceCaseRefusalAddRequest.getTimeOfFill();
        final String placeOfFill = administrativeOffenceCaseRefusalAddRequest.getPlaceOfFill();
        final Long policeOfficerID = administrativeOffenceCaseRefusalAddRequest.getPoliceOfficerID();
        final Long carAccidentInfoSender = administrativeOffenceCaseRefusalAddRequest.getCarAccidentInfoSender();
        final String carAccidentEstablishedInfo = administrativeOffenceCaseRefusalAddRequest.getCarAccidentEstablishedInfo();
        final String refusalReason = administrativeOffenceCaseRefusalAddRequest.getRefusalReason();
        final String refusalLawInfo = administrativeOffenceCaseRefusalAddRequest.getRefusalLawInfo();
        final Long carAccidentEntityID = administrativeOffenceCaseRefusalAddRequest.getCarAccidentEntityID();

        Optional<Users> policeOfficerCandidate = usersRepository.findById(policeOfficerID);

        if (policeOfficerCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<Users> carAccidentInfoSenderCandidate = usersRepository.findById(carAccidentInfoSender);

        if (carAccidentInfoSenderCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        AdministrativeOffenceCaseRefusal administrativeOffenceCaseRefusal = AdministrativeOffenceCaseRefusal
                .builder()
                .dateOfFill(dateOfFill)
                .timeOfFill(timeOfFill)
                .placeOfFill(placeOfFill)
                .trafficPoliceOfficer(policeOfficerCandidate.get())
                .carAccidentInfoSender(carAccidentInfoSenderCandidate.get())
                .carAccidentEstablishedInfo(carAccidentEstablishedInfo)
                .refusalInfo(refusalReason)
                .refusalLawInfo(refusalLawInfo)
                .build();

        administrativeOffenceCaseRefusalRepository.save(administrativeOffenceCaseRefusal);

        CarAccidentEntityDocuments carAccidentDocuments = getCarAccidentDocuments(new CarAccidentInfoRequest(carAccidentEntityCandidate.get().getId()));
        carAccidentDocuments.setAdministrativeOffenceCaseRefusal(administrativeOffenceCaseRefusal);
        carAccidentEntityDocumentsRepository.save(carAccidentDocuments);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Определение об отказе в возбуждении дела №" + administrativeOffenceCaseRefusal.getId() + " было добавлено в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("AdministrativeOffenceCaseRefusal", administrativeOffenceCaseRefusal.getId());
    }

    @Override
    public CreationResponse addAdministrativeOffenceSceneInspection(AdministrativeOffenceSceneInspectionAddRequest administrativeOffenceSceneInspectionAddRequest) {
        final Date dateOfFill = administrativeOffenceSceneInspectionAddRequest.getDateOfFill();
        final Time timeOfFill = administrativeOffenceSceneInspectionAddRequest.getTimeOfFill();
        final String placeOfFill = administrativeOffenceSceneInspectionAddRequest.getPlaceOfFill();
        final Long firstWitnessID = administrativeOffenceSceneInspectionAddRequest.getFirstWitnessID();
        final Long secondWitnessID = administrativeOffenceSceneInspectionAddRequest.getSecondWitnessID();
        final Long policeOfficerID = administrativeOffenceSceneInspectionAddRequest.getPoliceOfficerID();
        final Long carAccidentEntityID = administrativeOffenceSceneInspectionAddRequest.getCarAccidentEntityID();
        final Boolean cameraUsage = administrativeOffenceSceneInspectionAddRequest.getCameraUsage();
        final String sceneInspectionInfo = administrativeOffenceSceneInspectionAddRequest.getSceneInspectionInfo();

        Optional<CarAccidentWitness> firstWitnessCandidate = carAccidentWitnessRepository.findById(firstWitnessID);
        Optional<CarAccidentWitness> secondWitnessCandidate = carAccidentWitnessRepository.findById(secondWitnessID);

        if (firstWitnessCandidate.isEmpty() || secondWitnessCandidate.isEmpty()) {
            throw new ApiRequestException("Свидетели ДТП не были найдены");
        }

        Optional<Users> policeOfficerCandidate = usersRepository.findById(policeOfficerID);

        if (policeOfficerCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        WitnessesAdditionalInfo witnessesAdditionalInfo = WitnessesAdditionalInfo
                .builder()
                .firstWitness(firstWitnessCandidate.get())
                .secondWitness(secondWitnessCandidate.get())
                .build();

        witnessesAdditionalInfoRepository.save(witnessesAdditionalInfo);

        AdministrativeOffenceSceneInspection administrativeOffenceSceneInspection = AdministrativeOffenceSceneInspection
                .builder()
                .dateOfFill(dateOfFill)
                .timeOfFill(timeOfFill)
                .placeOfFill(placeOfFill)
                .trafficPoliceOfficer(policeOfficerCandidate.get())
                .cameraUsage(cameraUsage)
                .inspectionWitnessesInfo(witnessesAdditionalInfo)
                .sceneInspectionInfo(sceneInspectionInfo)
                .build();

        administrativeOffenceSceneInspectionRepository.save(administrativeOffenceSceneInspection);

        CarAccidentEntityDocuments carAccidentDocuments = getCarAccidentDocuments(new CarAccidentInfoRequest(carAccidentEntityCandidate.get().getId()));
        carAccidentDocuments.setAdministrativeOffenceSceneInspection(administrativeOffenceSceneInspection);
        carAccidentEntityDocumentsRepository.save(carAccidentDocuments);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Протокол осмотра места совершения по делу №" + administrativeOffenceSceneInspection.getId() + " был добавлен в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("AdministrativeOffenceSceneInspection", administrativeOffenceSceneInspection.getId());
    }

    @Override
    public CreationResponse addAdministrativeOffenceSceneScheme(AdministrativeOffenceSceneSchemeAddRequest administrativeOffenceSceneSchemeAddRequest) {
        final Date dateOfFill = administrativeOffenceSceneSchemeAddRequest.getDateOfFill();
        final Time timeOfFill = administrativeOffenceSceneSchemeAddRequest.getTimeOfFill();
        final String placeOfFill = administrativeOffenceSceneSchemeAddRequest.getPlaceOfFill();
        final Long firstWitnessID = administrativeOffenceSceneSchemeAddRequest.getFirstWitnessID();
        final Long secondWitnessID = administrativeOffenceSceneSchemeAddRequest.getSecondWitnessID();
        final Long policeOfficerID = administrativeOffenceSceneSchemeAddRequest.getPoliceOfficerID();
        final Long carAccidentEntityID = administrativeOffenceSceneSchemeAddRequest.getCarAccidentEntityID();
        final String schemeConventions = administrativeOffenceSceneSchemeAddRequest.getSchemeConventions();
        final String fileLink = administrativeOffenceSceneSchemeAddRequest.getFileLink();

        Optional<CarAccidentWitness> firstWitnessCandidate = carAccidentWitnessRepository.findById(firstWitnessID);
        Optional<CarAccidentWitness> secondWitnessCandidate = carAccidentWitnessRepository.findById(secondWitnessID);

        if (firstWitnessCandidate.isEmpty() || secondWitnessCandidate.isEmpty()) {
            throw new ApiRequestException("Свидетели ДТП не были найдены");
        }

        Optional<Users> policeOfficerCandidate = usersRepository.findById(policeOfficerID);

        if (policeOfficerCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        WitnessesAdditionalInfo witnessesAdditionalInfo = WitnessesAdditionalInfo
                .builder()
                .firstWitness(firstWitnessCandidate.get())
                .secondWitness(secondWitnessCandidate.get())
                .build();

        witnessesAdditionalInfoRepository.save(witnessesAdditionalInfo);

        AdministrativeOffenceSceneScheme administrativeOffenceSceneScheme = AdministrativeOffenceSceneScheme
                .builder()
                .dateOfFill(dateOfFill)
                .timeOfFill(timeOfFill)
                .placeOfFill(placeOfFill)
                .trafficPoliceOfficer(policeOfficerCandidate.get())
                .schemeFileLink(fileLink)
                .schemeConventions(schemeConventions)
                .schemeWitnessesInfo(witnessesAdditionalInfo)
                .build();

        administrativeOffenceSceneSchemeRepository.save(administrativeOffenceSceneScheme);

        CarAccidentEntityDocuments carAccidentDocuments = getCarAccidentDocuments(new CarAccidentInfoRequest(carAccidentEntityCandidate.get().getId()));
        carAccidentDocuments.setAdministrativeOffenceSceneScheme(administrativeOffenceSceneScheme);
        carAccidentEntityDocumentsRepository.save(carAccidentDocuments);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Схема места совершения дела №" + administrativeOffenceSceneScheme.getId() + " была добавлена в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("AdministrativeOffenceSceneScheme", administrativeOffenceSceneScheme.getId());
    }

    @Override
    public CreationResponse addConfiscationOfDocumentsProtocol(ConfiscationOfDocumentsProtocolAddRequest confiscationOfDocumentsProtocolAddRequest) {
        final Date dateOfFill = confiscationOfDocumentsProtocolAddRequest.getDateOfFill();
        final Time timeOfFill = confiscationOfDocumentsProtocolAddRequest.getTimeOfFill();
        final String placeOfFill = confiscationOfDocumentsProtocolAddRequest.getPlaceOfFill();
        final Long policeOfficerID = confiscationOfDocumentsProtocolAddRequest.getPoliceOfficerID();
        final Long carAccidentEntityID = confiscationOfDocumentsProtocolAddRequest.getCarAccidentEntityID();
        final Long firstWitnessID = confiscationOfDocumentsProtocolAddRequest.getFirstWitnessID();
        final Long secondWitnessID = confiscationOfDocumentsProtocolAddRequest.getSecondWitnessID();
        final Long carAccidentParticipant = confiscationOfDocumentsProtocolAddRequest.getCarAccidentParticipant();
        final String confiscationReason = confiscationOfDocumentsProtocolAddRequest.getConfiscationReason();
        final String confiscatedDocumentsInfo = confiscationOfDocumentsProtocolAddRequest.getConfiscatedDocumentsInfo();
        final String confiscationProcessFixationMethod = confiscationOfDocumentsProtocolAddRequest.getConfiscationProcessFixationMethod();

        Optional<CarAccidentWitness> firstWitnessCandidate = carAccidentWitnessRepository.findById(firstWitnessID);
        Optional<CarAccidentWitness> secondWitnessCandidate = carAccidentWitnessRepository.findById(secondWitnessID);

        if (firstWitnessCandidate.isEmpty() || secondWitnessCandidate.isEmpty()) {
            throw new ApiRequestException("Свидетели ДТП не были найдены");
        }

        Optional<Users> policeOfficerCandidate = usersRepository.findById(policeOfficerID);

        if (policeOfficerCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<Users> userCandidate = usersRepository.findById(carAccidentParticipant);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        WitnessesAdditionalInfo witnessesAdditionalInfo = WitnessesAdditionalInfo
                .builder()
                .firstWitness(firstWitnessCandidate.get())
                .secondWitness(secondWitnessCandidate.get())
                .build();

        witnessesAdditionalInfoRepository.save(witnessesAdditionalInfo);

        CarAccidentEntityDocuments carAccidentDocuments = getCarAccidentDocuments(new CarAccidentInfoRequest(carAccidentEntityCandidate.get().getId()));

        ConfiscationOfDocumentsProtocol confiscationOfDocumentsProtocol = ConfiscationOfDocumentsProtocol
                .builder()
                .carAccidentEntityDocuments(carAccidentDocuments)
                .dateOfFill(dateOfFill)
                .timeOfFill(timeOfFill)
                .placeOfFill(placeOfFill)
                .trafficPoliceOfficer(policeOfficerCandidate.get())
                .carAccidentParticipant(userCandidate.get())
                .confiscationReason(confiscationReason)
                .confiscatedDocumentsInfo(confiscatedDocumentsInfo)
                .confiscationProcessFixationMethod(confiscationProcessFixationMethod)
                .confiscationWitnessesInfo(witnessesAdditionalInfo)
                .build();

        confiscationOfDocumentsProtocolRepository.save(confiscationOfDocumentsProtocol);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Протокол об изъятии вещей и документов у участника №" + confiscationOfDocumentsProtocol.getCarAccidentParticipant().getId() + " по делу №" + confiscationOfDocumentsProtocol.getId() + " был добавлен в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("ConfiscationOfDocumentsProtocol", confiscationOfDocumentsProtocol.getId());
    }

    @Override
    public CreationResponse addExplanationDocument(ExplanationDocumentAddRequest explanationDocumentAddRequest) {
        final Date dateOfFill = explanationDocumentAddRequest.getDateOfFill();
        final Time timeOfFill = explanationDocumentAddRequest.getTimeOfFill();
        final String placeOfFill = explanationDocumentAddRequest.getPlaceOfFill();
        final Long policeOfficerID = explanationDocumentAddRequest.getPoliceOfficerID();
        final Long carAccidentEntityID = explanationDocumentAddRequest.getCarAccidentEntityID();
        final Long carAccidentParticipant = explanationDocumentAddRequest.getCarAccidentParticipant();
        final Long carAccidentWitness = explanationDocumentAddRequest.getCarAccidentWitness();
        final String interviewedPersonType = explanationDocumentAddRequest.getInterviewedPersonType();

        Optional<Users> policeOfficerCandidate = usersRepository.findById(policeOfficerID);

        if (policeOfficerCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<Users> userCandidate = usersRepository.findById(carAccidentParticipant);
        Optional<CarAccidentWitness> witnessCandidate = carAccidentWitnessRepository.findById(carAccidentWitness);

        if (userCandidate.isEmpty() && witnessCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Users user;
        CarAccidentWitness witness;

        if (userCandidate.isEmpty()) {
            user = null;
        } else {
            user = userCandidate.get();
        }

        if (witnessCandidate.isEmpty()) {
            witness = null;
        } else {
            witness = witnessCandidate.get();
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<InterviewedPersonType> interviewedPersonTypeCandidate = interviewedPersonTypeRepository.findByInterviewedPersonType(interviewedPersonType);

        if (interviewedPersonTypeCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные ДТП неверны");
        }

        CarAccidentEntityDocuments carAccidentDocuments = getCarAccidentDocuments(new CarAccidentInfoRequest(carAccidentEntityCandidate.get().getId()));

        ExplanationDocument explanationDocument = ExplanationDocument
                .builder()
                .carAccidentEntityDocuments(carAccidentDocuments)
                .dateOfFill(dateOfFill)
                .timeOfFill(timeOfFill)
                .placeOfFill(placeOfFill)
                .trafficPoliceOfficer(policeOfficerCandidate.get())
                .carAccidentParticipant(user)
                .carAccidentWitness(witness)
                .interviewedPersonType(interviewedPersonTypeCandidate.get())
                .build();

        explanationDocumentRepository.save(explanationDocument);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Объяснение от участника №" + explanationDocument.getCarAccidentParticipant().getId() + " по делу №" + explanationDocument.getId() + " было добавлено в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("ExplanationDocument", explanationDocument.getId());
    }

    @Override
    public String updateAdministrativeOffenceCaseDecision(AdministrativeOffenceCaseDecisionUpdateRequest administrativeOffenceCaseDecisionUpdateRequest) {

        final Long documentID = administrativeOffenceCaseDecisionUpdateRequest.getDocumentID();
        final Date dateOfFill = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedDateOfFill();
        final Time timeOfFill = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedTimeOfFill();
        final String placeOfFill = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedPlaceOfFill();
        final Long culpritID = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedCulpritID();
        final String culpritActualPlaceOfResidence = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedCulpritActualPlaceOfResidence();
        final Long carAccidentEntityID = administrativeOffenceCaseDecisionUpdateRequest.getCarAccidentEntityID();
        final String caseCircumstances = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedCaseCircumstances();
        final String caseDecision = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedCaseDecision();
        final Date dateOfReceiving = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedDateOfReceiving();
        final Date effectiveDate = administrativeOffenceCaseDecisionUpdateRequest.getUpdatedEffectiveDate();

        Optional<Users> culpritCandidate = usersRepository.findById(culpritID);

        if (culpritCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<AdministrativeOffenceCaseDecision> administrativeOffenceCaseDecisionCandidate = administrativeOffenceCaseDecisionRepository.findById(documentID);

        if (administrativeOffenceCaseDecisionCandidate.isEmpty()) {
            throw new ApiRequestException("Нужный документ не найден");
        }

        AdministrativeOffenceCaseDecision administrativeOffenceCaseDecision = administrativeOffenceCaseDecisionCandidate.get();

        administrativeOffenceCaseDecision.setDateOfFill(dateOfFill);
        administrativeOffenceCaseDecision.setTimeOfFill(timeOfFill);
        administrativeOffenceCaseDecision.setPlaceOfFill(placeOfFill);
        administrativeOffenceCaseDecision.setCarAccidentCulprit(culpritCandidate.get());

        administrativeOffenceCaseDecision.setCaseCircumstances(caseCircumstances);
        administrativeOffenceCaseDecision.setCulpritActualPlaceOfResidence(culpritActualPlaceOfResidence);
        administrativeOffenceCaseDecision.setCaseDecision(caseDecision);
        administrativeOffenceCaseDecision.setDateOfReceiving(dateOfReceiving);
        administrativeOffenceCaseDecision.setEffectiveDate(effectiveDate);

        administrativeOffenceCaseDecisionRepository.save(administrativeOffenceCaseDecision);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Постановление по делу №" + administrativeOffenceCaseDecision.getId() + " было обновлено в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Документ был обновлен";
    }

    @Override
    public String updateAdministrativeOffenceCaseInvestigation(AdministrativeOffenceCaseInvestigationUpdateRequest administrativeOffenceCaseInvestigationUpdateRequest) {

        final Long documentID = administrativeOffenceCaseInvestigationUpdateRequest.getDocumentID();
        final Date dateOfFill = administrativeOffenceCaseInvestigationUpdateRequest.getUpdatedDateOfFill();
        final Time timeOfFill = administrativeOffenceCaseInvestigationUpdateRequest.getUpdatedTimeOfFill();
        final String placeOfFill = administrativeOffenceCaseInvestigationUpdateRequest.getUpdatedPlaceOfFill();
        final Long carAccidentEntityID = administrativeOffenceCaseInvestigationUpdateRequest.getCarAccidentEntityID();
        final String investigationReason = administrativeOffenceCaseInvestigationUpdateRequest.getUpdatedInvestigationReason();
        final String lawViolationInfo = administrativeOffenceCaseInvestigationUpdateRequest.getUpdatedLawViolationInfo();

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<AdministrativeOffenceCaseInvestigation> administrativeOffenceCaseInvestigationCandidate = administrativeOffenceCaseInvestigationRepository.findById(documentID);

        if (administrativeOffenceCaseInvestigationCandidate.isEmpty()) {
            throw new ApiRequestException("Нужный документ не найден");
        }

        AdministrativeOffenceCaseInvestigation administrativeOffenceCaseInvestigation = administrativeOffenceCaseInvestigationCandidate.get();

        administrativeOffenceCaseInvestigation.setDateOfFill(dateOfFill);
        administrativeOffenceCaseInvestigation.setTimeOfFill(timeOfFill);
        administrativeOffenceCaseInvestigation.setPlaceOfFill(placeOfFill);
        administrativeOffenceCaseInvestigation.setInvestigationReason(investigationReason);
        administrativeOffenceCaseInvestigation.setLawViolationInfo(lawViolationInfo);

        administrativeOffenceCaseInvestigationRepository.save(administrativeOffenceCaseInvestigation);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Определение о возбуждении дела №" + administrativeOffenceCaseInvestigation.getId() + " было обновлено в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Документ был обновлен";
    }

    @Override
    public String updateAdministrativeOffenceCaseProtocol(AdministrativeOffenceCaseProtocolUpdateRequest administrativeOffenceCaseProtocolUpdateRequest) {

        final Long documentID = administrativeOffenceCaseProtocolUpdateRequest.getDocumentID();
        final Date dateOfFill = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedDateOfFill();
        final Time timeOfFill = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedTimeOfFill();
        final String placeOfFill = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedPlaceOfFill();
        final String culpritActualPlaceOfResidence = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedCulpritActualPlaceOfResidence();
        final Long carAccidentEntityID = administrativeOffenceCaseProtocolUpdateRequest.getCarAccidentEntityID();
        final String lawViolationInfo = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedLawViolationInfo();
        final String caseAdditionalInfo = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedCaseAdditionalInfo();
        final String placeAndTimeOfCaseExamination = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedPlaceAndTimeOfCaseExamination();
        final String changedPlaceOfCaseExamination = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedChangedPlaceOfCaseExamination();
        final String explanationsAndRemarksOfProtocol = administrativeOffenceCaseProtocolUpdateRequest.getUpdatedExplanationsAndRemarksOfProtocol();

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<AdministrativeOffenceCaseProtocol> administrativeOffenceCaseProtocolCandidate = administrativeOffenceCaseProtocolRepository.findById(documentID);

        if (administrativeOffenceCaseProtocolCandidate.isEmpty()) {
            throw new ApiRequestException("Нужный документ не найден");
        }

        AdministrativeOffenceCaseProtocol administrativeOffenceCaseProtocol = administrativeOffenceCaseProtocolCandidate.get();

        administrativeOffenceCaseProtocol.setDateOfFill(dateOfFill);
        administrativeOffenceCaseProtocol.setTimeOfFill(timeOfFill);
        administrativeOffenceCaseProtocol.setPlaceOfFill(placeOfFill);

        administrativeOffenceCaseProtocol.setLawViolationInfo(lawViolationInfo);
        administrativeOffenceCaseProtocol.setCaseAdditionalInfo(caseAdditionalInfo);
        administrativeOffenceCaseProtocol.setCulpritActualPlaceOfResidence(culpritActualPlaceOfResidence);
        administrativeOffenceCaseProtocol.setPlaceAndTimeOfCaseExamination(placeAndTimeOfCaseExamination);
        administrativeOffenceCaseProtocol.setChangedPlaceOfCaseExamination(changedPlaceOfCaseExamination);
        administrativeOffenceCaseProtocol.setExplanationsAndRemarksOfProtocol(explanationsAndRemarksOfProtocol);

        administrativeOffenceCaseProtocolRepository.save(administrativeOffenceCaseProtocol);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Протокол об административном правонарушении по делу №" + administrativeOffenceCaseProtocol.getId() + " был обновлен в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Документ был обновлен";
    }

    @Override
    public String updateAdministrativeOffenceCaseRefusal(AdministrativeOffenceCaseRefusalUpdateRequest administrativeOffenceCaseRefusalUpdateRequest) {

        final Long documentID = administrativeOffenceCaseRefusalUpdateRequest.getDocumentID();
        final Date dateOfFill = administrativeOffenceCaseRefusalUpdateRequest.getUpdatedDateOfFill();
        final Time timeOfFill = administrativeOffenceCaseRefusalUpdateRequest.getUpdatedTimeOfFill();
        final String placeOfFill = administrativeOffenceCaseRefusalUpdateRequest.getUpdatedPlaceOfFill();
        final String carAccidentEstablishedInfo = administrativeOffenceCaseRefusalUpdateRequest.getUpdatedCarAccidentEstablishedInfo();
        final String refusalReason = administrativeOffenceCaseRefusalUpdateRequest.getUpdatedRefusalReason();
        final String refusalLawInfo = administrativeOffenceCaseRefusalUpdateRequest.getUpdatedRefusalLawInfo();
        final Long carAccidentEntityID = administrativeOffenceCaseRefusalUpdateRequest.getCarAccidentEntityID();

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<AdministrativeOffenceCaseRefusal> administrativeOffenceCaseRefusalCandidate = administrativeOffenceCaseRefusalRepository.findById(documentID);

        if (administrativeOffenceCaseRefusalCandidate.isEmpty()) {
            throw new ApiRequestException("Нужный документ не найден");
        }

        AdministrativeOffenceCaseRefusal administrativeOffenceCaseRefusal = administrativeOffenceCaseRefusalCandidate.get();

        administrativeOffenceCaseRefusal.setDateOfFill(dateOfFill);
        administrativeOffenceCaseRefusal.setTimeOfFill(timeOfFill);
        administrativeOffenceCaseRefusal.setPlaceOfFill(placeOfFill);
        administrativeOffenceCaseRefusal.setCarAccidentEstablishedInfo(carAccidentEstablishedInfo);
        administrativeOffenceCaseRefusal.setRefusalInfo(refusalReason);
        administrativeOffenceCaseRefusal.setRefusalLawInfo(refusalLawInfo);

        administrativeOffenceCaseRefusalRepository.save(administrativeOffenceCaseRefusal);


        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Определение об отказе в возбуждении дела №" + administrativeOffenceCaseRefusal.getId() + " было обновлено в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Документ был обновлен";
    }

    @Override
    public String updateAdministrativeOffenceSceneInspection(AdministrativeOffenceSceneInspectionUpdateRequest administrativeOffenceSceneInspectionUpdateRequest) {

        final Long documentID = administrativeOffenceSceneInspectionUpdateRequest.getDocumentID();
        final Date dateOfFill = administrativeOffenceSceneInspectionUpdateRequest.getUpdatedDateOfFill();
        final Time timeOfFill = administrativeOffenceSceneInspectionUpdateRequest.getUpdatedTimeOfFill();
        final String placeOfFill = administrativeOffenceSceneInspectionUpdateRequest.getUpdatedPlaceOfFill();
        final Long carAccidentEntityID = administrativeOffenceSceneInspectionUpdateRequest.getCarAccidentEntityID();
        final Boolean cameraUsage = administrativeOffenceSceneInspectionUpdateRequest.getUpdatedCameraUsage();
        final Long firstWitnessID = administrativeOffenceSceneInspectionUpdateRequest.getUpdatedFirstWitnessID();
        final Long secondWitnessID = administrativeOffenceSceneInspectionUpdateRequest.getUpdatedSecondWitnessID();
        final String sceneInspectionInfo = administrativeOffenceSceneInspectionUpdateRequest.getUpdatedSceneInspectionInfo();

        Optional<CarAccidentWitness> firstWitnessCandidate = carAccidentWitnessRepository.findById(firstWitnessID);
        Optional<CarAccidentWitness> secondWitnessCandidate = carAccidentWitnessRepository.findById(secondWitnessID);

        if (firstWitnessCandidate.isEmpty() || secondWitnessCandidate.isEmpty()) {
            throw new ApiRequestException("Свидетели ДТП не были найдены");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<AdministrativeOffenceSceneInspection> administrativeOffenceSceneInspectionCandidate = administrativeOffenceSceneInspectionRepository.findById(documentID);

        if (administrativeOffenceSceneInspectionCandidate.isEmpty()) {
            throw new ApiRequestException("Нужный документ не найден");
        }

        AdministrativeOffenceSceneInspection administrativeOffenceSceneInspection = administrativeOffenceSceneInspectionCandidate.get();

        administrativeOffenceSceneInspection.setDateOfFill(dateOfFill);
        administrativeOffenceSceneInspection.setTimeOfFill(timeOfFill);
        administrativeOffenceSceneInspection.setPlaceOfFill(placeOfFill);
        administrativeOffenceSceneInspection.setCameraUsage(cameraUsage);

        WitnessesAdditionalInfo witnessesAdditionalInfo = administrativeOffenceSceneInspection.getInspectionWitnessesInfo();
        witnessesAdditionalInfo.setFirstWitness(firstWitnessCandidate.get());
        witnessesAdditionalInfo.setSecondWitness(secondWitnessCandidate.get());
        witnessesAdditionalInfoRepository.save(witnessesAdditionalInfo);

        administrativeOffenceSceneInspection.setSceneInspectionInfo(sceneInspectionInfo);

        administrativeOffenceSceneInspectionRepository.save(administrativeOffenceSceneInspection);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Протокол осмотра места совершения по делу №" + administrativeOffenceSceneInspection.getId() + " был обновлен в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Документ был обновлен";
    }

    @Override
    public String updateAdministrativeOffenceSceneScheme(AdministrativeOffenceSceneSchemeUpdateRequest administrativeOffenceSceneSchemeUpdateRequest) {

        final Long documentID = administrativeOffenceSceneSchemeUpdateRequest.getDocumentID();
        final Date dateOfFill = administrativeOffenceSceneSchemeUpdateRequest.getUpdatedDateOfFill();
        final Time timeOfFill = administrativeOffenceSceneSchemeUpdateRequest.getUpdatedTimeOfFill();
        final String placeOfFill = administrativeOffenceSceneSchemeUpdateRequest.getUpdatedPlaceOfFill();
        final Long carAccidentEntityID = administrativeOffenceSceneSchemeUpdateRequest.getCarAccidentEntityID();
        final Long firstWitnessID = administrativeOffenceSceneSchemeUpdateRequest.getUpdatedFirstWitnessID();
        final Long secondWitnessID = administrativeOffenceSceneSchemeUpdateRequest.getUpdatedSecondWitnessID();
        final String schemeConventions = administrativeOffenceSceneSchemeUpdateRequest.getUpdatedSchemeConventions();
        final String fileLink = administrativeOffenceSceneSchemeUpdateRequest.getFileLink();

        Optional<CarAccidentWitness> firstWitnessCandidate = carAccidentWitnessRepository.findById(firstWitnessID);
        Optional<CarAccidentWitness> secondWitnessCandidate = carAccidentWitnessRepository.findById(secondWitnessID);

        if (firstWitnessCandidate.isEmpty() || secondWitnessCandidate.isEmpty()) {
            throw new ApiRequestException("Свидетели ДТП не были найдены");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<AdministrativeOffenceSceneScheme> administrativeOffenceSceneSchemeCandidate = administrativeOffenceSceneSchemeRepository.findById(documentID);

        if (administrativeOffenceSceneSchemeCandidate.isEmpty()) {
            throw new ApiRequestException("Нужный документ не найден");
        }

        AdministrativeOffenceSceneScheme administrativeOffenceSceneScheme = administrativeOffenceSceneSchemeCandidate.get();

        administrativeOffenceSceneScheme.setDateOfFill(dateOfFill);
        administrativeOffenceSceneScheme.setTimeOfFill(timeOfFill);
        administrativeOffenceSceneScheme.setPlaceOfFill(placeOfFill);

        WitnessesAdditionalInfo witnessesAdditionalInfo = administrativeOffenceSceneScheme.getSchemeWitnessesInfo();
        witnessesAdditionalInfo.setFirstWitness(firstWitnessCandidate.get());
        witnessesAdditionalInfo.setSecondWitness(secondWitnessCandidate.get());
        witnessesAdditionalInfoRepository.save(witnessesAdditionalInfo);

        administrativeOffenceSceneScheme.setSchemeConventions(schemeConventions);
        administrativeOffenceSceneScheme.setSchemeFileLink(fileLink);

        administrativeOffenceSceneSchemeRepository.save(administrativeOffenceSceneScheme);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Схема места совершения дела №" + administrativeOffenceSceneScheme.getId() + " была обновлена в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Документ был обновлен";
    }

    @Override
    public String updateConfiscationOfDocumentsProtocol(ConfiscationOfDocumentsProtocolUpdateRequest confiscationOfDocumentsProtocolUpdateRequest) {

        final Long documentID = confiscationOfDocumentsProtocolUpdateRequest.getDocumentID();
        final Date dateOfFill = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedDateOfFill();
        final Time timeOfFill = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedTimeOfFill();
        final String placeOfFill = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedPlaceOfFill();
        final Long carAccidentEntityID = confiscationOfDocumentsProtocolUpdateRequest.getCarAccidentEntityID();
        final Long firstWitnessID = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedFirstWitnessID();
        final Long secondWitnessID = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedSecondWitnessID();
        final Long carAccidentParticipant = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedCarAccidentParticipant();
        final String confiscationReason = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedConfiscationReason();
        final String confiscatedDocumentsInfo = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedConfiscatedDocumentsInfo();
        final String confiscationProcessFixationMethod = confiscationOfDocumentsProtocolUpdateRequest.getUpdatedConfiscationProcessFixationMethod();

        Optional<CarAccidentWitness> firstWitnessCandidate = carAccidentWitnessRepository.findById(firstWitnessID);
        Optional<CarAccidentWitness> secondWitnessCandidate = carAccidentWitnessRepository.findById(secondWitnessID);

        if (firstWitnessCandidate.isEmpty() || secondWitnessCandidate.isEmpty()) {
            throw new ApiRequestException("Свидетели ДТП не были найдены");
        }

        Optional<Users> userCandidate = usersRepository.findById(carAccidentParticipant);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<ConfiscationOfDocumentsProtocol> confiscationOfDocumentsProtocolCandidate = confiscationOfDocumentsProtocolRepository.findById(documentID);

        if (confiscationOfDocumentsProtocolCandidate.isEmpty()) {
            throw new ApiRequestException("Нужный документ не найден");
        }

        ConfiscationOfDocumentsProtocol confiscationOfDocumentsProtocol = confiscationOfDocumentsProtocolCandidate.get();

        confiscationOfDocumentsProtocol.setDateOfFill(dateOfFill);
        confiscationOfDocumentsProtocol.setTimeOfFill(timeOfFill);
        confiscationOfDocumentsProtocol.setPlaceOfFill(placeOfFill);

        WitnessesAdditionalInfo witnessesAdditionalInfo = confiscationOfDocumentsProtocol.getConfiscationWitnessesInfo();
        witnessesAdditionalInfo.setFirstWitness(firstWitnessCandidate.get());
        witnessesAdditionalInfo.setSecondWitness(secondWitnessCandidate.get());
        witnessesAdditionalInfoRepository.save(witnessesAdditionalInfo);

        confiscationOfDocumentsProtocol.setConfiscationReason(confiscationReason);
        confiscationOfDocumentsProtocol.setConfiscatedDocumentsInfo(confiscatedDocumentsInfo);
        confiscationOfDocumentsProtocol.setConfiscationProcessFixationMethod(confiscationProcessFixationMethod);
        confiscationOfDocumentsProtocol.setCarAccidentParticipant(userCandidate.get());

        confiscationOfDocumentsProtocolRepository.save(confiscationOfDocumentsProtocol);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Протокол об изъятии вещей и документов у участника №" + confiscationOfDocumentsProtocol.getCarAccidentParticipant().getId() + " по делу №" + confiscationOfDocumentsProtocol.getId() + " был обновлен в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Документ был обновлен";
    }

    @Override
    public String updateExplanationDocument(ExplanationDocumentUpdateRequest explanationDocumentUpdateRequest) {

        final Long documentID = explanationDocumentUpdateRequest.getDocumentID();
        final Date dateOfFill = explanationDocumentUpdateRequest.getUpdatedDateOfFill();
        final Time timeOfFill = explanationDocumentUpdateRequest.getUpdatedTimeOfFill();
        final String placeOfFill = explanationDocumentUpdateRequest.getUpdatedPlaceOfFill();
        final Long carAccidentEntityID = explanationDocumentUpdateRequest.getCarAccidentEntityID();
        final String interviewedPersonType = explanationDocumentUpdateRequest.getUpdatedInterviewedPersonType();


        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<InterviewedPersonType> interviewedPersonTypeCandidate = interviewedPersonTypeRepository.findByInterviewedPersonType(interviewedPersonType);

        if (interviewedPersonTypeCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<ExplanationDocument> explanationDocumentCandidate = explanationDocumentRepository.findById(documentID);

        if (explanationDocumentCandidate.isEmpty()) {
            throw new ApiRequestException("Нужный документ не найден");
        }

        ExplanationDocument explanationDocument = explanationDocumentCandidate.get();

        explanationDocument.setDateOfFill(dateOfFill);
        explanationDocument.setTimeOfFill(timeOfFill);
        explanationDocument.setPlaceOfFill(placeOfFill);
        explanationDocument.setInterviewedPersonType(interviewedPersonTypeCandidate.get());

        explanationDocumentRepository.save(explanationDocument);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Объяснение от участника №" + explanationDocument.getCarAccidentParticipant().getId() + " по делу №" + explanationDocument.getId() + " было обновлено в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Документ был обновлен";
    }

    private boolean isCarAccidentClosed(CarAccidentEntity carAccidentEntity) {
        return carAccidentEntity.getEntityState().getCarAccidentEntityState().equals("Закрытое заявление");
    }

    public CreationResponse addUserAccidentDocument(UserAccidentDocumentAddRequest userAccidentDocumentAddRequest, Long userID) {

        final Long carAccidentEntityID = userAccidentDocumentAddRequest.getCarAccidentEntityID();
        final Date sendDate = userAccidentDocumentAddRequest.getSendDate();
        final Time sendTime = userAccidentDocumentAddRequest.getSendTime();
        final String explanationText = userAccidentDocumentAddRequest.getExplanationText();

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(carAccidentEntityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(carAccidentEntityCandidate.get())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        Optional<CarAccidentEntityDocuments> carAccidentEntityDocuments = carAccidentEntityDocumentsRepository.findByCarAccidentEntity(carAccidentEntityCandidate.get());

        if (carAccidentEntityDocuments.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь, создавший запрос, не был найден");
        }

        UserAccidentDocument userAccidentDocument = UserAccidentDocument
                .builder()
                .carAccidentEntityDocuments(carAccidentEntityDocuments.get())
                .sendDate(sendDate)
                .sendTime(sendTime)
                .senderParticipant(userCandidate.get())
                .explanationText(explanationText)
                .build();

        userAccidentDocumentRepository.save(userAccidentDocument);

        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "Заявление от участника №" + userAccidentDocument.getSenderParticipant().getId() + " по делу №" + userAccidentDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId() + " было добавлено в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("UserAccidentDocument", userAccidentDocument.getId());
    }

    public CreationResponse addUserDocumentsFile(UserDocumentsFileAddRequest userDocumentsFileAddRequest, Long userID) {

        final Long userAccidentDocument = userDocumentsFileAddRequest.getUserAccidentDocument();
        final String fileLink = userDocumentsFileAddRequest.getFileLink();

        Optional<UserAccidentDocument> userAccidentDocumentCandidate = userAccidentDocumentRepository.findById(userAccidentDocument);

        if (userAccidentDocumentCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (isCarAccidentClosed(userAccidentDocumentCandidate.get().getCarAccidentEntityDocuments().getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }

        if (!userAccidentDocumentCandidate.get().getSenderParticipant().getId().equals(userID)) {
            throw new ForbiddenApiException("У вас нет доступа к данному заявлению");
        }

        UserDocumentFiles userDocumentFiles = UserDocumentFiles
                .builder()
                .userAccidentDocument(userAccidentDocumentCandidate.get())
                .fileLink(fileLink)
                .build();

        userDocumentFilesRepository.save(userDocumentFiles);

        changelogService.addNewRecord(userDocumentFiles.getUserAccidentDocument().getCarAccidentEntityDocuments().getCarAccidentEntity().getId(), "Файл от участника №" + userDocumentFiles.getUserAccidentDocument().getSenderParticipant().getId() + " по заявлению №" + userDocumentFiles.getUserAccidentDocument().getId() + " был добавлен в систему", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("UserDocumentFiles", userDocumentFiles.getId());
    }

    public UserAccidentDocument getUserAccidentDocument(Long entityID, Long userID) {

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<CarAccidentEntityDocuments> carAccidentEntityDocuments = carAccidentEntityDocumentsRepository.findByCarAccidentEntity(carAccidentEntityCandidate.get());

        if (carAccidentEntityDocuments.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь, создавший запрос, не был найден");
        }

        Optional<UserAccidentDocument> userAccidentDocumentCandidate = userAccidentDocumentRepository.findBySenderParticipantAndCarAccidentEntityDocuments(userCandidate.get(), carAccidentEntityDocuments.get());

        if (userAccidentDocumentCandidate.isEmpty()) {
            throw new ApiRequestException("Заявление от данного пользователя не было найдено");
        }

        return userAccidentDocumentCandidate.get();
    }

    public List<UserDocumentFiles> getAllUserDocumentsFiles(Long entityID, Long userID) {

        Optional<List<UserDocumentFiles>> userDocumentFiles = userDocumentFilesRepository.findAllByUserAccidentDocument(getUserAccidentDocument(entityID, userID));

        if (userDocumentFiles.isEmpty()) {
            throw new ApiRequestException("Файлы данного пользователя не были найдены");
        }

        return userDocumentFiles.get();
    }

    public String updateUserAccidentDocument(UserAccidentDocumentUpdateRequest userAccidentDocumentUpdateRequest, Long userID) {

        final Long carAccidentEntityID = userAccidentDocumentUpdateRequest.getCarAccidentEntityID();
        final String updatedExplanationText = userAccidentDocumentUpdateRequest.getUpdatedExplanationText();

        UserAccidentDocument userAccidentDocument = getUserAccidentDocument(carAccidentEntityID, userID);

        userAccidentDocument.setExplanationText(updatedExplanationText);

        userAccidentDocumentRepository.save(userAccidentDocument);

        changelogService.addNewRecord(userAccidentDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId(), "Заявление от участника №" + userAccidentDocument.getSenderParticipant().getId() + " по делу №" + userAccidentDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId() + " было обновлено в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
        return "Заявление было обновлено";
    }

    public String deleteUserAccidentDocument(UserAccidentDocumentDeleteRequest userAccidentDocumentDeleteRequest, Long userID) {
        final Long carAccidentEntityID = userAccidentDocumentDeleteRequest.getCarAccidentEntityID();

        UserAccidentDocument userAccidentDocument = getUserAccidentDocument(carAccidentEntityID, userID);

        changelogService.addNewRecord(userAccidentDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId(), "Заявление от участника №" + userAccidentDocument.getSenderParticipant().getId() + " по делу №" + userAccidentDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId() + " было удалено в системе", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        userAccidentDocumentRepository.delete(userAccidentDocument);
        return "Заявление было удалено";
    }

    public String deleteUserDocumentsFile(UserDocumentsFileDeleteRequest userDocumentsFileDeleteRequest, Long userID) {

        final String fileLink = userDocumentsFileDeleteRequest.getFileLink();

        Optional<UserDocumentFiles> userDocumentFilesCandidate = userDocumentFilesRepository.findByFileLink(fileLink);

        if (userDocumentFilesCandidate.isEmpty()) {
            throw new ApiRequestException("Файл с таким именем не найден");
        }

        if (!userDocumentFilesCandidate.get().getUserAccidentDocument().getSenderParticipant().getId().equals(userID)) {
            throw new ForbiddenApiException("У вас нет доступа к этому файлу");
        }

        changelogService.addNewRecord(userDocumentFilesCandidate.get().getUserAccidentDocument().getCarAccidentEntityDocuments().getCarAccidentEntity().getId(), "Файл от участника №" + userDocumentFilesCandidate.get().getUserAccidentDocument().getSenderParticipant().getId() + " по заявлению №" + userDocumentFilesCandidate.get().getUserAccidentDocument().getId() + " был удален из системы", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        userDocumentFilesRepository.delete(userDocumentFilesCandidate.get());
        return "Файл о ДТП был удален";
    }

}
