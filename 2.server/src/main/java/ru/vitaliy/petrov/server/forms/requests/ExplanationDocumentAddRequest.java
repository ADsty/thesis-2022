package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class ExplanationDocumentAddRequest {
    private final String dateOfFill;
    private final String timeOfFill;
    private final String placeOfFill;
    private final Long policeOfficerID;
    private final Long carAccidentParticipant;
    private final String interviewedPersonType;
    private final Long entityDocumentsID;
}
