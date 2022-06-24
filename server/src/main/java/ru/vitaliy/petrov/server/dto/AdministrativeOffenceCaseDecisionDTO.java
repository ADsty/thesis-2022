package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceCaseDecision;

import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseDecisionDTO {

    private Long documentID;
    private Date dateOfFill;
    private Time timeOfFill;
    private String placeOfFill;
    private Long trafficPoliceOfficerID;
    private Long carAccidentCulpritID;
    private String culpritActualPlaceOfResidence;
    private Long carAccidentEntityID;
    private String caseCircumstances;
    private String caseDecision;
    private Date dateOfReceiving;
    private Date effectiveDate;

    public AdministrativeOffenceCaseDecisionDTO(AdministrativeOffenceCaseDecision administrativeOffenceCaseDecision) {
        this.documentID = administrativeOffenceCaseDecision.getId();
        this.dateOfFill = administrativeOffenceCaseDecision.getDateOfFill();
        this.timeOfFill = administrativeOffenceCaseDecision.getTimeOfFill();
        this.placeOfFill = administrativeOffenceCaseDecision.getPlaceOfFill();
        this.trafficPoliceOfficerID = administrativeOffenceCaseDecision.getTrafficPoliceOfficer().getId();
        this.carAccidentCulpritID = administrativeOffenceCaseDecision.getCarAccidentCulprit().getId();
        this.culpritActualPlaceOfResidence = administrativeOffenceCaseDecision.getCulpritActualPlaceOfResidence();
        this.carAccidentEntityID = administrativeOffenceCaseDecision.getCarAccidentEntity().getId();
        this.caseCircumstances = administrativeOffenceCaseDecision.getCaseCircumstances();
        this.dateOfReceiving = administrativeOffenceCaseDecision.getDateOfReceiving();
        this.effectiveDate = administrativeOffenceCaseDecision.getEffectiveDate();
    }

}
