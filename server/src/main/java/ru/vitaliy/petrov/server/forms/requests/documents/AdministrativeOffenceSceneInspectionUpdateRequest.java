package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceSceneInspectionUpdateRequest {

    @NotNull
    private final Long documentID;

    @NotNull
    private final Date updatedDateOfFill;

    @NotNull
    private final Time updatedTimeOfFill;

    @NotNull
    @NotEmpty
    private final String updatedPlaceOfFill;

    @NotNull
    private final Boolean updatedCameraUsage;

    @NotNull
    private final Long updatedFirstWitnessID;

    @NotNull
    private final Long updatedSecondWitnessID;

    @NotNull
    @NotEmpty
    private final String updatedSceneInspectionInfo;

    @NotNull
    private final Long carAccidentEntityID;
}
