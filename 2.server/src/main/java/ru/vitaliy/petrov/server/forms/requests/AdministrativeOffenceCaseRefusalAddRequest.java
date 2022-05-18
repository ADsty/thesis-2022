package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class AdministrativeOffenceCaseRefusalAddRequest {
    private final String dateOfFill;
    private final String timeOfFill;
    private final String placeOfFill;
    private final Long policeOfficerID;
    private final Long carAccidentInfoSender;
    private final String carAccidentEstablishedInfo;
    private final String refusalReason;
    private final String refusalLawInfo;
    private final Long entityDocumentsID;
}
