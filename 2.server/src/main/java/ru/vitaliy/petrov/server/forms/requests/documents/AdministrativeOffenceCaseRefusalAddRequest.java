package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdministrativeOffenceCaseRefusalAddRequest {

    @NotNull
    @NotEmpty
    private final String dateOfFill;

    @NotNull
    @NotEmpty
    private final String timeOfFill;

    @NotNull
    @NotEmpty
    private final String placeOfFill;

    @NotNull
    @NotEmpty
    private final Long policeOfficerID;

    @NotNull
    @NotEmpty
    private final Long carAccidentInfoSender;

    @NotNull
    @NotEmpty
    private final String carAccidentEstablishedInfo;

    @NotNull
    @NotEmpty
    private final String refusalReason;

    @NotNull
    @NotEmpty
    private final String refusalLawInfo;

    @NotNull
    @NotEmpty
    private final Long entityDocumentsID;
}