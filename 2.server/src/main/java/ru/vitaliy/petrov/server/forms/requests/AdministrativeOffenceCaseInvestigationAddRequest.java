package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceCaseInvestigationAddRequest {
    private final String dateOfFill;
    private final String timeOfFill;
    private final String placeOfFill;
    private final Long policeOfficerID;
    private final Long carAccidentEntityID;
    private final String investigationReason;
    private final String lawViolationInfo;
    private final Long entityDocumentsID;
}
