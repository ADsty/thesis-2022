package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdministrativeOffenceCaseDecisionUpdateRequest {

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
    private final Long updatedCulpritID;

    @NotNull
    @NotEmpty
    private final String updatedCulpritActualPlaceOfResidence;

    @NotNull
    @NotEmpty
    private final String updatedCaseCircumstances;

    @NotNull
    @NotEmpty
    private final String updatedCaseDecision;

    @NotNull
    @NotEmpty
    private final String updatedDateOfReceiving;

    @NotNull
    @NotEmpty
    private final String updatedEffectiveDate;

    @NotNull
    @NotEmpty
    private final Long entityDocumentsID;
}
