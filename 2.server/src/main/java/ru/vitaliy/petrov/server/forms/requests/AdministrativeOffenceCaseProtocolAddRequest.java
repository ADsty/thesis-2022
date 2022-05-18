package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceCaseProtocolAddRequest {
    private final String dateOfFill;
    private final String timeOfFill;
    private final String placeOfFill;
    private final Long policeOfficerID;
    private final Long culpritID;
    private final String culpritActualPlaceOfResidence;
    private final Long carAccidentEntityID;
    private final String lawViolationInfo;
    private final String caseAdditionalInfo;
    private final String placeAndTimeOfCaseExamination;
    private final String changedPlaceOfCaseExamination;
    private final String explanationsAndRemarksOfProtocol;
    private final Long entityDocumentsID;
}
