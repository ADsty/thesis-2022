package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseDecisionUpdateRequest {

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
    private final Date updatedDateOfReceiving;

    @NotNull
    private final Date updatedEffectiveDate;

    @NotNull
    private final Long carAccidentEntityID;
}
