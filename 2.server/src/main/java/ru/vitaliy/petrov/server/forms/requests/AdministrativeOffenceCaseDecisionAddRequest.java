package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceCaseDecisionAddRequest {
    private final String dateOfFill;
    private final String timeOfFill;
    private final String placeOfFill;
    private final Long policeOfficerID;
    private final Long culpritID;
    private final String culpritActualPlaceOfResidence;
    private final Long carAccidentEntityID;
    private final String caseCircumstances;
    private final String caseDecision;
    private final String dateOfReceiving;
    private final String effectiveDate;
    private final Long entityDocumentsID;
}
