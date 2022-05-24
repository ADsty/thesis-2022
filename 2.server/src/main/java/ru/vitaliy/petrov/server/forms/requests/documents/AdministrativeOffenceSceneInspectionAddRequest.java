package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdministrativeOffenceSceneInspectionAddRequest {

    @NotNull
    @NotEmpty
    private final String dateOfFill;

    @NotNull
    @NotEmpty
    private final String timeOfFill;

    @NotNull
    @NotEmpty
    private final String placeOfFill;

    @NotNull
    @NotEmpty
    private final Long policeOfficerID;

    @NotNull
    @NotEmpty
    private final Boolean cameraUsage;

    @NotNull
    @NotEmpty
    private final String firstWitnessFullName;

    @NotNull
    @NotEmpty
    private final String firstWitnessResidentialAddress;

    @NotNull
    @NotEmpty
    private final String firstWitnessPhoneNumber;

    @NotNull
    @NotEmpty
    private final String secondWitnessFullName;

    @NotNull
    @NotEmpty
    private final String secondWitnessResidentialAddress;

    @NotNull
    @NotEmpty
    private final String secondWitnessPhoneNumber;

    @NotNull
    @NotEmpty
    private final String sceneInspectionInfo;

    @NotNull
    @NotEmpty
    private final Long entityDocumentsID;
}
