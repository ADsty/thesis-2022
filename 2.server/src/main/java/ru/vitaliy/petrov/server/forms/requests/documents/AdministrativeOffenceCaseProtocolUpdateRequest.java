package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseProtocolUpdateRequest {

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
    @NotEmpty
    private final String updatedCulpritActualPlaceOfResidence;

    @NotNull
    @NotEmpty
    private final String updatedLawViolationInfo;

    @NotNull
    @NotEmpty
    private final String updatedCaseAdditionalInfo;

    @NotNull
    @NotEmpty
    private final String updatedPlaceAndTimeOfCaseExamination;

    @NotNull
    @NotEmpty
    private final String updatedChangedPlaceOfCaseExamination;

    @NotNull
    @NotEmpty
    private final String updatedExplanationsAndRemarksOfProtocol;

    @NotNull
    private final Long carAccidentEntityID;
}
