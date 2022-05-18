package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceCaseInvestigationUpdateRequest {
    private final String updatedDateOfFill;
    private final String updatedTimeOfFill;
    private final String updatedPlaceOfFill;
    private final String updatedInvestigationReason;
    private final String updatedLawViolationInfo;
    private final Long entityDocumentsID;
}
