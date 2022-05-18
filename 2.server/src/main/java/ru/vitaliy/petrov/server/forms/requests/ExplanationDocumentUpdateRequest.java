package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class ExplanationDocumentUpdateRequest {
    private final String updatedDateOfFill;
    private final String updatedTimeOfFill;
    private final String updatedPlaceOfFill;
    private final String updatedInterviewedPersonType;
    private final Long entityDocumentsID;
}
