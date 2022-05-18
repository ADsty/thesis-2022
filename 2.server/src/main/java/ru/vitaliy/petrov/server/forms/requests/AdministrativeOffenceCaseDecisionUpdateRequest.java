package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceCaseDecisionUpdateRequest {
    private final String updatedDateOfFill;
    private final String updatedTimeOfFill;
    private final String updatedPlaceOfFill;
    private final Long updatedCulpritID;
    private final String updatedCulpritActualPlaceOfResidence;
    private final String updatedCaseCircumstances;
    private final String updatedCaseDecision;
    private final String updatedDateOfReceiving;
    private final String updatedEffectiveDate;
    private final Long entityDocumentsID;
}
