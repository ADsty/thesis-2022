package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceCaseProtocol;

import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseProtocolDTO {

    private Long administrativeOffenceCaseProtocolID;
    private Date dateOfFill;
    private Time timeOfFill;
    private String placeOfFill;
    private Long trafficPoliceOfficerID;
    private Long carAccidentCulpritID;
    private String culpritActualPlaceOfResidence;
    private Long carAccidentEntityID;
    private String lawViolationInfo;
    private String caseAdditionalInfo;
    private String placeAndTimeOfCaseExamination;
    private String changedPlaceOfCaseExamination;
    private String explanationsAndRemarksOfProtocol;

    public AdministrativeOffenceCaseProtocolDTO(AdministrativeOffenceCaseProtocol administrativeOffenceCaseProtocol) {
        this.administrativeOffenceCaseProtocolID = administrativeOffenceCaseProtocol.getId();
        this.dateOfFill = administrativeOffenceCaseProtocol.getDateOfFill();
        this.timeOfFill = administrativeOffenceCaseProtocol.getTimeOfFill();
        this.placeOfFill = administrativeOffenceCaseProtocol.getPlaceOfFill();
        this.trafficPoliceOfficerID = administrativeOffenceCaseProtocol.getTrafficPoliceOfficer().getId();
        this.carAccidentCulpritID = administrativeOffenceCaseProtocol.getCarAccidentCulprit().getId();
        this.culpritActualPlaceOfResidence = administrativeOffenceCaseProtocol.getCulpritActualPlaceOfResidence();
        this.carAccidentEntityID = administrativeOffenceCaseProtocol.getCarAccidentEntity().getId();
        this.lawViolationInfo = administrativeOffenceCaseProtocol.getLawViolationInfo();
        this.caseAdditionalInfo = administrativeOffenceCaseProtocol.getCaseAdditionalInfo();
        this.placeAndTimeOfCaseExamination = administrativeOffenceCaseProtocol.getPlaceAndTimeOfCaseExamination();
        this.changedPlaceOfCaseExamination = administrativeOffenceCaseProtocol.getChangedPlaceOfCaseExamination();
        this.explanationsAndRemarksOfProtocol = administrativeOffenceCaseProtocol.getExplanationsAndRemarksOfProtocol();
    }

}
