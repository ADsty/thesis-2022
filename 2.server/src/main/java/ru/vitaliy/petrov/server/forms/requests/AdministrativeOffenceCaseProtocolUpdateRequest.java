package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceCaseProtocolUpdateRequest {
    private final String updatedDateOfFill;
    private final String updatedTimeOfFill;
    private final String updatedPlaceOfFill;
    private final String updatedCulpritActualPlaceOfResidence;
    private final String updatedLawViolationInfo;
    private final String updatedCaseAdditionalInfo;
    private final String updatedPlaceAndTimeOfCaseExamination;
    private final String updatedChangedPlaceOfCaseExamination;
    private final String updatedExplanationsAndRemarksOfProtocol;
    private final Long entityDocumentsID;
}
