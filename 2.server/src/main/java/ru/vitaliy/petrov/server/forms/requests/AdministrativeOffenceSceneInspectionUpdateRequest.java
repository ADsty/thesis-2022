package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceSceneInspectionUpdateRequest {
    private final String updatedDateOfFill;
    private final String updatedTimeOfFill;
    private final String updatedPlaceOfFill;
    private final Boolean updatedCameraUsage;
    private final String updatedFirstWitnessFullName;
    private final String updatedFirstWitnessResidentialAddress;
    private final String updatedFirstWitnessPhoneNumber;
    private final String updatedSecondWitnessFullName;
    private final String updatedSecondWitnessResidentialAddress;
    private final String updatedSecondWitnessPhoneNumber;
    private final String updatedSceneInspectionInfo;
    private final Long entityDocumentsID;
}
