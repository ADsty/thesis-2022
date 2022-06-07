package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceSceneScheme;

import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceSceneSchemeDTO {

    private Long administrativeOffenceSceneSchemeID;
    private Date dateOfFill;
    private Time timeOfFill;
    private String placeOfFill;
    private Long trafficPoliceOfficerID;
    private String schemeFileLink;
    private String schemeConventions;
    private String firstWitnessFullName;
    private String firstWitnessResidentialAddress;
    private String firstWitnessPhoneNumber;
    private String secondWitnessFullName;
    private String secondWitnessResidentialAddress;
    private String secondWitnessPhoneNumber;

    public AdministrativeOffenceSceneSchemeDTO(AdministrativeOffenceSceneScheme administrativeOffenceSceneScheme) {
        this.administrativeOffenceSceneSchemeID = administrativeOffenceSceneScheme.getId();
        this.dateOfFill = administrativeOffenceSceneScheme.getDateOfFill();
        this.timeOfFill = administrativeOffenceSceneScheme.getTimeOfFill();
        this.placeOfFill = administrativeOffenceSceneScheme.getPlaceOfFill();
        this.trafficPoliceOfficerID = administrativeOffenceSceneScheme.getTrafficPoliceOfficer().getId();
        this.schemeFileLink = administrativeOffenceSceneScheme.getSchemeFileLink();
        this.schemeConventions = administrativeOffenceSceneScheme.getSchemeConventions();
        this.firstWitnessFullName = administrativeOffenceSceneScheme.getSchemeWitnessesInfo().getFirstWitness().getWitnessFullName();
        this.firstWitnessResidentialAddress = administrativeOffenceSceneScheme.getSchemeWitnessesInfo().getFirstWitness().getWitnessResidentialAddress();
        this.firstWitnessPhoneNumber = administrativeOffenceSceneScheme.getSchemeWitnessesInfo().getFirstWitness().getWitnessPhoneNumber();
        this.secondWitnessFullName = administrativeOffenceSceneScheme.getSchemeWitnessesInfo().getSecondWitness().getWitnessFullName();
        this.secondWitnessResidentialAddress = administrativeOffenceSceneScheme.getSchemeWitnessesInfo().getSecondWitness().getWitnessResidentialAddress();
        this.secondWitnessPhoneNumber = administrativeOffenceSceneScheme.getSchemeWitnessesInfo().getSecondWitness().getWitnessPhoneNumber();
    }

}
