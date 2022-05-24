package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdministrativeOffenceCaseDecisionAddRequest {

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
    private final Long culpritID;

    @NotNull
    @NotEmpty
    private final String culpritActualPlaceOfResidence;

    @NotNull
    @NotEmpty
    private final Long carAccidentEntityID;

    @NotNull
    @NotEmpty
    private final String caseCircumstances;

    @NotNull
    @NotEmpty
    private final String caseDecision;

    @NotNull
    @NotEmpty
    private final String dateOfReceiving;

    @NotNull
    @NotEmpty
    private final String effectiveDate;

    @NotNull
    @NotEmpty
    private final Long entityDocumentsID;
}
