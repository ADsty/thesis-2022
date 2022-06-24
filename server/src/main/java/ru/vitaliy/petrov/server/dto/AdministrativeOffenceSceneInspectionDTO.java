package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceSceneInspection;

import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceSceneInspectionDTO {

    private Long administrativeOffenceSceneInspectionID;
    private Date dateOfFill;
    private Time timeOfFill;
    private String placeOfFill;
    private Long trafficPoliceOfficerID;
    private Boolean cameraUsage;
    private String firstWitnessFullName;
    private String firstWitnessResidentialAddress;
    private String firstWitnessPhoneNumber;
    private String secondWitnessFullName;
    private String secondWitnessResidentialAddress;
    private String secondWitnessPhoneNumber;
    private String sceneInspectionInfo;

    public AdministrativeOffenceSceneInspectionDTO(AdministrativeOffenceSceneInspection administrativeOffenceSceneInspection) {
        this.administrativeOffenceSceneInspectionID = administrativeOffenceSceneInspection.getId();
        this.dateOfFill = administrativeOffenceSceneInspection.getDateOfFill();
        this.timeOfFill = administrativeOffenceSceneInspection.getTimeOfFill();
        this.placeOfFill = administrativeOffenceSceneInspection.getPlaceOfFill();
        this.trafficPoliceOfficerID = administrativeOffenceSceneInspection.getTrafficPoliceOfficer().getId();
        this.cameraUsage = administrativeOffenceSceneInspection.getCameraUsage();
        this.firstWitnessFullName = administrativeOffenceSceneInspection.getInspectionWitnessesInfo().getFirstWitness().getWitnessFullName();
        this.firstWitnessResidentialAddress = administrativeOffenceSceneInspection.getInspectionWitnessesInfo().getFirstWitness().getWitnessResidentialAddress();
        this.firstWitnessPhoneNumber = administrativeOffenceSceneInspection.getInspectionWitnessesInfo().getFirstWitness().getWitnessPhoneNumber();
        this.secondWitnessFullName = administrativeOffenceSceneInspection.getInspectionWitnessesInfo().getSecondWitness().getWitnessFullName();
        this.secondWitnessResidentialAddress = administrativeOffenceSceneInspection.getInspectionWitnessesInfo().getSecondWitness().getWitnessResidentialAddress();
        this.secondWitnessPhoneNumber = administrativeOffenceSceneInspection.getInspectionWitnessesInfo().getSecondWitness().getWitnessPhoneNumber();
        this.sceneInspectionInfo = administrativeOffenceSceneInspection.getSceneInspectionInfo();
    }

}
