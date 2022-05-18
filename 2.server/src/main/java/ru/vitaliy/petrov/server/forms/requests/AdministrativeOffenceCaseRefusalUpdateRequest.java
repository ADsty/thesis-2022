package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceCaseRefusalUpdateRequest {
    private final String updatedDateOfFill;
    private final String updatedTimeOfFill;
    private final String updatedPlaceOfFill;
    private final String updatedCarAccidentEstablishedInfo;
    private final String updatedRefusalReason;
    private final String updatedRefusalLawInfo;
    private final Long entityDocumentsID;
}
