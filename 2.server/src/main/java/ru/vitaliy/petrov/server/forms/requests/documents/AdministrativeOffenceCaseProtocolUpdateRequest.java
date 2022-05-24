package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdministrativeOffenceCaseProtocolUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedDateOfFill;

    @NotNull
    @NotEmpty
    private final String updatedTimeOfFill;

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
    @NotEmpty
    private final Long entityDocumentsID;
}
