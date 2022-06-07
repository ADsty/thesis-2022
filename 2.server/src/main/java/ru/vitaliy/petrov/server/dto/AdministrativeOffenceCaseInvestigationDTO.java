package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceCaseInvestigation;

import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseInvestigationDTO {

    private Long administrativeOffenceCaseInvestigationID;
    private Date dateOfFill;
    private Time timeOfFill;
    private String placeOfFill;
    private Long trafficPoliceOfficerID;
    private Long carAccidentEntityID;
    private String investigationReason;
    private String lawViolationInfo;

    public AdministrativeOffenceCaseInvestigationDTO(AdministrativeOffenceCaseInvestigation administrativeOffenceCaseInvestigation) {
        this.administrativeOffenceCaseInvestigationID = administrativeOffenceCaseInvestigation.getId();
        this.dateOfFill = administrativeOffenceCaseInvestigation.getDateOfFill();
        this.timeOfFill = administrativeOffenceCaseInvestigation.getTimeOfFill();
        this.placeOfFill = administrativeOffenceCaseInvestigation.getPlaceOfFill();
        this.trafficPoliceOfficerID = administrativeOffenceCaseInvestigation.getTrafficPoliceOfficer().getId();
        this.carAccidentEntityID = administrativeOffenceCaseInvestigation.getCarAccidentEntity().getId();
        this.investigationReason = administrativeOffenceCaseInvestigation.getInvestigationReason();
        this.lawViolationInfo = administrativeOffenceCaseInvestigation.getLawViolationInfo();
    }

}
